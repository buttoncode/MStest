package com.buttoncode.mstest.admin.web.validators;

import com.buttoncode.mstest.core.entities.Employeedata;
import com.buttoncode.mstest.core.entities.Taskbook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TaskbookValidator {

    public List<Taskbook> taskbookListToEmployeedata (Employeedata employeedata, List<Taskbook> taskbookList, List<Employeedata> employeedataList) {

        List<Taskbook> taskbookListToEmployeedata = new ArrayList<>();

        Date start = null;
        Date stop = null;
        Date test = new Date();

        int y = 0;

        for (int i = 0; i<employeedataList.size(); i++){
            if(employeedata.getDepartment().getId() == employeedataList.get(i).getDepartment().getId()){
                if(start == null && stop == null){
                    start = employeedataList.get(i).getBeginningofvalidity();
                }else if (start != null && i+1 == employeedataList.size()){
                    stop = new Date();
                }
            }else{
                if (start != null && stop == null){
                    stop = employeedataList.get(i).getBeginningofvalidity();
                }else if (start != null && i+1 == employeedataList.size()){
                    stop = new Date();
                }
            }
            while(start != null && stop != null){
                Taskbook taskbooktoEmployeedata = null;

                for(int x = 0; x<taskbookList.size(); x++){
                    if(start.after(taskbookList.get(x).getTaskbookdate())){
                        taskbooktoEmployeedata = taskbookList.get(x);
                    }else if(start.equals(taskbookList.get(x).getTaskbookdate())){
                        taskbookListToEmployeedata.add(taskbookList.get(x));
                    }else if(start.before(taskbookList.get(x).getTaskbookdate()) && stop.after(taskbookList.get(x).getTaskbookdate())){
                        while(taskbooktoEmployeedata != null){
                            taskbookListToEmployeedata.add(taskbooktoEmployeedata);
                            taskbooktoEmployeedata = null;
                        }
                        taskbookListToEmployeedata.add(taskbookList.get(x));
                    }
                }
                start = null;
                stop = null;
            }
        }

        removeDuplicateListTaskbook(taskbookListToEmployeedata);

        return taskbookListToEmployeedata;
    }


    public List<Taskbook> removeDuplicateListTaskbook (List<Taskbook> taskbookList){

        for (int i=0; i<taskbookList.size() ;i++){
            for (int j=1; j<taskbookList.size(); j++){
                if(i!=j &&
                        taskbookList.get(i).getId() == taskbookList.get(j).getId()){
                    if(taskbookList.size() == i+1){
                        i = i-1;
                    }
                    taskbookList.remove(j);
                    taskbookList.size();
                }
            }
        }
        return taskbookList;
    }
}
