package com.company.company.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDto {
    private int id;
    private String name;
    private String[] employees;
    private String department;
}
