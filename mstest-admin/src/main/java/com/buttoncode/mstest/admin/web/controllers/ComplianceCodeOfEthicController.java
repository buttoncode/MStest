package com.buttoncode.mstest.admin.web.controllers;

import com.buttoncode.mstest.admin.security.AuthenticatedUser;
import com.buttoncode.mstest.admin.security.SecurityUtil;
import com.buttoncode.mstest.admin.web.models.ComplianceForm;
import com.buttoncode.mstest.admin.web.models.ComplianceSearchForm;
import com.buttoncode.mstest.admin.web.models.EmployeeSearchForm;
import com.buttoncode.mstest.admin.web.specifications.SearchComplianceSpecification;
import com.buttoncode.mstest.admin.web.specifications.SearchEmployeeSpecification;
import com.buttoncode.mstest.admin.web.utils.WebInfoUtils;
import com.buttoncode.mstest.admin.web.utils.WebUtils;
import com.buttoncode.mstest.admin.web.validators.EmployeeTaskBookFileFormValidator;
import com.buttoncode.mstest.core.MStestException;
import com.buttoncode.mstest.core.entities.*;
import com.buttoncode.mstest.core.service.*;
import jcifs.smb.*;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@Secured(SecurityUtil.MANAGE_COMPLIANCES_CODE_OF_ETHIC)
public class ComplianceCodeOfEthicController extends MStestAdminBaseController {
        @Autowired
        ComplianceService complianceService;
        @Autowired
        StatusComplianceService statusComplianceService;
        @Autowired
        StatusEmployeeService statusEmployeeService;
        @Autowired
        EmployeeService employeeService;
        @Autowired
        WaitingTimeService waitingTimeService;
        @Autowired private GenerallyService generallyService;
        @Autowired private EmployeeTaskBookFileFormValidator employeeTaskBookFileFormValidator;

        @Override
        protected String getHeaderTitle() {
            return "Manage ComplianceCodeOfEthic";
        }

        private static final String viewPrefix = "compliances/code_of_ethic/";

        @ModelAttribute("complianceStatusList")
        public List<StatusCompliance> statusCompliancesList()
    {
        return statusComplianceService.getAllStatusCompliance();
    }

        @ModelAttribute("employeeStatus")
        public List<StatusEmployee> statusEmployees()
    {
        return statusEmployeeService.getAllStatusEmployee();
    }




//    ****************************************************************************************************
//    ****************************************  ACTIVE COMPLIANCES  **************************************
//    ****************************************************************************************************
        @RequestMapping(value = "/compliances/code_of_ethic", method = RequestMethod.GET)
        public String compliance(Model model)
        {
            ComplianceSearchForm complianceSearchForm = new ComplianceSearchForm();
            model.addAttribute("compliancesearch", complianceSearchForm);

            List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
            List<StatusCompliance> statusComplianceList = statusComplianceService.getAllStatusCompliance();

            String statusEmployeeName = statusEmployeeList.get(4).getName();
            String statusComplianceName = statusComplianceList.get(0).getName();

            model.addAttribute("employeeStatus", statusEmployeeList);
            model.addAttribute("complianceStatus", statusComplianceList);

            List<ComplianceCodeOfEthic> complianceCodeOfEthicList = complianceService.getAllComplianceCodeOfEthicWithoutEmployeeStatusAndComplianceStatus(statusComplianceName, statusEmployeeName);
            model.addAttribute("complianceListDefault", complianceCodeOfEthicList);

            return viewPrefix+"compliances_compliances";
        }

    @RequestMapping(value = "/compliances/code_of_ethic/compliance", method= RequestMethod.POST)
    public ModelAndView Search(
            @ModelAttribute("compliancesearch") ComplianceSearchForm complianceSearchForm,
            Model model, AuthenticatedUser authenticatedUser){

        ModelAndView modelAndView = new ModelAndView(viewPrefix+"compliances_compliances");

        SearchComplianceSpecification searchComplianceSpecification = new SearchComplianceSpecification(complianceSearchForm);
        List<ComplianceCodeOfEthic> listSearchComplianceCodeOfEthic = complianceService.getSearch(searchComplianceSpecification);
        model.addAttribute("complianceListDefault", listSearchComplianceCodeOfEthic);


        List<StatusCompliance> complianceComplianceList = statusComplianceService.getAllStatusCompliance();
        model.addAttribute("complianceStatus", complianceComplianceList);

        return modelAndView;
    }


//    ****************************************************************************************************
//    ****************************************  EMPLOYEE WITHOUT  ****************************************
//    ****************************************************************************************************
    @RequestMapping(value = "/compliances/code_of_ethic/employee_without", method = RequestMethod.GET)
    public String complianceNewEmployee(Model model)
    {
        String status = "Zwolniony";
        List<Employee> employeeListWithoutCompliance = employeeService.getAllEmployeeWithoutComplianceCodeOfEhic(status);
        model.addAttribute("complianceWithout", employeeListWithoutCompliance);

        EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
        model.addAttribute("employeeSearchWithout", employeeSearchForm);

        return viewPrefix+"compliances_employee_without";
    }

