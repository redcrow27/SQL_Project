package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utils.DBUtility;
import utils.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class dbValidation {



    @Before
    public void setUp() {
        DBUtility.establishConnection(Database.POSTGRESQL, "demoHR");
    }

    @Test
    public void assertDB() {
       DBUtility.getRowsCount("select * from employees");
       System.out.println( DBUtility.getQueryResults("select first_name from employees").get(1));
    }

    @Test
    public void assertDB2() {
        List<Map<String, Object>> empData = DBUtility.getQueryResults("select first_name from employees limit 10");

        // Print rows -> array starts with 0 as the 1st from SQL
        System.out.println(empData.get(0));
        System.out.println(empData.get(4));


    }

    @Test
    public void assertTitle(){
        List<Map<String, Object>> empData2 = DBUtility.getQueryResults("select title from titles limit 10");
        Assert.assertEquals(empData2.get(1).get("title"), "Staff");
        Assert.assertEquals(empData2.get(2).get("title"), "Senior Engineer");
    }

    @Test
    public void tesr1(){
        List<Map<String, Object>> empData = DBUtility.getQueryResults("select * from departments limit 10");
        Assert.assertEquals(empData.get(6).get("dept_no"), "d007");
        Assert.assertEquals(empData.get(6).get("dept_name"), "Sales");
    }

    @Test
    public void test2(){
        //TODO
        // query first_name from employees and assert "Anneke" and "Berni" from their column

        List<Map<String, Object>> empData = DBUtility.getQueryResults("select * from employees");
        Assert.assertEquals(empData.get(5).get("first_name"), "Anneke");
        Assert.assertEquals(empData.get(13).get("first_name"), "Berni");


    }
    @Test
    public void test3(){
        //TODO
        //query all results from employees table and assert emp_no "1005" birth_date "1955-01-21"
        // and first name "Kyoichi"
        List<Map<String, Object>> empData = DBUtility.getQueryResults("select emp_no, birth_date, first_name from employees ");
        Assert.assertEquals(empData.get(4).get("emp_no"), 10005);
        Assert.assertEquals(empData.get(4).get("first_name"), "Kyoichi");
        Assert.assertEquals(String.valueOf(empData.get(4).get("birth_date")), "1955-01-21");
    }

    @Test
    public void test4(){
        //TODO
        // query the total count from employees table and assert results = 300024
        int count = DBUtility.getRowsCount("select * from employees");
        Assert.assertEquals(count, 300024);
    }


    @Test
    public void test5(){
        //TODO
        // query a list of emp_no whose minimun salary is 40000 or less limit 1
        List<Map<String, Object>> empData = DBUtility.getQueryResults("select emp_no, min(salary) from salaries group by emp_no having min(salary)<=40000 limit 1");
        Assert.assertEquals(empData.get(0).get("min"), 40000);
    }

    @Test
    public void test6(){
        //TODO
        //  query emp_no and salary form the second highets salary table alias salary => as secondHighestSalary
        List<Map<String, Object>> empData2 = DBUtility.getQueryResults("select salary from salaries order by salary desc limit 1 offset 1");
        Assert.assertEquals(empData2.get(0).get("salary"), 157821);
    }





    @After
    public void tearDown() {
        DBUtility.closeConnections();
    }


}
