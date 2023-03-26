import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Class{
    String nazwaGrupy;
    int maksStudentow;
    ArrayList<Student> listaStudentow;

    public Class(String nazwa, int maks) throws IllegalAccessException {
       nazwaGrupy = nazwa;
       maksStudentow = maks;
       listaStudentow = new ArrayList<Student>();
    }

    void addStudent(Student st){
        boolean czyJest=false;
        if(listaStudentow.size() == 0){
            listaStudentow.add(st);
            System.out.println("Student dodany do grupy!");
        }
        else if(listaStudentow.size() < maksStudentow ) {
            int i = 0;
            while(i < listaStudentow.size()){
                Student tempStud = listaStudentow.get(i);
                if(tempStud.imie == st.imie){
                    System.out.println("Student jest już w grupie!");
                    czyJest = true;
                }
                i++;
            }
            if(czyJest != true){
                listaStudentow.add(st);
                System.out.println("Student dodany do grupy!");
            }
        }else {
            System.err.println("W grupie jest już maksymalna ilość studentów");
        }
    }


    void addPoints(Student st, double pkt){
        st.ilośćPkt+=pkt;
        System.out.println("liczba punktów po dodaniu: "+ st.ilośćPkt);
    }

    void getStudent(Student st){
        int index = listaStudentow.indexOf(st);
        listaStudentow.remove(index);
        if(st.ilośćPkt == 0.0){
            listaStudentow.remove(index);
            st = null;
            System.out.println("Student usunięty, liczba punktów równa zero!");
        }
    }

    void changeCondition(Student st, StudentCondition newCond) {
        st.stanStudenta = newCond;
        System.out.println("Nowy Stan: " + st.stanStudenta);
    }

    void removePoints(Student st, double pkt) {
        st.ilośćPkt -= pkt;
        System.out.println("Liczba punktów po usunięciu: " + st.ilośćPkt);
    }

    Student search(String nazwisko) throws IllegalAccessException {
        int i=0;
        Student studentToCompare = new Student("x", nazwisko, StudentCondition.obecny, 0, 0);
        while(i < listaStudentow.size()) {
            Student tempStud = listaStudentow.get(i);
            if (studentToCompare.compareTo(tempStud) == 0) {
                System.out.println("Znaleziono studenta o podanym nazwisku");
                return tempStud;
            }
            i++;
        }
        System.out.println("Nie znaleziono studenta o podanym nazwisku");
        return null;
    }

    ArrayList<Student> searchPartial(String czescNazwiska){
        int i=0;
        ArrayList<Student> znalezionaLista = new ArrayList<Student>();
        while(i < listaStudentow.size()) {
            Student tempStud = listaStudentow.get(i);
            if (tempStud.nazwisko.contains(czescNazwiska)) {
                znalezionaLista.add(tempStud);
            }
            i++;
        }
        return znalezionaLista;
    }

    int countByCondition(StudentCondition Cond){
        int i=0;
        int counter = 0;
        while(i< listaStudentow.size()){
            if(listaStudentow.get(i).stanStudenta == Cond){
                counter++;
            }
            i++;
        }
        return counter;
    }

    void summary(){
        int i=0;
        System.out.println("INFORMACJA O WSZYTSKICH STUDENTACH W GRUPIE: ");
        while(i<listaStudentow.size()){
            listaStudentow.get(i).print();
            i++;
        }
    }

    public static void sortNaz(ArrayList<Student> list) {

        list.sort(Comparator.comparing(Student::getNazwisko));
    }

    ArrayList<Student> sortByName(){
         sortNaz(listaStudentow);
         return listaStudentow;
     }

    ArrayList<Student> sortByPoints(){
        Collections.sort(listaStudentow, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o2.getPkt() - o1.getPkt());
            }
        });
        return listaStudentow;
    }

}
