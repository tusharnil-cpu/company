package com.company.company.controllers;

import com.company.company.dto.department.ReqDto;
import com.company.company.dto.department.ResDto;
import com.company.company.services.DeptServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptServices deptServices;

    @PostMapping("/create")
    public ResDto create(@RequestBody ReqDto dto){
        return deptServices.create(dto);
    }

    @GetMapping("/details/{id}")
    public ResDto display(@PathVariable Long id){
        return deptServices.displayDetails(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResDto del(@PathVariable Long id){
        return deptServices.delete(id);
    }

}
