import javax.swing.*;
import java.util.ArrayList;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createGUI();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    static ClassContainer makeData() throws IllegalAccessException {
        ClassContainer listaKlas = new ClassContainer();
        listaKlas.addClass("grupa_3", 5);
        listaKlas.addClass("grupa_2", 6);
        listaKlas.addClass("grupa_1", 8);

        Student s1 = new Student("Hubert", "Dabrowski", StudentCondition.obecny, 2000, 90.1);
        Student s2 = new Student("Leo", "Messi", StudentCondition.obecny, 2000, 82.9);
        Student s3 = new Student("Robert", "Lewandowski", StudentCondition.chory, 1999, 68.5);
        Student s4 = new Student("Cristiano", "Ronaldo", StudentCondition.nieobecny, 1985, 29.5);
        Student s5 = new Student("Ronaldinho", "Gaucho", StudentCondition.odrabiający, 1993, 59.2);
        Student s6 = new Student("Piotr", "Kowalski", StudentCondition.odrabiający, 1988, 43.7);
        Student s7 = new Student("Adam", "Małysz", StudentCondition.odrabiający, 1989, 75.7);
        Student s8 = new Student("Mariusz", "Pudzianowski", StudentCondition.odrabiający, 1998, 31.9);
        Student s9 = new Student("Ryszard", "Szatkowski", StudentCondition.odrabiający, 2001, 66.3);
        Student s10 = new Student("Dariusz", "Sienkiewicz", StudentCondition.odrabiający, 1994, 21.3);


        listaKlas.grupy.get("grupa_3").addStudent(s3);
        listaKlas.grupy.get("grupa_3").addStudent(s2);
        listaKlas.grupy.get("grupa_3").addStudent(s1);
        listaKlas.grupy.get("grupa_3").addStudent(s4);
        listaKlas.grupy.get("grupa_3").addStudent(s5);
        listaKlas.grupy.get("grupa_2").addStudent(s6);
        listaKlas.grupy.get("grupa_2").addStudent(s7);
        listaKlas.grupy.get("grupa_2").addStudent(s8);
        listaKlas.grupy.get("grupa_1").addStudent(s9);
        listaKlas.grupy.get("grupa_1").addStudent(s10);

        return listaKlas;
    }

    private static void createGUI() throws IllegalAccessException {
        ObslugaStudenta ui = new ObslugaStudenta();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}