    @RequestMapping(value = "/compliances/code_of_ethic/employee_without", method= RequestMethod.POST)
    public ModelAndView SearchEmployeeWithout(
            @ModelAttribute("searchemployeewithout") EmployeeSearchForm employeeSearchForm,
            Model model, AuthenticatedUser authenticatedUser){

        ModelAndView modelAndView = new ModelAndView(viewPrefix+"compliances_employee_without");

        model.addAttribute("employeeSearchWithout", employeeSearchForm);

        employeeSearchForm.setDepartmentname("");
        employeeSearchForm.setDepartmentshortname("");

        SearchEmployeeSpecification searchEmployeeSpecification = new SearchEmployeeSpecification(employeeSearchForm);
        List<Employee> employeeSearchWithout = employeeService.getSearch(searchEmployeeSpecification);

        model.addAttribute("complianceWithout", employeeSearchWithout);

        return modelAndView;
    }


//    ************************************************************************************************
//    ****************************************  EMPLOYEE ALL  ****************************************
//    ************************************************************************************************
    @RequestMapping(value = "/compliances/code_of_ethic/employee_all", method = RequestMethod.GET)
    public String complianceEmployeeAll(Model model)
    {
        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        String statusEmployeeName = statusEmployeeList.get(4).getName();
        List<Employee> allEmployeeList = employeeService.getAllEmployeeWithStatusEmployee(statusEmployeeName);
        model.addAttribute("employeeAll", allEmployeeList);

        EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
        model.addAttribute("employeeSearchAll", employeeSearchForm);

        return viewPrefix+"compliances_employee_all";
    }


    @RequestMapping(value = "/compliances/code_of_ethic/employee_all", method= RequestMethod.POST)
    public ModelAndView SearchEmployeeAll(
            @ModelAttribute("searchemployeeall") EmployeeSearchForm employeeSearchForm,
            Model model, AuthenticatedUser authenticatedUser){

        ModelAndView modelAndView = new ModelAndView(viewPrefix+"compliances_employee_all");

        employeeSearchForm.setDepartmentname("");
        employeeSearchForm.setDepartmentshortname("");
        SearchEmployeeSpecification searchEmployeeSpecification = new SearchEmployeeSpecification(employeeSearchForm);
        model.addAttribute("employeeSearchAll", employeeSearchForm);

        List<Employee> searchEmployeedataList = employeeService.getSearch(searchEmployeeSpecification);
        model.addAttribute("employeeAll", searchEmployeedataList);

        return modelAndView;
    }

//    ************************************************************************************************
//    ****************************************  COMPLIANCE EMPLOYEE EDIT  ****************************
//    ************************************************************************************************
    @RequestMapping(value="/compliances/code_of_ethic/{id}", method=RequestMethod.GET)
    public String editComplianceEmployeeForm(@PathVariable Integer id, Model model) {

        ComplianceForm complianceForm = new ComplianceForm();
        model.addAttribute("complianceform",complianceForm);

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);

        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        model.addAttribute("employeeStatusList",statusEmployeeList);

        List<ComplianceCodeOfEthic> complianceCodeOfEthic = complianceService.getAllComplianceCodeOfEthicByEmployeeId(id);
        model.addAttribute("complianceEmployeeList", complianceCodeOfEthic);

        List<StatusCompliance> statusComplianceList = statusComplianceService.getAllStatusCompliance();
        model.addAttribute("complianceStatusList",statusComplianceList);

        return viewPrefix+"compliances_edit";
    }

    @RequestMapping(value="/compliances/code_of_ethic/{ide}/edit/{idc}/{idsc}", method=RequestMethod.GET)
