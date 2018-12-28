package com.buttoncode.mstest.admin.web.controllers;

import com.buttoncode.mstest.admin.security.AuthenticatedUser;
import com.buttoncode.mstest.admin.security.SecurityUtil;
import com.buttoncode.mstest.admin.web.models.EmployeeFileForm;
import com.buttoncode.mstest.admin.web.models.EmployeeSearchForm;
import com.buttoncode.mstest.admin.web.models.EmployeeTaskBookFileForm;
import com.buttoncode.mstest.admin.web.specifications.SearchEmployeeSpecification;
import com.buttoncode.mstest.admin.web.specifications.SearchEmployeedataSpecification;
import com.buttoncode.mstest.admin.web.utils.WebInfoUtils;
import com.buttoncode.mstest.admin.web.utils.WebUtils;
import com.buttoncode.mstest.admin.web.validators.EmployeeSearchFormValidator;
import com.buttoncode.mstest.admin.web.validators.EmployeeTaskBookFileFormValidator;
import com.buttoncode.mstest.admin.web.validators.TaskbookValidator;
import com.buttoncode.mstest.core.MStestException;
import com.buttoncode.mstest.core.entities.*;
import com.buttoncode.mstest.core.service.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@Secured(SecurityUtil.MANAGE_EMPLOYEES)
public class EmployeeController extends MStestAdminBaseController {

    String colorFlashAttribute = "info";
    String informationFlashAttribute = null;

    private static final String viewPrefix = "employees/";

    @Autowired private GenerallyService generallyService;
    @Autowired private EmployeeService employeeService;
    @Autowired private EmployeedataServis employeedataServis;
    @Autowired private TaskbookService taskbookService;
    @Autowired private CostPositionService costPositionService;
    @Autowired private StatusEmployeeService statusEmployeeService;
    @Autowired private DepartmentService departmentService;

    @Autowired private EmployeeTaskBookFileFormValidator employeeTaskBookFileFormValidator;
    @Autowired private EmployeeSearchFormValidator employeeSearchFormValidator;
    @Autowired private TaskbookValidator taskbookValidator;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Employees";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String listEmployee (Model model, AuthenticatedUser authenticatedUser){

        User user = employeeService.getUserByEmail(authenticatedUser.getUsername()); //pobieranie Usera
        List<Employeedata> employeedatasList = employeedataServis.showToEmployeedataAuthentication(user);
        employeedataServis.removeDuplicateListEmployeedata(employeedatasList);

        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        model.addAttribute("employeeStatus", statusEmployeeList);

        List<Department> departmentsList = departmentService.getAllDepartments();
        model.addAttribute("employeedepartament", departmentsList);

        EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
        model.addAttribute("employeesearch", employeeSearchForm);

        EmployeeTaskBookFileForm employeeTaskBookFileForm = new EmployeeTaskBookFileForm();
        model.addAttribute("employeetaskbook", employeeTaskBookFileForm);

        model.addAttribute("employees", employeedatasList);
        return viewPrefix + "employees";
    }


    @RequestMapping(value="/employees/employees_menu", method=RequestMethod.GET)
    public String employeesMenu() {

        return viewPrefix+"employees_menu";
    }

