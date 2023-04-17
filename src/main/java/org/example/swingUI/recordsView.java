package org.example.swingUI;

import static org.example.swingUI.Main.readAllLinesAttendanceRecord;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class recordsView<T> extends JFrame
{
    private JPanel panelMain;
    private JTable EmployeeRecord;
    private JScrollPane jp;
    private JComboBox comboBox;

    public JPanel
    getPanelMain ()
    {
        return panelMain;
    }

    public void
    setPanelMain (JPanel panelMain)
    {
        this.panelMain = panelMain;
    }

    public JTable
    getEmployeeRecord ()
    {
        return EmployeeRecord;
    }

    public void
    setEmployeeRecord (JTable employeeRecord)
    {
        EmployeeRecord = employeeRecord;
    }

    public JScrollPane
    getJp ()
    {
        return jp;
    }

    public void
    setJp (JScrollPane jp)
    {
        this.jp = jp;
    }

    public JComboBox
    getComboBox ()
    {
        return comboBox;
    }

    public void
    setComboBox (JComboBox comboBox)
    {
        this.comboBox = comboBox;
    }
    public String[] header(List<String[]> list){

        return new String[]{"Employee #","Last Name","First Name","Date","Time-in","Time-out"};
    }
    public TableModel allData(List<String[]> list){
        // initialize data for the first param of DefaultTableModel
        String[][] data = new String[list.size ()][list.get (0).length];
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
                data[col][row] = list.get (col)[row];
                row++;
            }
            col++;
        }
        TableModel dtm = new DefaultTableModel (data, header(list));
        return dtm;
    }

    public recordsView () throws Exception
    {
        List<String[]> list = readAllLinesAttendanceRecord ();

        list.remove (0); // REMOVE THE HEADER From the list


        //ADDITEMS TO JCOMBOBOX
        removedEmployeeNumberDuplicates(list);


        getEmployeeRecord ().setModel (allData(list));
        TableColumnModel tcm = getEmployeeRecord ().getColumnModel ();
        tcm.getColumn (0).setPreferredWidth (100);
        tcm.getColumn (1).setPreferredWidth (100);
        tcm.getColumn (2).setPreferredWidth (100);
        tcm.getColumn (3).setPreferredWidth (100);
        tcm.getColumn (4).setPreferredWidth (100);
        getEmployeeRecord ().setRowHeight (40);

        JTableHeader jth = (getEmployeeRecord ().getTableHeader ());
        jth.setFont (new Font ("Arial", Font.BOLD, 16));

        getJp ().getViewport ().add (getEmployeeRecord ());

        setTitle ("ATTENDANCE RECORD VIEW");
        setContentPane (getPanelMain ());
        pack ();
        setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo (null);
        setVisible (true);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {


                try{
                    if (Integer.valueOf(e.getItem().toString()) > 0){
                        String [][]arr = (showEmployeeRecordByNumber(comboBox.getSelectedItem().toString(), list)) ;
                        System.out.println("outer: " + arr.length);
                        System.out.println("inner: " + arr[0].length);

                        DefaultTableModel dtm = new DefaultTableModel(arr, header(list));
                        getEmployeeRecord().setModel(dtm);
                    }
                }
                catch (Exception eStateChanged){
                    System.out.println(eStateChanged.getMessage());
                    getEmployeeRecord().setModel(allData(list));
                }


//                System.out.println(countEmployeeNumberAppearance(e.getItem().toString(),list));
            }
        });

//        String [][]arr22d = showEmployeeRecordByNumber(10001,list);
//
//        Arrays.stream(arr22d).forEach(s-> {
//            for (String i: s) {
//                System.out.println(i);
//                System.out.println();
//            }
//        });


    }

    public void
    removedEmployeeNumberDuplicates (List<String[]> list)
    {

        ArrayList EmployeeNumber = new ArrayList ();




        try {
            for (int counter = 0; counter < list.size(); counter++){

                int employee_num = Integer.valueOf (list.get (counter)[0]);

                if (EmployeeNumber.contains (employee_num))
                {
                    break;
                }
                else
                {
                    EmployeeNumber.add(employee_num);
                    getComboBox().addItem(employee_num);
                }
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    public String[][] showEmployeeRecordByNumber(String id_num, List<String[]>list){

        int i = 0;
        int count = 0;
        int col = countEmployeeNumberAppearance(id_num,list);
        System.out.println("COL = " + col);
        int row = list.get(0).length;
        String [][] arr2d = new String[col][row];
        System.out.println("ARR2d = col =" + col);
        System.out.println("ARR2d = row =" + row);
        try{
            while (i < list.size()){ // starting loop: 0 < 2175
                if (list.get(i)[0]. // employee number, ex: 10001
                        equals((id_num))){
                    //  Store the values of employee into
                    int j = 0;
//                  Employee #  ||  Last Name ||  First Name  ||  Date    ||  Time-in   ||  Time-out
                    System.out.println("asdf"+i);
                    while (j < row){ // 0 < 6
                        arr2d[count][j] = list.get(i)[j];
                        j++;
                    }
                    count++;
                }
                i++;
            }
            return arr2d;
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }


    public int countEmployeeNumberAppearance(String employee_number, List<String []> list){
        int count = 0;
        int start = 0;
        int end = list.size();

        while (start < end){
//            if (start == 0){
//                System.out.println("START DATA");
//                Arrays.stream(list.get(0)).forEach(s-> System.out.println("->>>>" + s));
//                String get = list.get(0)[0];
//                System.out.println("GET??? "+ get);
//                System.out.println("mweheheh");
//                System.out.println((employee_number)==(get));
//                System.out.println("???1 " + list.get(0)[0].equals(employee_number));
//                System.out.println("???2 " + list.get(0)[0].equals(String.valueOf(employee_number)));
//                System.out.println("END DATA");
//                System.out.println("SAAN BA AKO NAGKULANG???");
//                System.out.println(employee_number);
//                System.out.println(get);
//                System.out.println("HERE CHECK");
//                System.out.println(employee_number.equals(get));
//            }
            //  Check if the employeenumber equals to the @params employee_number
            if (list.get(start)[0].equals(String.valueOf(employee_number))){
                count++;
            }
            start++;
        }
        return count;
    }

}