//    public String editComplianceForm(@PathVariable Integer idc, Model model) {
    public String editComplianceForm(@PathVariable Integer ide,
                                     @PathVariable Integer idc,
                                     @PathVariable Integer idsc,
                                     Model model) {

        ComplianceCodeOfEthic complianceCodeOfEthic = complianceService.getComplianceCodeOfEthicById(idc);
        StatusCompliance statusCompliance = new StatusCompliance();

        if(idsc == 1){
            System.out.println("dsc = 1");
            statusCompliance = statusComplianceService.getStatusComplianceById(2);
        }else if(idsc == 2){
            System.out.println("dsc =/= 1");
            statusCompliance = statusComplianceService.getStatusComplianceById(1);
        }

        complianceCodeOfEthic.setStatusCompliance(statusCompliance);
        complianceService.updateComplianceCodeOfEthicStatus(complianceCodeOfEthic);

        Employee employee = employeeService.getEmployeeById(ide);
        model.addAttribute("employee",employee);

        List<StatusEmployee> statusEmployeeList = statusEmployeeService.getAllStatusEmployee();
        model.addAttribute("employeeStatusList",statusEmployeeList);

        ComplianceForm complianceForm = new ComplianceForm();
        model.addAttribute("complianceform",complianceForm);

        List<ComplianceCodeOfEthic> complianceCodeOfEthic1 = complianceService.getAllComplianceCodeOfEthicByEmployeeId(ide);
        model.addAttribute("complianceEmployeeList", complianceCodeOfEthic1);

        List<StatusCompliance> statusComplianceList = statusComplianceService.getAllStatusCompliance();
        model.addAttribute("complianceStatusList",statusComplianceList);

        return viewPrefix+"compliances_edit";
    }

    @RequestMapping(value = "/compliances/code_of_ethic/{ide}/delete/{idc}",method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    String deleteComplianceForm(@PathVariable("ide") Integer ide,
                                @PathVariable("idc") Integer idc, Model model, RedirectAttributes redirectAttributes) throws MalformedURLException, SmbException {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        ComplianceCodeOfEthic complianceCodeOfEthic = complianceService.getComplianceCodeOfEthicById(idc);

        NtlmPasswordAuthentication ntlmPasswordAuthentication=new NtlmPasswordAuthentication(WebUtils.DOMAIN_NAME_CONNECT_TO_SERVER, WebUtils.USER_NAME_CONNECT_TO_SERVER, WebUtils.PASSWORD_NAME_CONNECT_TO_SERVER);
        SmbFile smbFile = new SmbFile(WebUtils.FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_URL + complianceCodeOfEthic.getFilename(), ntlmPasswordAuthentication);
        SmbFile smbFileDeleted = new SmbFile(WebUtils.FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_DELETED_URL + complianceCodeOfEthic.getFilename(), ntlmPasswordAuthentication);

        SmbFileInputStream smbFileInputStream = null;
        byte[] pdfContents  = new byte[(int) smbFile.length()];
        try{
            smbFileInputStream = new SmbFileInputStream(smbFile);
            smbFileInputStream.read(pdfContents);
            smbFileInputStream.close();

            BufferedOutputStream stream = new BufferedOutputStream(new SmbFileOutputStream(smbFileDeleted));
            stream.write(pdfContents);
            stream.close();

            smbFile.delete();

        } catch (Exception e) {
            throw new MStestException(e);
        }
        complianceService.deleteComplianceCodeOfEthic(idc);

        opisWarning.add("Szkolenie z Etyki zostalo usuniete.");
        generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);

        return JSONObject.quote(String.valueOf(ide));
    }

    @RequestMapping(value="/compliances/code_of_ethic/{ide}/create", method=RequestMethod.POST)
    public String createComplianceInEmployeeForm(@ModelAttribute ("complianceform") ComplianceForm complianceForm,
                                                 @PathVariable Integer ide,
                                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws MalformedURLException {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        if (!complianceForm.getImage().isEmpty()) {

            Pattern regexEndFileName = Pattern.compile(".[pdfPDF]{3}");

            String fileName = complianceForm.getImage().getOriginalFilename();
            String endFileName = employeeTaskBookFileFormValidator.LastPartFileName(fileName);

            if (regexEndFileName.matcher(endFileName).matches()) {

                WaitingTime waitingTime = waitingTimeService.getByTime(complianceForm.getWaitingtime());
                complianceForm.setWaitingTime(waitingTime);
                int waitingTimeInt = Integer.parseInt(waitingTime.getTime());
                long milisec = (long) waitingTimeInt * 365 * 24 * 60 * 60 * 1000;
                complianceForm.setExpirydateoftraining(new Date(complianceForm.getDateoftrainging().getTime() + milisec));

                StatusCompliance statusCompliance = statusComplianceService.getStatusComplianceById(1);
                complianceForm.setStatusCompliance(statusCompliance);

                Employee employee = employeeService.getEmployeeById(ide);
                complianceForm.setEmployee(employee);

                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                String datatofilename = dateFormat.format(complianceForm.getDateoftrainging());

                String name = System.currentTimeMillis() + "_" + employee.getId() + "_" + employee.getNumbersap() + WebUtils.IMAGES_COMPLIANCE_CODE_OF_ETHIC + datatofilename + WebInfoUtils.FILE_FORMAT_PDF;
                complianceForm.setFilename(name);

                ComplianceCodeOfEthic complianceCodeOfEthic = complianceForm.toComplianceCodeOfEthic();

                complianceService.createComplianceCodeOfEthic(complianceCodeOfEthic);

                NtlmPasswordAuthentication ntlmPasswordAuthentication=new NtlmPasswordAuthentication(WebUtils.DOMAIN_NAME_CONNECT_TO_SERVER, WebUtils.USER_NAME_CONNECT_TO_SERVER, WebUtils.PASSWORD_NAME_CONNECT_TO_SERVER);
                SmbFile smbFile = new SmbFile (WebUtils.FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_URL + name, ntlmPasswordAuthentication);

                try {
                    byte[] bytes = complianceForm.getImage().getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new SmbFileOutputStream(smbFile));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    throw new MStestException(e);
                }

                opisSuccess.add("Szkolenie z etyki zostalo dodane na okres "+ waitingTimeInt +" lat oraz plik z oswiadczeniem: " + fileName + " zostal dodany do uzytkownika numer SAP: " + employee.getNumbersap());

            }else {
                opisError.add("Bledny format pliku: " + fileName);
            }
        }else{
            opisError.add("Nie wybrano zadnego pliku, sprobuj ponownie.");
        }

        generallyService.informationShow(redirectAttributes, opisInfo, opisSuccess, opisWarning, opisError);

        return "redirect:/compliances/code_of_ethic/{ide}";
    }

    @RequestMapping(value="/compliances/code_of_ethic/open/{id}", method= RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> showPDF (@PathVariable("id") Integer id, ModelMap modelMap) throws IOException {
        ComplianceCodeOfEthic complianceCodeOfEthic = complianceService.getComplianceCodeOfEthicById(id);

        NtlmPasswordAuthentication ntlmPasswordAuthentication=new NtlmPasswordAuthentication(WebUtils.DOMAIN_NAME_CONNECT_TO_SERVER, WebUtils.USER_NAME_CONNECT_TO_SERVER, WebUtils.PASSWORD_NAME_CONNECT_TO_SERVER);
        SmbFile smbFile = new SmbFile(WebUtils.FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_URL + complianceCodeOfEthic.getFilename(), ntlmPasswordAuthentication);

        SmbFileInputStream smbFileInputStream = null;
        byte[] pdfContents  = new byte[(int) smbFile.length()];
        try{
            smbFileInputStream = new SmbFileInputStream(smbFile);
            smbFileInputStream.read(pdfContents);
            smbFileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);

        return response;
    }

    @RequestMapping(value="/compliances/code_of_ethic/download/{id}", method= RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> downloadPDF (@PathVariable("id") Integer id, ModelMap modelMap) throws IOException {
//        Taskbook taskbook = employeeService.getTaskbookById(id);
        ComplianceCodeOfEthic complianceCodeOfEthic = complianceService.getComplianceCodeOfEthicById(id);

        NtlmPasswordAuthentication ntlmPasswordAuthentication=new NtlmPasswordAuthentication(WebUtils.DOMAIN_NAME_CONNECT_TO_SERVER, WebUtils.USER_NAME_CONNECT_TO_SERVER, WebUtils.PASSWORD_NAME_CONNECT_TO_SERVER);
        SmbFile smbFile = new SmbFile(WebUtils.FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_URL + complianceCodeOfEthic.getFilename(), ntlmPasswordAuthentication);

        SmbFileInputStream smbFileInputStream = null;
        byte[] pdfContents  = new byte[(int) smbFile.length()];
        try{
            smbFileInputStream = new SmbFileInputStream(smbFile);
            smbFileInputStream.read(pdfContents);
            smbFileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.add("Content-Disposition", "attachment; filename="+ complianceCodeOfEthic.getFilename()+";");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value="/compliances/code_of_ethic/compliances_menu", method=RequestMethod.GET)
    public String compliancesMenu() {

        return viewPrefix+"compliances_menu";
    }

}