//    ****************************************************************************************************
//    ****************************************  EMPLOYEE TASKBOOK  ***************************************
//    ****************************************************************************************************

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_TASKBOOK)
    @RequestMapping(value="/employees/uploads_taskbook", method=RequestMethod.GET)
    public String uploadTaskbookForm(Model model) {
        EmployeeFileForm employeeFileForm = new EmployeeFileForm();
        model.addAttribute("employeesaction", employeeFileForm);

        return viewPrefix+"employees_upload_taskbook";
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_TASKBOOK)
    @RequestMapping(value="/employees/uploads_taskbook", method=RequestMethod.POST)
    public String uploadTaskBook(@ModelAttribute("employeesaction") EmployeeFileForm employeeFileForm,
                                 Model model, BindingResult result, RedirectAttributes redirectAttributes) throws ParseException {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        Taskbook taskbook = new Taskbook();

        if (!employeeFileForm.getImage2()[0].isEmpty()) {
            for (MultipartFile multipartFile : employeeFileForm.getImage2()) {

                Pattern regexEndFileName = Pattern.compile(".[pdfPDF]{3}");

                String fileName = multipartFile.getOriginalFilename();
                String endFileName = employeeTaskBookFileFormValidator.LastPartFileName(fileName);

                if (regexEndFileName.matcher(endFileName).matches()) {

                    String sapFileName = employeeTaskBookFileFormValidator.FirstPartFileName(fileName);
                    String dateFileName = employeeTaskBookFileFormValidator.DatePartFileName(fileName);

//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFileName);

                    Integer intfirstFileName = Integer.parseInt(sapFileName);
                    Employee employee = employeeService.EmployeeCheckNumberSAP(intfirstFileName);
                    if (employee == null) {
                        opisError.add("Nie znaleziono uzytkownika o numerze SAP: "+employee.getNumbersap()+".");
                    } else {

                        String name = WebUtils.FILE_PDF_TASKBOOK_URL + employee.getId() + "_" + employee.getNumbersap() + WebUtils.IMAGES_TASKBOOK + dateFileName + WebInfoUtils.FILE_FORMAT_PDF;

                        employeeFileForm.setNamefile(employee.getId() + "_" + sapFileName + WebUtils.IMAGES_TASKBOOK + dateFileName + WebInfoUtils.FILE_FORMAT_PDF);

                        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
                        Date parsed = format.parse(dateFileName);
                        employeeFileForm.setTaksbookdate(parsed);

                        employeeFileForm.setEmployee(employee);

                        Taskbook taskbookTaskbook = employeeFileForm.toTaskbook();
                        Taskbook persistedTaskBook = employeeService.uploadTaskBook(taskbookTaskbook);
                        try {
                            byte[] bytes = multipartFile.getBytes();
                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                            stream.write(bytes);
                            stream.close();
                        } catch (Exception e) {
                            throw new MStestException(e);
                        }
                        opisSuccess.add("Plik: " + fileName + " zostal dodany do uzytkownika numer SAP: " + employee.getNumbersap());
                    }
                    }else{
                        opisError.add("Bledny format pliku: " + fileName);
                    }

//            logger.debug("Created new product with id : {}", firstFileName);
//            redirectAttributes.addFlashAttribute("info", "Product created successfully - " + firstFileName);
            }
        }else{
            opisError.add("Nie wybrano zadnego pliku, sprobuj ponownie.");
             }

             generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);

        return viewPrefix + "employees";
    }

