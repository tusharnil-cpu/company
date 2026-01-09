package com.company.company.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// annotaion data comes with getters and setters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqDto {
    private String name;
}