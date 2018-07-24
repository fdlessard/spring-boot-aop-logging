package io.fdlessard.codebites.aoplogging.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class Student {

    private String name;
    private Integer age;
}