//    ****************************************************************************************************
//    ****************************************  EMPLOYEE ADD  ********************************************
//    ****************************************************************************************************

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_ADDEMPLOYEE)
    @RequestMapping(value="/employees/add_employees", method=RequestMethod.GET)
    public String addEmployeeForm(Model model) {
        EmployeeFileForm employeeFileForm = new EmployeeFileForm();
        model.addAttribute("employeesaction", employeeFileForm);

        return viewPrefix+"employees_add_employees";
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_ADDEMPLOYEE)
    @RequestMapping(value="/employees/add_employees", method=RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employeesaction") EmployeeFileForm employeeFileForm, Model model, RedirectAttributes redirectAttributes) throws IOException {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        if(!employeeFileForm.getImage().isEmpty()) {

            Pattern regexEndFileName = Pattern.compile(".[xslXLS]{4}");

            String fileName = employeeFileForm.getImage().getOriginalFilename();
            String endFileName = employeeTaskBookFileFormValidator.LastPartFileName(fileName);

            if (regexEndFileName.matcher(endFileName).matches()) {

                int i = 1;
            XSSFWorkbook workbook = new XSSFWorkbook(employeeFileForm.getImage().getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            while (i <= worksheet.getLastRowNum()) {
                XSSFRow row = worksheet.getRow(i++);

                Integer numbersapInteger = Integer.parseInt(row.getCell(1).getStringCellValue());
                employeeFileForm.setNumersap(numbersapInteger); //employee.numbersap
                Employee employee = employeeService.EmployeeCheckNumberSAP(numbersapInteger);
                if (employee == null) {
                    employeeFileForm.setFirstname(row.getCell(2).getStringCellValue()); //employee.firstname
                    employeeFileForm.setLastname(row.getCell(3).getStringCellValue()); //employee.lastname
                    employeeFileForm.setGroupdesignation(row.getCell(11).getStringCellValue()); //employeedata.groupdesignation -- Grupa pracownikow
                    employeeFileForm.setWorkposition(row.getCell(14).getStringCellValue()); //employeedata.workposition -- Stanowisko

                    CostPosition costPositionExcelEmployee = costPositionService.getOneCostPostiionByName(row.getCell(36).getStringCellValue()); //costposition.name --  MPK
                    employeeFileForm.setDepartament(costPositionExcelEmployee.getDepartments().get(0));

                    StatusEmployee statusExcelEmployee = statusEmployeeService.getStatusEmployeeByName(row.getCell(33).getStringCellValue()); //status.name -- Status
                    employeeFileForm.setStatusEmployee(statusExcelEmployee);
                    employeeFileForm.setBeginningofvalidity(row.getCell(44).getDateCellValue());

                    Employee employeeToEmployee = employeeFileForm.toEmployee();
                    Employee persistedEmployee = employeeService.updateEmployee(employeeToEmployee);

                    Integer employeeExcelid = employeeToEmployee.getId();
                    Employee employeeExcelEmployee = employeeService.getEmployeeById(employeeExcelid);
                    employeeFileForm.setEmployee(employeeExcelEmployee);

                    Employeedata employeedata = employeeFileForm.toEmployeedata();
                    Employeedata persistedEmployeedata = employeeService.updateEmployeedata(employeedata);


                    opisSuccess.add("Pracownik o numerze SAP: " + employeeFileForm.getNumersap()+" zostal dodany do systemu.");
                } else {
                    opisWarning.add("Numer SAP: " + employeeFileForm.getNumersap()+" jest juz zajety.");
                }
            }
            workbook.close();
        }else{
            opisError.add("Wybrany plik [ "+fileName+" ] nie jest formatu XSLX - Excel 2007-2016.");
        }

        }else{
            opisError.add("Nie wybrano zadnego pliku, sprobuj ponownie.");
        }

        generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);

        return "redirect:/employees";
    }

