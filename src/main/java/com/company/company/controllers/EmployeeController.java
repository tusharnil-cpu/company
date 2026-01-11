package com.company.company.controllers;

import com.company.company.dto.employee.ReqDto;
import com.company.company.dto.employee.ResDto;
import com.company.company.services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @PostMapping("/create")
    public ResDto create(@RequestBody ReqDto dto){
        return employeeServices.create(dto);
    }

    @GetMapping("/details/{id}")
    public ResDto display(@PathVariable Long id){
        return employeeServices.displayDetails(id);
    }

    @PutMapping("/updateProj/{id}")
    public ResDto updateProj(@PathVariable Long id, ReqDto dto){
        return employeeServices.updateProject(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResDto del(@PathVariable Long id){
        return employeeServices.delete(id);
    }

}
