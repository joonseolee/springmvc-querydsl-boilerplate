package com.joonseolee.springmvcquerydslboilerplate.service;

import com.joonseolee.springmvcquerydslboilerplate.entity.School;
import com.joonseolee.springmvcquerydslboilerplate.entity.SchoolInsertDto;
import com.joonseolee.springmvcquerydslboilerplate.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final TransactionTemplate mainTransactionTemplate;

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public School save(SchoolInsertDto school) {
        return mainTransactionTemplate.execute(status -> {
            var newSchool = new School();
            newSchool.setName(school.getName());
            return schoolRepository.save(newSchool);
        });
    }
}
