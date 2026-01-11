package com.company.company.controllers;

import com.company.company.dto.project.ReqDto;
import com.company.company.dto.project.ResDto;
import com.company.company.services.ProjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proj")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServices projectServices;

    @PostMapping("/create")
    public ResDto create(@RequestBody ReqDto dto){
        return projectServices.create(dto);
    }

    @GetMapping("/details/{id}")
    public ResDto display(@PathVariable Long id){
        return projectServices.displayDetails(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResDto del(@PathVariable Long id){
        return projectServices.delete(id);
    }
}
