package com.example.demospring.services;

import com.example.demospring.model.Employee;

import java.util.List;
import java.util.Map;

public interface DataService {

    List<Employee> csvToEmployees(String filepath) ;

    Map<String, List<?>> getCoworkersANDprojects(List<Employee> employees);

}
