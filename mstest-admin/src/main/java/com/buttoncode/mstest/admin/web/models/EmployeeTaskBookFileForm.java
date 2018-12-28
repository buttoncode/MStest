package com.buttoncode.mstest.admin.web.models;

import com.buttoncode.mstest.admin.web.utils.WebInfoUtils;
import com.buttoncode.mstest.admin.web.utils.WebUtils;
import com.buttoncode.mstest.core.entities.Employee;
import com.buttoncode.mstest.core.entities.Taskbook;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeTaskBookFileForm {

    private Integer id;

    private String imageURL;
    private Date createdon;
    private Date taskbookdate;
    private String namefile;

    @NotNull
    @NotEmpty
    MultipartFile[] imageMulti;

    @NotNull
    @NotEmpty
    private MultipartFile image;

    private Employee employee;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getNamefile() {
        return namefile;
    }
    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    public MultipartFile[] getImageMulti() {
        return imageMulti;
    }
    public void setImageMulti(MultipartFile[] imageMulti) {
        this.imageMulti = imageMulti;
    }

    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Date getCreatedon() {
        return createdon;
    }
    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Date getTaskbookdate() {
        return taskbookdate;
    }
    public void setTaskbookdate(Date taskbookdate) {
        this.taskbookdate = taskbookdate;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Taskbook toTaskbook(Integer id) {
        Taskbook tb = new Taskbook();

        tb.setNamefile(WebUtils.IMAGES_TASKBOOK +id+ WebInfoUtils.FILE_FORMAT_PDF);
        tb.setEmployee(employee);
        tb.setTaskbookdate(taskbookdate);

        return tb;
    }


}
