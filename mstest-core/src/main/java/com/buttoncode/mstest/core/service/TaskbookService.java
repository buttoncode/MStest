package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.Taskbook;
import com.buttoncode.mstest.core.repository.TaskbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskbookService {

    @Autowired
    TaskbookRepository taskbookRepository;

    public List<Taskbook> getAllTaskbookByIdEmployeeAndSortedByDate(Integer employeeId) {
        Sort sort = new Sort (Sort.Direction.DESC, "taskbook.taskbookdate");
        return taskbookRepository.findAllByIdEmployee(employeeId); }

    //## WERYFIKACJA ##//

}
