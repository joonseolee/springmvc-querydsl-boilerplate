package com.joonseolee.springmvcquerydslboilerplate.controller;

import com.joonseolee.springmvcquerydslboilerplate.entity.School;
import com.joonseolee.springmvcquerydslboilerplate.entity.SchoolInsertDto;
import com.joonseolee.springmvcquerydslboilerplate.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public List<School> getSchools() {
        return schoolService.findAll();
    }

    @PostMapping
    public School saveSchool(@RequestBody SchoolInsertDto school) {
        return schoolService.save(school);
    }
}
