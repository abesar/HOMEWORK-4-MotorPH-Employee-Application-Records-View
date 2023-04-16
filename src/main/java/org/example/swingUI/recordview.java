package org.example.swingUI;

import javax.swing.*;

public class recordview extends JFrame{
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

    recordview(){
        setContentPane(getPanelMain());
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800,600);
        setVisible(true);
    }
}
