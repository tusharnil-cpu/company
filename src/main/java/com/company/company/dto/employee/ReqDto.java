package com.company.company.dto.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqDto {

    private String name;
    private Long department;
    // here we can choose to not have a project during intitialization defualting to Null
    private Long project;

}