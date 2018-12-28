package com.buttoncode.mstest.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GenerallyService {

    public void informationList (){
        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();
    }

    public void informationShow (RedirectAttributes redirectAttributes, List<String> opisInfo, List<String> opisSuccess, List<String> opisWarning, List<String> opisError){
        if(opisInfo.size()>0){
            redirectAttributes.addFlashAttribute("info", String.join("\n", opisInfo));
        }if(opisSuccess.size()>0) {
            redirectAttributes.addFlashAttribute("success", String.join("\n", opisSuccess));
        }if(opisError.size()>0) {
            redirectAttributes.addFlashAttribute("error", String.join("\n", opisError));
        }if(opisWarning.size()>0){
            redirectAttributes.addFlashAttribute("warning", String.join("\n", opisWarning));
        }
    }
}
