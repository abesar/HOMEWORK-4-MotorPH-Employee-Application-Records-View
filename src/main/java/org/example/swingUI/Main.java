package org.example.swingUI;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

public class Main {

//    PATH FROM CONTENT ROOT
    static File csvEmployeeDetails = new File("F:\\electronomia\\src\\main\\utils\\MotorPH Employee Data - Employee Details (3).csv");
    static File csvAttendanceDetails = new File("src/main/utils/MotorPH Employee Data - Attendance Record (2).csv");


    // FROM CAMU LEARNING MATERIALS
    public static List<String[]> readAllLinesEmployeeDetails() throws Exception {
        try (Reader reader = Files.newBufferedReader(csvEmployeeDetails.toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return (List<String[]>) csvReader.readAll();
            }
            catch (Exception inner){
                System.out.println("readAllLinesEmployeeDetails() : " + inner.getMessage());
            }
        }
        catch (Exception outer){
            System.out.println("readAllLinesEmployeeDetails() : " + outer.getMessage());
        }
        return null;
    }

    public static List<String[]> readAllLinesAttendanceRecord() throws Exception {
        try (Reader reader = Files.newBufferedReader(csvAttendanceDetails.toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return (List<String[]>) csvReader.readAll();
            }
            catch (Exception inner){
                System.out.println("readAllLinesAttendanceRecord() : " + inner.getMessage());
            }
        }
        catch (Exception outer){
            System.out.println("readAllLinesAttendanceRecord() : " + outer.getMessage());
        }
        return null;
    }


    public static void main(String[] args) throws Exception {

        recordsView rv = new recordsView();
    }
}
