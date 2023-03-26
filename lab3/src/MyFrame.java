//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.Map;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;
//
//public class MyFrame extends JFrame{
//    public MyFrame(ArrayList<Student> listaS, Map<String, Class> listaC){
//        super("Hello world");
//        JFrame frame = new JFrame("JFrame Object");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        JPanel mainPanel = new JPanel(new GridBagLayout());
//        JPanel panelStudents = new JPanel();
//        JPanel panelClasses = new JPanel();
//        JPanel panelButton = new JPanel();
//
//        JButton addButton = new JButton("Add");
//        JButton deleteButton = new JButton("Delete");
//        JButton changeButton = new JButton("Change");
//        JButton sortButton = new JButton("Sort");
//        panelButton.add(addButton);
//        panelButton.add(deleteButton);
//        panelButton.add(changeButton);
//        panelButton.add(sortButton);
//
//       // String[][] dataStudents = addDataS(listaS);
//        String column1[]={"IMIE","NAZWISKO","STAN","ROK_URODZENIA","ILOŚĆ_PUNKTÓW"};
//      //  DefaultTableModel tableModel = new DefaultTableModel(dataStudents, column1);
//       // JTable tableStudents = new JTable(tableModel);
//
//       // String dataClasses[][]= addDataC(listaC);
//        String column2[]={"NAZWA GRUPY", "MAX ILOŚĆ W GRUPIE"};
//        JTable tableClasses=new JTable(dataClasses,column2);
//
//        JScrollPane paneSt = new JScrollPane(tableStudents);
//        JScrollPane paneCl = new JScrollPane(tableClasses);
//        paneSt.setViewportView(tableStudents);
//        paneCl.setViewportView(tableClasses);
//
//        tableStudents.setFillsViewportHeight(true);
//        tableClasses.setFillsViewportHeight(true);
//
//        panelStudents.add(paneSt);
//        panelClasses.add(paneCl);
//
//
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(5,5,5,5);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        mainPanel.add(panelStudents, gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        mainPanel.add(panelClasses, gbc);
//        gbc.gridwidth = 2;
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        mainPanel.add(panelButton, gbc);
//
//        ListSelectionModel listSelection = tableClasses.getSelectionModel();
//        listSelection.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if(!listSelection.isSelectionEmpty()){
//                    int selectedRow = listSelection.getMinSelectionIndex();
//                    //JOptionPane.showMessageDialog(null,"Wybrano: grupa_"+(selectedRow+1) );
//                    //String newDataStudents[][] = addDataS();
//                    ArrayList<Student> listaTemp = listaC.get("grupa_"+(selectedRow+1)).listaStudentow;
//
//                    int warunek;
//                    if(listaS.size()>=listaTemp.size()){
//                        warunek = listaS.size();
//                    }else{
//                        warunek = listaTemp.size();
//                    }
//
//                    System.out.println(String.format(String.valueOf(warunek)));
//
//                    for(int i=0; i < 10; i++){
//                        if(i < listaTemp.size()){
//                            tableStudents.setValueAt(listaTemp.get(i).imie,i,0);
//                            tableStudents.setValueAt(listaTemp.get(i).nazwisko,i,1);
//                            tableStudents.setValueAt(listaTemp.get(i).stanStudenta,i,2);
//                            tableStudents.setValueAt(listaTemp.get(i).rokUrodzenia,i,3);
//                            tableStudents.setValueAt(listaTemp.get(i).ilośćPkt,i,4);
//                        }else{
//                            tableStudents.setValueAt("-",i,0);
//                            tableStudents.setValueAt("-",i,1);
//                            tableStudents.setValueAt("-",i,2);
//                            tableStudents.setValueAt("-",i,3);
//                            tableStudents.setValueAt("-",i,4);
//                        }
//                    }
//                    tableStudents.repaint();
//                }
//            }
//        });
//        deleteButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                removeSelectedRowsStud(tableStudents, tableModel);
//            }
//        });
//
//        frame.add(mainPanel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    String[][] loadDataStudents(ArrayList<Student> lista){
//        String data[][] = new String[10][5];
//        for(int i=0; i < 10; i++){
//            if(i<lista.size()){
//                data[i][0]=lista.get(i).imie;
//                data[i][1]=lista.get(i).nazwisko;
//                data[i][2]= String.valueOf(lista.get(i).stanStudenta);
//                data[i][3]= String.valueOf(lista.get(i).rokUrodzenia);
//                data[i][4]= String.valueOf(lista.get(i).ilośćPkt);
//            }else{
//                data[i][0]="-";
//                data[i][1]="-";
//                data[i][2]= "-";
//                data[i][3]= "-";
//                data[i][4]= "-";
//            }
//        }
//        return data;
//    }
//
//    String[][] loadDataClass(Map<String, Class> lista){
//        String data[][] = new String[lista.size()][2];
//        int i=lista.size()-1;
//        for (Map.Entry<String, Class> entry : lista.entrySet()) {
//            String key = entry.getKey();
//            System.out.println(String.format(String.valueOf(i)));
//            data[i][0]=lista.get(key).nazwaGrupy;
//            data[i][1]= String.valueOf(lista.get(key).maksStudentow);
//            i--;
//        }
//        return data;
//    }
//
//    void removeSelectedRowsStud(JTable table, DefaultTableModel model)
//    {
//        int[] selectedRows = table.getSelectedRows();
//
//        for(int i = 0; i < selectedRows.length ; i++)
//            model.removeRow(table.getSelectedRow());
//
//		//variablesTable.clearSelection();
//		//variablesTable.getCellEditor().stopCellEditing();
//    }
//
//    void removeSelectedRowsClass(JTable table, DefaultTableModel model)
//    {
//        int[] selectedRows = table.getSelectedRows();
//
//        for(int i = 0; i < selectedRows.length ; i++)
//            model.removeRow(table.getSelectedRow());
//
//        //variablesTable.clearSelection();
//        //variablesTable.getCellEditor().stopCellEditing();
//    }
//}
