package org.example.swingUI;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {

    static File csvEmployeeDetails = new File("F:\\electronomia\\src\\main\\utils\\MotorPH Employee Data - Employee Details (3).csv");


    // FROM CAMU LEARNING MATERIALS
    public static List<String[]> readAllLines() throws Exception {
        try (Reader reader = Files.newBufferedReader(csvEmployeeDetails.toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return (List<String[]>) csvReader.readAll();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        recordview rc = new recordview();


        String[] header = {
                "Employee #",
                "Last Name",
                "First Name",
                "Birthday",
                "Address"
        };


        List<String[]> list = readAllLines();
        System.out.println("LENGTH");
        System.out.println(list.get(0).length);
        System.out.println("SIZE");
        System.out.println(list.size());

        String[][] data = {
                list.get(1),
                list.get(2),
                list.get(3),
                list.get(4),
                list.get(5),
                list.get(6),
                list.get(7),
                list.get(8),
                list.get(9),
                list.get(10),
                list.get(11),
                list.get(12),
                list.get(13),
                list.get(14),
                list.get(15),
                list.get(16),
                list.get(17),
                list.get(18),
                list.get(19),
                list.get(20),
                list.get(21),
                list.get(22),
                list.get(23),
                list.get(24),
                list.get(25),
        };

        System.out.println(data[17].length);

        TableModel dtm = new DefaultTableModel(data, header);

        rc.getEmployeeRecord().setModel(dtm);
        TableColumnModel tcm = rc.getEmployeeRecord().getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(100);
        tcm.getColumn(3).setPreferredWidth(250);
        tcm.getColumn(4).setPreferredWidth(250);
        rc.getEmployeeRecord().setRowHeight(40);


        JTableHeader jth = (rc.getEmployeeRecord().getTableHeader());
        jth.setFont(new Font("Arial", Font.BOLD,16));

        rc.getJp().getViewport().add(rc.getEmployeeRecord());


    }
}
