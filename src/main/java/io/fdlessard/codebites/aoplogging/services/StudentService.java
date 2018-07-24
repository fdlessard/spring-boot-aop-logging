package io.fdlessard.codebites.aoplogging.services;

import io.fdlessard.codebites.aoplogging.core.MethodLogger;
import io.fdlessard.codebites.aoplogging.model.Student;
import org.slf4j.event.Level;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @MethodLogger(logLevel = Level.DEBUG)
    public Student getStudent(String name) {
        return new Student(name, Integer.valueOf(60));
    }

    @MethodLogger
    public void postStudent(Student student) {
    }
}
