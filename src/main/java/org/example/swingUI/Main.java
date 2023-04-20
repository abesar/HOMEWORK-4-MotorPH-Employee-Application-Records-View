package org.example.swingUI;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

public class Main {

//  PATH FROM CONTENT ROOT
    static File file_EmployeeDetails = new File("src/main/utils/MotorPH Employee Data - Employee Details (3).csv");
    static File file_AttendanceRecords = new File("src/main/utils/MotorPH Employee Data - Attendance Record (2).csv");


    /**
     *
     *      This code defines a method called "readAllLinesEmployeeDetails()" and "readAllLinesAttendanceRecord()"
     * that attempts to read all lines from a CSV file containing employee details.
     * It uses the CSVReader class to accomplish this.
     *
     *      The code is wrapped in a try-catch block to handle any exceptions that
     * may be thrown while reading the file.
     *      If an exception is caught, it will print out an error message indicating where the problem occurred.
     *
     *      The method returns a list of string arrays containing the parsed CSV data.
     * If an error occurs, it returns null.
     *
     *
     *
     **/
    public static List<String[]> readAllLinesEmployeeDetails() throws Exception {
        try (Reader reader = Files.newBufferedReader(file_EmployeeDetails.toPath())) {
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
        try (Reader reader = Files.newBufferedReader(file_AttendanceRecords.toPath())) {
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


    /** Run the Program
     *      git clone git@github.com:abesar/HOMEWORK-4-MotorPH-Employee-Application-Records-View.git
     *      Once you run the program you will see two visible GUI "recordsView" and "employeeprofileview"
     *
     *
     */
    public static void main(String[] args) throws Exception {

        employeeprofileview EmployeeProfileView = new employeeprofileview();
        recordsView  RecordsView = new recordsView();
    }
}
