package io.fdlessard.codebites.aoplogging.controllers;

import io.fdlessard.codebites.aoplogging.core.MethodLogger;
import io.fdlessard.codebites.aoplogging.model.Student;
import io.fdlessard.codebites.aoplogging.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @MethodLogger
    @GetMapping(value = "/student/{name}")
    public Student getStudent(@PathVariable String name) {
        return studentService.getStudent(name);
    }

    @MethodLogger
    @PostMapping(value = "/student/{student}")
    @ResponseStatus(HttpStatus.OK)
    public void postStudent(@RequestBody Student student) {
         studentService.postStudent(student);
    }
}
