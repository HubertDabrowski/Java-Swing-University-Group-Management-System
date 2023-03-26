import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassContainer {
    Map<String, Class> grupy = new HashMap<String,Class>();

    void addClass(String nazwa, int pojemnosc) throws IllegalAccessException {
        System.out.println(String.format("Dodano grupę"));
        grupy.put(nazwa, new Class(nazwa, pojemnosc));
    }

    void removeClass(String nazwa){
        System.out.println(String.format("Usunięto grupę"));
        grupy.remove(nazwa);
    }

    ArrayList<Class> findEmpty(){
        ArrayList<Class> pusteGrupy = new ArrayList<Class>();
        for (Map.Entry<String, Class> entry : grupy.entrySet()) {
            String key = entry.getKey();
            Class value = entry.getValue();
            if(value.listaStudentow.size() == 0){
                pusteGrupy.add(value);
                System.out.println(String.format("Nazwa pustej grupy: " + key));
            }
        }
        return pusteGrupy;
    }

    void summary(){
        for (Map.Entry<String, Class> entry : grupy.entrySet()) {
            String key = entry.getKey();
            Class nazwa = entry.getValue();
            System.out.println(String.format("Nazwa grupy: " + key + " procentowe zapełnienie: " + Math.round(((double)nazwa.listaStudentow.size()/ (double)nazwa.maksStudentow)*100) + " procent"));
        }
    }
}
