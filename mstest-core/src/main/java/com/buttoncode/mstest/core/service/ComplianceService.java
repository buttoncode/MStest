package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.ComplianceAntiCorruptionPolicy;
import com.buttoncode.mstest.core.entities.ComplianceCodeOfEthic;
import com.buttoncode.mstest.core.repository.ComplianceAntiCorruptionPolicyRepository;
import com.buttoncode.mstest.core.repository.ComplianceCodeOfEthicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ComplianceService {

    @Autowired
    ComplianceCodeOfEthicRepository complianceCodeOfEthicRepository;
    @Autowired
    ComplianceAntiCorruptionPolicyRepository complianceAntiCorruptionPolicyRepository;

    public List<ComplianceCodeOfEthic> getAllComplianceCodeOfEthicWithoutEmployeeStatusAndComplianceStatus(String statusCompliance, String statusEmployee) {
        return complianceCodeOfEthicRepository.findAllWithoutEmployeeStatusAndComplianceStatus(statusCompliance, statusEmployee);}

    public List<ComplianceCodeOfEthic> getAllComplianceCodeOfEthicByEmployeeId(Integer id){
        return complianceCodeOfEthicRepository.findAllByEmployeeId(id);}

    public ComplianceCodeOfEthic createComplianceCodeOfEthic (ComplianceCodeOfEthic complianceCodeOfEthic){
        return complianceCodeOfEthicRepository.save(complianceCodeOfEthic);}

    public ComplianceCodeOfEthic getComplianceCodeOfEthicById (Integer id) {return complianceCodeOfEthicRepository.findOne(id);}

    public ComplianceCodeOfEthic updateComplianceCodeOfEthicStatus (ComplianceCodeOfEthic complianceCodeOfEthic){
        ComplianceCodeOfEthic persistedComplianceCodeOfEthic = getComplianceCodeOfEthicById(complianceCodeOfEthic.getId());
        return complianceCodeOfEthicRepository.save(persistedComplianceCodeOfEthic);}

    public void deleteComplianceCodeOfEthic (Integer id){complianceCodeOfEthicRepository.delete(id);}



    public List<ComplianceAntiCorruptionPolicy> getAllComplianceAntiCorruptionPolicyWithoutEmployeeStatusAndComplianceStatus(String statusCompliance, String statusEmployee) {
        return complianceAntiCorruptionPolicyRepository.findAllWithoutEmployeeStatusAndComplianceStatus(statusCompliance, statusEmployee);}

    public List<ComplianceAntiCorruptionPolicy> getAllComplianceAntiCorruptionPolicyByEmployeeId(Integer id){
        return complianceAntiCorruptionPolicyRepository.findAllByEmployeeId(id);}

    public ComplianceAntiCorruptionPolicy createComplianceAntiCorruptionPolicy (ComplianceAntiCorruptionPolicy complianceAntiCorruptionPolicy){
        return complianceAntiCorruptionPolicyRepository.save(complianceAntiCorruptionPolicy);}

    public ComplianceAntiCorruptionPolicy getComplianceAntiCorruptionPolicyById (Integer id) {return complianceAntiCorruptionPolicyRepository.findOne(id);}

    public ComplianceAntiCorruptionPolicy updateComplianceAntiCorruptionPolicyStatus (ComplianceAntiCorruptionPolicy complianceAntiCorruptionPolicy){
        ComplianceAntiCorruptionPolicy persistedComplianceAntiCorruptionPolicy = getComplianceAntiCorruptionPolicyById(complianceAntiCorruptionPolicy.getId());
        return complianceAntiCorruptionPolicyRepository.save(persistedComplianceAntiCorruptionPolicy);}

    public void deleteComplianceAnticorruptionPolicy (Integer id){complianceAntiCorruptionPolicyRepository.delete(id);}
    //## WERYFIKACJA ##//


    public Date checkDate (Integer mounth) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MONTH, mounth);
        return c.getTime();
    }

    public List<ComplianceCodeOfEthic> getAllComplianceDefaul(Integer mounth, String statusCompliance, String statusEmployee) {
        Date date = checkDate(mounth);
        return complianceCodeOfEthicRepository.findAll();}

    public List<ComplianceCodeOfEthic> getAllComplianceWarning(Integer mounth1, Integer mounth2, String statusCompliance, String statusEmployee) {
        Date date1 = checkDate(mounth1);
        Date date2 = checkDate(mounth2);
        return complianceCodeOfEthicRepository.findAll();}

    public List<ComplianceCodeOfEthic> getAllComplianceDanger(Integer mounth, String statusCompliance, String statusEmployee) {
        Date date1 = checkDate(mounth);
        return complianceCodeOfEthicRepository.findAll();}


    public List<ComplianceCodeOfEthic> getAllCompliance(){
        return complianceCodeOfEthicRepository.findAll();
    }


    public List getAllEmployeeWithoutCompliance () {
        return complianceCodeOfEthicRepository.findAll();
    }

    public List <ComplianceCodeOfEthic> getSearch (Specification<ComplianceCodeOfEthic> complianceSpecification){

        return complianceCodeOfEthicRepository.findAll(complianceSpecification);
    }

}
