import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ObslugaStudenta {
    private JButton addStudentButton;
    private JButton deleteButton;
    private JButton changeButton;
    private JButton sortButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JPanel rootPanel;
    private JPanel tablePanel;
    private JPanel optionsPanel;
    private JPanel textboxPanel;
    private JButton addClassButton;
    private JPanel buttonPanel;
    private JTable table1;
    private JTable table2;
    private JScrollPane pane2;
    private JScrollPane pane1;
    private JTextField textField6;
    private JTextField textField7;

    ClassContainer listaKlas = Main.makeData();

    public ObslugaStudenta() throws IllegalAccessException {
        createTableS(listaKlas.grupy);
        createTableC(listaKlas.grupy);
        addStButton();
        addClButton();
        removeButton();
        changeButton();
        sortButton();
        changeTableData();

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTableS(Map<String, Class> listaC) {
        String[][] dataStudents = loadDataStudents(listaC.get("grupa_1").listaStudentow);
        table1.setModel(new DefaultTableModel(dataStudents, new String[]{"IMIE", "NAZWISKO", "STAN", "ROK_URODZENIA", "ILOŚĆ_PUNKTÓW"}));
    }

    private void createTableC(Map<String, Class> listaC) {
        String[][] dataClass = loadDataClass(listaC);
        table2.setModel(new DefaultTableModel(dataClass, new String[]{"NAZWA GRUPY", "MAX ILOŚĆ W GRUPIE"}));
    }

    String[][] loadDataStudents(ArrayList<Student> lista) {
        String data[][] = new String[lista.size()][5];
        for (int i = 0; i < lista.size(); i++) {
            if (i < lista.size()) {
                data[i][0] = lista.get(i).imie;
                data[i][1] = lista.get(i).nazwisko;
                data[i][2] = String.valueOf(lista.get(i).stanStudenta);
                data[i][3] = String.valueOf(lista.get(i).rokUrodzenia);
                data[i][4] = String.valueOf(lista.get(i).ilośćPkt);
            }
        }
        return data;
    }

    String[][] loadDataClass(Map<String, Class> lista) {
        String data[][] = new String[lista.size()][2];
        int i = 0;
        Iterator<Map.Entry<String, Class>> it = lista.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Class> pair = it.next();
            String key = pair.getKey();
            System.out.println(String.format(String.valueOf(i)));
            data[i][0] = lista.get(key).nazwaGrupy;
            data[i][1] = String.valueOf(lista.get(key).maksStudentow);
            i++;
        }
//
//        for (Map.Entry<String, Class> entry : lista.entrySet()) {
//            String key = entry.getKey();
//            System.out.println(String.format(String.valueOf(i)));
//            data[i][0] = lista.get(key).nazwaGrupy;
//            data[i][1] = String.valueOf(lista.get(key).maksStudentow);
//            i++;
//        }
        return data;
    }

    public void changeTableData() {
        ListSelectionModel listSelection = table2.getSelectionModel();
        listSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!listSelection.isSelectionEmpty()) {
                    int selectedRow = listSelection.getMinSelectionIndex();
                    ArrayList<Student> listaTemp = listaKlas.grupy.get("grupa_" + (selectedRow + 1)).listaStudentow;

                    String[][] dataStudents = loadDataStudents(listaKlas.grupy.get("grupa_" + (selectedRow + 1)).listaStudentow);
                    table1.setModel(new DefaultTableModel(dataStudents, new String[]{"IMIE", "NAZWISKO", "STAN", "ROK_URODZENIA", "ILOŚĆ_PUNKTÓW"}));
                }
            }
        });
    }

    public void repaintStTable(){
        ListSelectionModel listSelection = table2.getSelectionModel();
        int selectedRow = listSelection.getMinSelectionIndex();

        String[][] dataStudents = loadDataStudents(listaKlas.grupy.get("grupa_" + (selectedRow + 1)).listaStudentow);
        table1.setModel(new DefaultTableModel(dataStudents, new String[]{"IMIE", "NAZWISKO", "STAN", "ROK_URODZENIA", "ILOŚĆ_PUNKTÓW"}));
    }

    public void repaintClTable(){
        ListSelectionModel listSelection = table2.getSelectionModel();
        int selectedRow = listSelection.getMinSelectionIndex();

        String[][] dataClass = loadDataClass(listaKlas.grupy);
        table2.setModel(new DefaultTableModel(dataClass, new String[]{"NAZWA GRUPY", "MAX ILOŚĆ W GRUPIE"}));
    }

    public void addStudent() throws IllegalAccessException {
        String newImie = textField1.getText();
        String newNaz = textField2.getText();
        String newStan = textField4.getText();
        String newRok = textField3.getText();
        String newPkt= textField5.getText();
        Student newStudent = new Student(newImie,newNaz,StudentCondition.valueOf(newStan),Integer.valueOf(newRok),Double.valueOf(newPkt));

        ListSelectionModel listSelection = table2.getSelectionModel();
        int selectedRow = listSelection.getMinSelectionIndex();

        listaKlas.grupy.get("grupa_" + (selectedRow + 1)).listaStudentow.add(newStudent);
    }

    public void addStButton(){
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addStudent();
                    repaintStTable();
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void addClass() throws IllegalAccessException {
        String newName = textField6.getText();
        String newAmount = textField7.getText();

        listaKlas.addClass(newName, Integer.parseInt(newAmount));
    }

    public void addClButton(){
        addClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addClass();
                    repaintClTable();
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void removeStudent() throws IllegalAccessException {
        ListSelectionModel listSelection1 = table2.getSelectionModel();
        ListSelectionModel listSelection2 = table1.getSelectionModel();

                if (!listSelection1.isSelectionEmpty()) {
                    int selectedRow1 = listSelection1.getMinSelectionIndex();
                    int selectedRow2 = listSelection2.getMinSelectionIndex();
                    //listaKlas.grupy.get("grupa_" + (selectedRow1 + 1)).listaStudentow.remove(listaKlas.grupy.get("grupa_" + (selectedRow1 + 1)).listaStudentow.get(selectedRow2));
                    listaKlas.grupy.get("grupa_" + (selectedRow1 + 1)).getStudent(listaKlas.grupy.get("grupa_" + (selectedRow1 + 1)).listaStudentow.get(selectedRow2));
                }
        }

    public void removeButton(){
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    removeStudent();
                    repaintStTable();
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void changeButton(){
        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newStan = textField4.getText();
                ListSelectionModel listSelection1 = table2.getSelectionModel();
                ListSelectionModel listSelection2 = table1.getSelectionModel();

                if (!listSelection1.isSelectionEmpty()) {
                    int selectedRow1 = listSelection1.getMinSelectionIndex();
                    int selectedRow2 = listSelection2.getMinSelectionIndex();
                    listaKlas.grupy.get("grupa_" + (selectedRow1 + 1)).changeCondition(listaKlas.grupy.get("grupa_" + (selectedRow1 + 1)).listaStudentow.get(selectedRow2), StudentCondition.valueOf(newStan));
                }
                repaintStTable();
            }
        });
    }

    public void sortButton(){
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> listaSort;
                ListSelectionModel listSelection1 = table2.getSelectionModel();
                int selectedRow1 = listSelection1.getMinSelectionIndex();
                listaKlas.grupy.get("grupa_" + (selectedRow1+1)).sortByPoints();

                repaintStTable();
            }
        });
    }
}