//    ****************************************************************************************************
//    ****************************************  EMPLOYEE WITHOUT  ****************************************
//    ****************************************************************************************************

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_WITHOUT)
    @RequestMapping(value="/employees/employees_without", method=RequestMethod.GET)
    public String employeeWithoutTaskbookForm(Model model) {

        List<Employee> employeesList = employeeService.getAllWithoutEmployee();
//        employeedataServis.removeDuplicateListEmployeedata(employeesList);
        model.addAttribute("employeeWithout", employeesList);

        EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
        model.addAttribute("employeeSearchWithout", employeeSearchForm);

        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        model.addAttribute("employeeStatus", statusEmployeeList);

        return viewPrefix+"employees_without_employees";
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_WITHOUT)
    @RequestMapping(value = "/employees/employees_without", method= RequestMethod.POST)
    public ModelAndView SearchWithout(
            @ModelAttribute ("employeesearch") EmployeeSearchForm employeeSearchForm,
            Model model, AuthenticatedUser authenticatedUser){
        ModelAndView modelAndView = new ModelAndView(viewPrefix+"employees_without_employees");

        employeeSearchForm.setDepartmentshortname("");
        employeeSearchForm.setDepartmentname("");

        SearchEmployeeSpecification searchEmployeeSpecification = new SearchEmployeeSpecification(employeeSearchForm);
        List<Employee> searchEmployeeList = employeeService.getSearch(searchEmployeeSpecification);

//        List<Employeedata> getEmployeedataAuthenticationWithSearch = employeedataServis.getEmployeedataAuthenticationWithSearch(searchEmployeedataList, searchEmployeedataList);
//        List<Employeedata> getEmployeedataAuthenticationWithSearchWithoutDuplicate = employeedataServis.removeDuplicateListEmployeedata(getEmployeedataAuthenticationWithSearch);

/*        List<Employee> employeesList = employeeService.getAllWithoutEmployee();

        SearchEmployeeSpecification searchEmployeeSpecification = new SearchEmployeeSpecification(employeeSearchForm);
        List<Employee> searchEmployeeList = employeeService.getSearch(searchEmployeeSpecification);*/

        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        model.addAttribute("employeeStatus", statusEmployeeList);

        EmployeeFileForm employeeFileForm = new EmployeeFileForm();
        model.addAttribute("employeesaction", employeeFileForm);

        model.addAttribute("employeeSearchWithout", employeeSearchForm);

        modelAndView.addObject("employeeWithout", searchEmployeeList);

        return modelAndView;
    }

