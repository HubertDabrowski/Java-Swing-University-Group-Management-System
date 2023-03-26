public class Student implements Comparable<Student>{
    String imie;
    String nazwisko;
    StudentCondition stanStudenta;
    int rokUrodzenia;
    double ilośćPkt;

    public Student(String name, String surname, StudentCondition stan, int rok, double punkty) throws IllegalAccessException {
        this.imie = name;
        this.nazwisko = surname;
        this.stanStudenta = stan;
        this.rokUrodzenia = rok;
        this.ilośćPkt = punkty;
    }

    String getNazwisko(){
        return nazwisko;
    }

    double getPkt(){
        return ilośćPkt;
    }

    public void print(){
        System.out.println(String.format(""));
        System.out.println(String.format("imie: %s", imie));
        System.out.println(String.format("nazwisko: %s", nazwisko));
        System.out.println(String.format("stan studenta: %s", stanStudenta));
        System.out.println(String.format("rok urodzenia studenta: %d", rokUrodzenia));
        System.out.println(String.format("ilość punktów: %.2f", ilośćPkt));
        System.out.println(String.format(""));
    }

    @Override
    public int compareTo(Student st) {
        if(nazwisko == st.nazwisko){
            return 0;
        }
        else return 1;
    }

}



