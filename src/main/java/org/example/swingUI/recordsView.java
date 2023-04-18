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

    /** Attributes */

    private JPanel panelMain;
    private JTable EmployeeRecord;
    private JScrollPane jScrollPane;
    private JComboBox comboBox;


    /* Getter and Setter */


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
    getjScrollPane()
    {
        return jScrollPane;
    }

    public void
    setjScrollPane(JScrollPane jScrollPane)
    {
        this.jScrollPane = jScrollPane;
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

    /*
     *  This method will return the CSV's header.
     */

    public String[] header(){
        return new String[]{"Employee #","Last Name","First Name","Date","Time-in","Time-out"};
    }

    /**
     *      This method will convert List<String[]> to String[][]
     *
     *      When the iteration is complete, the 2D Array is returned.
     *
     *      @param list The input List contains one-dimensional String values.
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
//                System.out.println("inner " + inner);
            }
//            System.out.println("outer " + outer);
            outer++;
        }
        return data;
    }


    /** Constructor */
    public recordsView () throws Exception
    {
        //  Gathering data from the CSV file
        List<String[]> list = readAllLinesAttendanceRecord ();
        //  REMOVE THE HEADER From the list
        list.remove (0);


        //  ADDITEMS TO JCOMBOBOX
        removedEmployeeNumberDuplicates(list);


        //  initializes the table by passing data and columnNames
        TableModel tm = new DefaultTableModel(data(list), header());
        //  Set data model
        getEmployeeRecord ().setModel (tm);
        //  Customize columns width
        TableColumnModel tcm = getEmployeeRecord ().getColumnModel ();
        tcm.getColumn (0).setPreferredWidth (100);
        tcm.getColumn (1).setPreferredWidth (100);
        tcm.getColumn (2).setPreferredWidth (100);
        tcm.getColumn (3).setPreferredWidth (100);
        tcm.getColumn (4).setPreferredWidth (100);
        getEmployeeRecord ().setRowHeight (40);
        //  Customize Header of the Table
        JTableHeader jth = (getEmployeeRecord ().getTableHeader ());
        jth.setFont (new Font ("Arial", Font.BOLD, 16));
        //  Add scroll to table
        getjScrollPane().getViewport ().add (getEmployeeRecord ());

        setTitle ("ATTENDANCE RECORD VIEW");
        setContentPane (getPanelMain ());
        pack ();
        setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo (null);
        setVisible (true);



        /*
         *  The user can select a specific employee number, which will instantly update the table.
         *
         *  The table will only show the data of the selected employee number
         */

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    String [][]arr = (showEmployeeRecordsByNumber(comboBox.getSelectedItem().toString(), list)) ;
                    System.out.println("outer: " + arr.length);
                    System.out.println("inner: " + arr[0].length);
                    DefaultTableModel dtm = new DefaultTableModel(arr, header());
                    getEmployeeRecord().setModel(dtm);
                }

                /*
                 *  Because view record is a string, there will be an error whenever it is picked.
                 */
                catch (Exception eStateChanged){
                    System.out.println(eStateChanged.getMessage());
                    /***  Call the data() method to the tablemodel display all records. ***/
                    DefaultTableModel dtm = new DefaultTableModel(data(list), header());
                    getEmployeeRecord().setModel(dtm);
                }
            }
        });
    }

    /*
     *  This method will eliminate employee number duplicates.
     *
     *  @param The input List contains one-dimensional String values.
     *
     *
     */
    public void
    removedEmployeeNumberDuplicates (List<String[]> list)
    {
        ArrayList EmployeeNumber = new ArrayList ();
        try
        {
            for (int counter = 0; counter < list.size(); counter++)
            {
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


    /*
     *  This method will store all the record of the Specified input employee number
     *
     *  @param employee_number Enter employee_number to find all occurrences of that number.
     *
     *  @param list The input List contains one-dimensional String values.
     *
     *  @return arr2d
     */
    public String[][] showEmployeeRecordsByNumber(String employee_number, List<String[]>list){

        int i = 0;
        //  Count each appearance of the employee_number on the csv.
        int count = 0;
        //  The total number of times the employee number appears
        int col = countEmployeeNumberAppearance(employee_number,list);
        //  The first line's length
        int row = list.get(0).length;
        // Declare a two-dimensional array of integers with col size and row size
        String [][] arr2d = new String[col][row];
        try{
            while (i < list.size())
                { // starting loop: 0 < 2175
                    if (list.get(i)[0]. // employee number, ex: 10001
                            equals((employee_number)))
                    {
                        int j = 0;
                        //  Store the values of rows into arr2d
                        while (j < row)
                        { // 0 < 6
                            arr2d[count][j] = list.get(i)[j]; //set the value of each element
                            j++;
                        }
                        count++;
                    }
                    i++;
                }
            }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return arr2d;
        }


        /*
         *  this method provides a simple way to count the number of
         *  times a given employee number appears in a list.
         *  @param employee_number The input String to check for appearances in the list
         *  @param list The input List contains one-dimensional String values.
         *  @return total counts
         */


    public int countEmployeeNumberAppearance(String employee_number, List<String []> list){
        int count = 0;
        int start = 0;
        int end = list.size();

        while (start < end){
            //  Check to see if the employeenumber matches the @params employee_number.
            if (list.get(start)[0].equals(String.valueOf(employee_number))){
                count++;
            }
            start++;
        }
        return count;
    }

}