//    ****************************************************************************************************
//    ****************************************  EMPLOYEE UPDATE  *****************************************
//    ****************************************************************************************************

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_UPLOADEMPLOYEE)
    @RequestMapping(value="/employees/update_employees", method=RequestMethod.GET)
    public String uploadEmployeeForm(Model model) {
        EmployeeFileForm employeeFileForm = new EmployeeFileForm();
        model.addAttribute("employeeupdate", employeeFileForm);

        return viewPrefix+"employees_update_employees";
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_UPLOADEMPLOYEE)
    @RequestMapping(value="/employees/update_employees", method=RequestMethod.POST)
    public String uploadEmployee(@ModelAttribute("employeesaction") EmployeeFileForm employeeFileForm, Model model, RedirectAttributes redirectAttributes) throws IOException {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();
        int i = 1;

        if (!employeeFileForm.getImage().isEmpty()) {

            Pattern regexEndFileName = Pattern.compile(".[xslXLS]{4}");

            String fileName = employeeFileForm.getImage().getOriginalFilename();
            String endFileName = employeeTaskBookFileFormValidator.LastPartFileName(fileName);

            if (regexEndFileName.matcher(endFileName).matches()) {

                XSSFWorkbook workbook = new XSSFWorkbook(employeeFileForm.getImage().getInputStream());
                XSSFSheet worksheet = workbook.getSheetAt(0);

                while (i <= worksheet.getLastRowNum()) {
                    XSSFRow row = worksheet.getRow(i++);
                    String incident = row.getCell(1).getStringCellValue();

                    Integer numbersapInteger = Integer.parseInt(row.getCell(7).getStringCellValue()); //employee.numbersap
                    Employee employee = employeeService.getEmployeeByNumbersap(numbersapInteger);

                    switch (incident){

                        case "B9" /*### Zwolniony ###*/:
                        case "BA" /*### Zwolniony Emeryt ###*/:
                        case "BM" /*### Powrot z zawieszenia ###*/:

                            if (employee == null) {
                                if(incident.equals("BM")){
                                    incident="BM1";
                                    opisInfo.add("Nie mozna zmienic statusu pracownika na ZWOLNIONY jesli nie ma pracownika w bazie danych [numer SAP: " + numbersapInteger + " - z pliku: " + fileName + " ] PRACOWNIK DODANT JAKO NOWY.");
                                }else {
                                    opisWarning.add("Nie mozna zmienic statusu pracownika na ZWOLNIONY jesli nie ma pracownika w bazie danych [numer SAP: " + numbersapInteger + " - z pliku: " + fileName + " ]");
                                }
                            } else {
                                String descriptionIncident = "Zwolniony";
                                StatusEmployee statusIncident = statusEmployeeService.getStatusEmployeeByName(descriptionIncident);
                                employee.setStatusEmployee(statusIncident);
                                employeeService.updateFileEmployee(employee);
                                opisSuccess.add("Zwolniono pracownika [numer SAP: " + employee.getNumbersap() + " ]");
                            }
                            if(!incident.equals("BM1")){
                                break;
                            }

                        case "BM1" /*### Powrot z zawieszenia ###*/:
                        case "B1"/*### Przyjecie nowego pracownika ###*/:
                            if (employee == null) {
                                //############# Dodawanie nowego pracownika albo dodawanie istniejacego pracownika z istniejacym juz numerem sap -- STATUS - ZWOLNIONY #############
                                employeeFileForm.setNumersap(numbersapInteger); // employee.numbersap
                                employeeFileForm.setFirstname(row.getCell(8).getStringCellValue()); //employee.firstname
                                employeeFileForm.setLastname(row.getCell(9).getStringCellValue()); //employee.lastname
                                employeeFileForm.setGroupdesignation(row.getCell(33).getStringCellValue()); //employeedata.groupdesignation -- Grupa pracownikow
                                employeeFileForm.setWorkposition(row.getCell(36).getStringCellValue()); //employeedata.workposition -- Stanowisko

                                CostPosition costPositionExcelEmployee = costPositionService.getOneCostPostiionByName(row.getCell(54).getStringCellValue()); //costposition.name --  MPK

                                employeeFileForm.setDepartament(costPositionExcelEmployee.getDepartments().get(0));


                                StatusEmployee statusExcelEmployee = statusEmployeeService.getStatusEmployeeByName("Aktywny"); //status.name -- Status
                                employeeFileForm.setStatusEmployee(statusExcelEmployee);

                                employeeFileForm.setBeginningofvalidity(row.getCell(11).getDateCellValue());

                                Employee employeeToEmployee = employeeFileForm.toEmployee();
                                Employee employeeToStatusEmployee = employeeFileForm.toEmployeeStatus();
                                Employee persistedEmployee = employeeService.updateEmployee(employeeToEmployee);

                                Integer employeeExcelid = employeeToEmployee.getId();
                                Employee employeeExcelEmployee = employeeService.getEmployeeById(employeeExcelid);
                                employeeFileForm.setEmployee(employeeExcelEmployee);

                                Employeedata employeedata = employeeFileForm.toEmployeedata();
                                Employeedata persistedEmployeedata = employeeService.updateEmployeedata(employeedata);

                                opisSuccess.add("Dodawanie nowego pracownika [numer SAP: "+numbersapInteger+" ]");

                            } else {
                                opisError.add("Nie mozna dodac nowego pracownika z numerem SAP, ktory jest uzywane przez aktywnego pracownika [numer SAP: "+employee.getNumbersap()+" ]");
                            }
                            break;

                        case "B2"/*### Transfer Pracownika ###*/:
                            if (employee == null) {
                                opisWarning.add("Nie mozna transferowac pracownika, ktorego nie ma w bazie [numeru SAP: " + numbersapInteger + " z pliku: "+fileName+" ].");
                            } else {
                                employeeFileForm.setGroupdesignation(row.getCell(11).getStringCellValue()); //employeedata.groupdesignation -- Grupa pracownikow
                                employeeFileForm.setWorkposition(row.getCell(36).getStringCellValue()); //employeedata.workposition -- Stanowisko

                                CostPosition costPositionExcelEmployee = costPositionService.getOneCostPostiionByName(row.getCell(54).getStringCellValue()); //costposition.name --  MPK

                                employeeFileForm.setDepartament(costPositionExcelEmployee.getDepartments().get(0));


                                employeeFileForm.setBeginningofvalidity(row.getCell(11).getDateCellValue());

                                Integer employeeExcelid = employee.getId();
                                Employee employeeExcelEmployee = employeeService.getEmployeeById(employeeExcelid);
                                employeeFileForm.setEmployee(employeeExcelEmployee);

                                Employeedata employeedata = employeeFileForm.toEmployeedata();
                                Employeedata persistedEmployeedata = employeeService.updateEmployeedata(employeedata);
                                opisSuccess.add("Udany transfer pracownika [numer SAP: "+employee.getNumbersap()+" ]");
                            }
                            break;

                        case "B7"/*### Zawieszenie Pracownika ###*/:
                            if (employee == null) {
                                opisWarning.add("Nie mozna zmienic statusu pracownika na ZWOLNIONY jesli nie ma pracownika w bazie danych [numer SAP: " + numbersapInteger +" - z pliku: "+fileName+" ]");
                            } else {
                                System.out.println("## Jest pracownik w bazie danych -- employee[" + employee.getNumbersap() + "] = nonull");
                                String descriptionIncident = "Zawieszony";
                                StatusEmployee statusIncident = statusEmployeeService.getStatusEmployeeByName(descriptionIncident);
                                employee.setStatusEmployee(statusIncident);
                                employeeService.updateFileEmployee(employee);
                                opisSuccess.add("Zawieszono pracownika [numer SAP: " + employee.getNumbersap() + " ]");
                            }
                            break;

                        case "B8"/*### Przywrocenie zawieszonego pracownika ###*/:
                            if (employee == null) {
                                opisWarning.add("Nie mozna zmienic statusu pracownika na ZWOLNIONY jesli nie ma pracownika w bazie danych [numer SAP: " + numbersapInteger +" - z pliku: "+fileName+" ]");
                            } else {
                                String descriptionIncident = "Aktywny";
                                StatusEmployee statusIncident = statusEmployeeService.getStatusEmployeeByName(descriptionIncident);
                                employee.setStatusEmployee(statusIncident);
                                employeeService.updateFileEmployee(employee);
                                opisSuccess.add("Zawieszono pracownika [numer SAP: " + employee.getNumbersap() + " ]");
                            }
                            break;
                    }
                }
                workbook.close();
            } else {
                opisError.add("Wybrany plik [ "+fileName+" ] nie jest formatu XSLX - Excel 2007-2016.");
            }
        }else{
            opisError.add("Nie wybrano zadnego pliku, sprobuj ponownie.");
        }

        generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);

        return "redirect:/employees";
    }


