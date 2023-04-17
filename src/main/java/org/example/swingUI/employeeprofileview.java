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
    // ATTRIB
    private JPanel panelMain;
    private  JTable EmployeeRecord;
    private  JComboBox comboBox_EmployeeNumber;
    private JScrollPane jp;
    private  JLabel lbl_selectEmployeeNumber;

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

    public JScrollPane getJp() {
        return jp;
    }

    public void setJp(JScrollPane jp) {
        this.jp = jp;
    }

    public TableModel allData(List<String[]> list){

        String[] header = {
                "Employee #",
                "Last Name",
                "First Name",
                "Birthday",
                "Address"
        };
        String[][] data = new String[list.size()][list.get(0).length];



        /**  length of the employee data */
        int length_of_col = data.length;
        int length_of_row = data[0].length;

        /** COL COUNTER */
        int col = 0;


        while (col < length_of_col)
        {
            /** ROW COUNTER */
            int row = 0;
            while (row < length_of_row)
            {
                data[col][row] = list.get(col)[row];
                row++;
            }
            col++;
        }
        TableModel dtm = new DefaultTableModel(data, header);
        return dtm;
    }

    employeeprofileview() throws Exception {




        List<String[]> list = readAllLinesEmployeeDetails();
        list.remove(0); // REMOVE THE HEADER




        getEmployeeRecord().setModel(allData(list));
        TableColumnModel tcm = getEmployeeRecord().getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(100);
        tcm.getColumn(3).setPreferredWidth(250);
        tcm.getColumn(4).setPreferredWidth(250);
        getEmployeeRecord().setRowHeight(40);


        JTableHeader jth = (getEmployeeRecord().getTableHeader());
        jth.setFont(new Font("Arial", Font.BOLD,16));

        getJp().getViewport().add(getEmployeeRecord());


        setTitle("EMPLOYEE PROFILE VIEW");
        setContentPane(getPanelMain());
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
