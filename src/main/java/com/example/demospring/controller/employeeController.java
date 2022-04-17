package com.example.demospring.controller;

import com.example.demospring.model.Cowork;
import com.example.demospring.model.Employee;
import com.example.demospring.model.Project;
import com.example.demospring.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class employeeController {

    @Autowired
    private DataService dataService;

    @RequestMapping("home")
    public ModelAndView greeting() {

        List<Employee> employees = this.dataService.csvToEmployees("src/main/resources/empl.csv");
        Map<String, List<?>> mapCoworkersProjects =   this.dataService.getCoworkersANDprojects(employees);
        List<Cowork> coworkersAll  = (List<Cowork>)mapCoworkersProjects.get("corowkers");
        List<Project> projectsAll=   (List<Project>)mapCoworkersProjects.get("projects");


        ModelAndView mv = new ModelAndView();
        mv.addObject("employees", employees);
        mv.addObject("corowkers", coworkersAll);
        mv.addObject("projects", projectsAll);
        mv.setViewName("home");
        return mv;
    }
}


