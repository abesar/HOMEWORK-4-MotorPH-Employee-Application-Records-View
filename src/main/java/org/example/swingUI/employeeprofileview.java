package org.example.swingUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

import static org.example.swingUI.Main.readAllLinesEmployeeDetails;



public class employeeprofileview extends JFrame{
    // ATTRIBUTES START
    private JPanel panelMain;
    private  JTable EmployeeRecord;
    private  JComboBox comboBox_EmployeeNumber;
    private JScrollPane jScrollPane;
    private  JLabel lbl_selectEmployeeNumber;
    //  ATTRIBUTES END



    /** SETTERS AND GETTERS */

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JTable getEmployeeRecord() {
        return EmployeeRecord;
    }

    public void setEmployeeRecord(JTable employeeRecord) {
        EmployeeRecord = employeeRecord;
    }

    public JComboBox getComboBox_EmployeeNumber() {
        return comboBox_EmployeeNumber;
    }

    public void setComboBox_EmployeeNumber(JComboBox comboBox_EmployeeNumber) {
        this.comboBox_EmployeeNumber = comboBox_EmployeeNumber;
    }

    public JLabel getLbl_selectEmployeeNumber() {
        return lbl_selectEmployeeNumber;
    }

    public void setLbl_selectEmployeeNumber(JLabel lbl_selectEmployeeNumber) {
        this.lbl_selectEmployeeNumber = lbl_selectEmployeeNumber;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }




    /**
     *      This method will convert List<String[]> to String[][]
     *
     *      When the iteration is complete, the 2D Array is returned.
     *
     *      @param list The input list contains one-dimensional String values.
     *
     *      @return data
     **/

    public String[][] data(List<String[]> list){
        // Column Size of CSV.
        int col = list.size();
        // Row Size of first line.
        int row = list.get(0).length;
        //  Data collection containing the String data type
        String[][] data = new String[col][row];


        /** COL COUNTER */
        int outer = 0;
        while (outer < col)
        {
            /** ROW COUNTER */
            int inner = 0;
            while (inner < row)
            {
                // Collect Row Data
                data[outer][inner] = list.get(outer)[inner];
                inner++;
            }
            outer++;
        }
        return data;
    }


    /**
     *  This method creates an array that will store the values in the first column of the file as rows
     *
     *  @param list The input List contains one-dimensional String values.
     *
     *  @return  arr_header
     *
     **/

    public String[] getHeaders(List<String[]> list){
        int rows = 5;
        String [] arr_header = new String[rows];
        int first_line = 0;
        int i = 0;
        while (i < rows){
            arr_header[i] = list.get(first_line)[i];
            i++;
        }
        return arr_header;
    }

    // Constructor

    employeeprofileview() throws Exception {


        List<String[]> list = readAllLinesEmployeeDetails();
        // {Employee # , Last Name , First Name , Birthday , Address}
        String [] header = getHeaders(list);

        list.remove(0); // REMOVE THE HEADER

        TableModel dtm = new DefaultTableModel(data(list), header);

        getEmployeeRecord().setModel(dtm);

        //  Customize Column Width
        TableColumnModel tcm = getEmployeeRecord().getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(100);
        tcm.getColumn(3).setPreferredWidth(250);
        tcm.getColumn(4).setPreferredWidth(250);
        //  Customize Row Height
        getEmployeeRecord().setRowHeight(40);// set height for each

        //  Customize Header
        JTableHeader jth = (getEmployeeRecord().getTableHeader());
        jth.setFont(new Font("Arial", Font.BOLD,16));

        // Add JScroll to JTable
        getjScrollPane().getViewport().add(getEmployeeRecord());


        setTitle("EMPLOYEE PROFILE VIEW");
        setContentPane(getPanelMain());
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
