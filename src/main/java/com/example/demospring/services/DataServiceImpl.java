package com.example.demospring.services;

import com.example.demospring.model.Cowork;
import com.example.demospring.model.Employee;
import com.example.demospring.model.Project;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class DataServiceImpl implements DataService {

   public List<Employee> csvToEmployees( String filepath) {

       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try (BufferedReader fileReader = new BufferedReader(new FileReader(filepath));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
                List<Employee> employeesAll = new ArrayList<Employee>();
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();
                Date dateFrom;
                Date dateTo;
                String strDateFrom;
                String strDdateTo;
                for (CSVRecord csvRecord : csvRecords) {
                    strDateFrom = csvRecord.get("DateFrom");
                    strDdateTo = csvRecord.get("DateTo");

                    dateFrom ="NULL".equals(strDateFrom) ? new Date():formatter.parse(strDateFrom);
                    dateTo = "NULL".equals(strDdateTo)? new Date():formatter.parse(strDdateTo);

                    Employee employee = new Employee(
                            Long.parseLong(csvRecord.get("EmpID")),
                            Long.parseLong(csvRecord.get("ProjectID")),
                            dateFrom,
                            dateTo,
                            getDifferenceDays(dateFrom, dateTo));
                    employeesAll.add(employee);
                }
                    return employeesAll;
            } catch (IOException | ParseException e) {
                throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
            }
        }
    public  Map<String, List<?>> getCoworkersANDprojects(List<Employee> employeesAll){
        List<Project> projectsAll = new ArrayList<Project>();
        List<Cowork> coworkersAll = new ArrayList<Cowork>();

        for (int counter = 0; counter < employeesAll.size(); counter++) {
            Employee employee1 = employeesAll.get(counter);

            boolean ProjectIn = projectsAll.stream().anyMatch(o -> employee1.getProjectID()==(o.getId()));
            if (!ProjectIn){
                projectsAll = addproject(projectsAll,employee1.getProjectID() );
            }

            for (int innercounter = counter + 1; innercounter < employeesAll.size(); innercounter++) {
                Employee employee2 = employeesAll.get(innercounter);
                long period = 0;
                if (employee1.getProjectID() == employee2.getProjectID() &&
                        !(employee1.getDateFrom().after(employee2.getDateTo()) || employee2.getDateFrom().after(employee1.getDateTo()))
                ) {
                    if (employee1.getDateFrom().after(employee2.getDateFrom()) &&
                            employee1.getDateTo().before(employee2.getDateTo())) {
                        period = getDifferenceDays(employee1.getDateFrom(), employee1.getDateTo());
                    } else  if (employee2.getDateFrom().after(employee1.getDateFrom()) &&
                            employee2.getDateTo().before(employee1.getDateTo())) {
                        period = getDifferenceDays(employee2.getDateFrom(), employee2.getDateTo());
                    }else  if (employee2.getDateFrom().before(employee1.getDateFrom()) &&
                            employee2.getDateTo().before(employee1.getDateTo()) &&
                            employee2.getDateTo().after(employee1.getDateFrom())) {
                        period = getDifferenceDays(employee1.getDateFrom(), employee2.getDateTo());
                    }else  if (employee2.getDateFrom().before(employee1.getDateTo()) &&
                            employee2.getDateFrom().after(employee1.getDateFrom()) &&
                            employee2.getDateTo().after(employee1.getDateTo())) {
                        period = getDifferenceDays(employee2.getDateFrom(), employee1.getDateTo());
                    }

                    coworkersAll.add( new Cowork(employee1.getId(), employee2.getId(), employee1.getProjectID(), period));
                }
                System.out.println(String.valueOf(employee1.getProjectID()) + ' ' + employee1.getId() + ' ' + employee2.getId() + ' ' + period);
                System.out.println(employeesAll.get(counter));
            }
        }

        coworkersAll.sort(Comparator.comparing(Cowork::getDuration).reversed());
        Map<String, List<?>> coworkersANDprojects = new HashMap<>();
        coworkersANDprojects.put("corowkers", coworkersAll);
        coworkersANDprojects.put("projects", projectsAll);

        return coworkersANDprojects;

    };


 public  long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
 public  List<Project> addproject( List<Project> projectsAll,long projectID ){
      projectsAll.add(new Project(projectID));
       return projectsAll;
    }


}
