package com.company.company.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDto {
    private Long id;
    private String name;
    private String department;
    private String project;
}