//    ****************************************************************************************************
//    ****************************************  EMPLOYEE EDIT  *******************************************
//    ****************************************************************************************************

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_EDITEMPLOYEE)
    @RequestMapping(value = "/employees/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    String deleteEmployee(@PathVariable("id") Integer id , Model model, RedirectAttributes redirectAttributes){

        employeeService.deleteEmployee(id);

        logger.debug("Deleted section w");
        redirectAttributes.addFlashAttribute("info", "SectionTop deleted successfully");
        return JSONObject.quote(String.valueOf(id));
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_EDITEMPLOYEE)
    @RequestMapping(value="/employees/{id}", method= RequestMethod.GET)
    public String editEmployeeForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes, AuthenticatedUser authenticatedUser) {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        Employeedata employeedataById = employeedataServis.getEmployeedataById(id);

        User user = employeeService.getUserByEmail(authenticatedUser.getUsername()); //pobieranie Usera

        List<Employeedata> employeedatasList = employeedataServis.showToEmployeedataAuthentication(user);
        List<Employeedata> employeedataListByEmployeeAndDepartament = employeedataServis.checkToEmployeedataAuthentication(employeedatasList, employeedataById);

        if(employeedataListByEmployeeAndDepartament.isEmpty()){
            opisInfo.add("Szczegolowa informacja wyslana do Administra systemu!");
            opisError.add("#ERROR! Brak dostepu.");

            generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);
            return "redirect:/employees";
        }

        EmployeeFileForm employeeFileForm = new EmployeeFileForm();
        model.addAttribute("employeeform", employeeFileForm);

        Employee employee = employeeService.getEmployeeById(employeedataById.getEmployee().getId());
        model.addAttribute("employeeedit",employee);
        model.addAttribute("employeedataedit",employeedataListByEmployeeAndDepartament);

        List<Taskbook> taskbookAllList = taskbookService.getAllTaskbookByIdEmployeeAndSortedByDate(employeedataById.getEmployee().getId());

        List<Employeedata> employeedataListForEmployeer = employeedataServis.getAllEmployeesdataByIdEmployee(employeedataById.getEmployee().getId());
        employeedataServis.sortedByBeginningofvalidity(employeedataListForEmployeer);

        List<Taskbook> taskbookList = taskbookValidator.taskbookListToEmployeedata(employeedataById, taskbookAllList, employeedataListForEmployeer);

        model.addAttribute("taskbookedit", taskbookList);

        return viewPrefix+"employees_edit";
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_EDITEMPLOYEE)
    @RequestMapping(value="/employees/{ide}/create", method=RequestMethod.POST)
    public String createEmployeeInEmployeeForm(@ModelAttribute ("employeeform") EmployeeTaskBookFileForm employeeTaskBookFileForm,
                                                 @PathVariable Integer ide,
                                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws MalformedURLException {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        if (!employeeTaskBookFileForm.getImage().isEmpty()) {

            String fileName = employeeTaskBookFileForm.getImage().getOriginalFilename();
            String endFileName = employeeTaskBookFileFormValidator.LastPartFileName(fileName);

            Employeedata employeedataById = employeedataServis.getEmployeedataById(ide);
            Employee employeeEdit = employeeService.getEmployeeById(employeedataById.getEmployee().getId());

            Pattern regexEndFileName = Pattern.compile(".[pdfPDF]{3}");
                if (regexEndFileName.matcher(endFileName).matches()) {
                    String dateFileName = employeeTaskBookFileForm.getTaskbookdate().toString();

                    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                    String dateaa = dateFormat.format(employeeTaskBookFileForm.getTaskbookdate());

                    String name = WebUtils.FILE_PDF_TASKBOOK_URL + employeeEdit.getId() + "_" + employeeEdit.getNumbersap() + WebUtils.IMAGES_TASKBOOK + dateFileName + WebInfoUtils.FILE_FORMAT_PDF;
                    employeeTaskBookFileForm.setNamefile(employeeEdit.getId() + "_" + employeeEdit.getNumbersap() + WebUtils.IMAGES_TASKBOOK + dateFileName + WebInfoUtils.FILE_FORMAT_PDF);

                    employeeTaskBookFileForm.setEmployee(employeeEdit);

                    Taskbook taskbookTaskbook = employeeTaskBookFileForm.toTaskbook(ide);
                    Taskbook persistedTaskBook = employeeService.uploadTaskBook(taskbookTaskbook);
                    try {
                        byte[] bytes = employeeTaskBookFileForm.getImage().getBytes();
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        throw new MStestException(e);
                    }
                    opisSuccess.add("Plik: " + fileName + " zostal dodany do uzytkownika numer SAP: " + employeeEdit.getNumbersap());
                }else {
                    opisError.add("Bledny format pliku, system przyjmuje jedynie PDF");
                }

        }else{
            opisError.add("Nie wybrano zadnego pliku, sprobuj ponownie.");
        }

        generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);

        return "redirect:/employees/{ide}";
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_EDITEMPLOYEE)
    @RequestMapping(value="/employees/open/{id}", method= RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> showPDF (@PathVariable("id") Integer id, ModelMap modelMap) throws IOException {
        Taskbook taskbook = employeeService.getTaskbookById(id);
        Path path = Paths.get(WebUtils.FILE_PDF_TASKBOOK_URL+taskbook.getNamefile());
        byte[] pdfContents = null;
        try{
            pdfContents = Files.readAllBytes(path);
        } catch (IOException e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);

        return response;
    }

    @Secured(SecurityUtil.MANAGE_EMPLOYEES_EDITEMPLOYEE)
    @RequestMapping(value="/employees/download/{id}", method= RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> downloadPDF (@PathVariable("id") Integer id, ModelMap modelMap) throws IOException {
        Taskbook taskbook = employeeService.getTaskbookById(id);
        Path path = Paths.get(WebUtils.FILE_PDF_TASKBOOK_URL+taskbook.getNamefile());
        byte[] pdfContents = null;
        try{
            pdfContents = Files.readAllBytes(path);
        } catch (IOException e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.add("Content-Disposition", "attachment; filename="+taskbook.getNamefile()+";");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
        return response;
    }

//    ****************************************************************************************************
//    ****************************************  EMPLOYEE SEARCH  *****************************************
//    ****************************************************************************************************

    @RequestMapping(value = "/employees", method= RequestMethod.POST)
    public ModelAndView Search(
            @ModelAttribute ("employeesearch") EmployeeSearchForm employeeSearchForm,
            Model model, AuthenticatedUser authenticatedUser){
        ModelAndView modelAndView = new ModelAndView(viewPrefix+"employees");

        User user = employeeService.getUserByEmail(authenticatedUser.getUsername()); //pobieranie Usera
        List<Employeedata> employeedatasList = employeedataServis.showToEmployeedataAuthentication(user);

        SearchEmployeedataSpecification searchEmployeedataSpecification = new SearchEmployeedataSpecification(employeeSearchForm);
        List<Employeedata> searchEmployeedataList = employeedataServis.getSearch(searchEmployeedataSpecification);

        List<Employeedata> getEmployeedataAuthenticationWithSearch = employeedataServis.getEmployeedataAuthenticationWithSearch(employeedatasList, searchEmployeedataList);
        List<Employeedata> getEmployeedataAuthenticationWithSearchWithoutDuplicate = employeedataServis.removeDuplicateListEmployeedata(getEmployeedataAuthenticationWithSearch);

        List<Department> departmentsList = departmentService.getAllDepartments();
        model.addAttribute("employeedepartament", departmentsList);

        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        model.addAttribute("employeeStatus", statusEmployeeList);

        EmployeeFileForm employeeFileForm = new EmployeeFileForm();
        model.addAttribute("employeesaction", employeeFileForm);

        model.addAttribute("employeesearch", employeeSearchForm);

        EmployeeTaskBookFileForm employeeTaskBookFileForm = new EmployeeTaskBookFileForm();
        model.addAttribute("employeetaskbook", employeeTaskBookFileForm);

        modelAndView.addObject("employees", getEmployeedataAuthenticationWithSearchWithoutDuplicate);

        return modelAndView;
        }

    protected void sendOrderStatusUpdateEmail(Employee employee)
    {
        try {
            // Prepare the evaluation context
            final Context ctx = new Context();
            ctx.setVariable("employee", employee);

/*            // Create the HTML body using Thymeleaf
            final String htmlContent = this.templateEngine.process("order-status-update-email", ctx);*/

/*            emailService.sendEmail(order.getCustomer().getEmail(),
                    "QuilCartCart - Order Status Update",
                    htmlContent);*/

        } catch (MStestException e) {
            logger.error(e);
        }
    }

    protected void sendOrderStatusUpdateEmail1(Employeedata employeedata)
    {
        try {
            // Prepare the evaluation context
            final Context ctx = new Context();
            ctx.setVariable("employeedata", employeedata);

/*            // Create the HTML body using Thymeleaf
            final String htmlContent = this.templateEngine.process("order-status-update-email", ctx);*/

/*            emailService.sendEmail(order.getCustomer().getEmail(),
                    "QuilCartCart - Order Status Update",
                    htmlContent);*/

        } catch (MStestException e) {
            logger.error(e);
        }
    }


    }
