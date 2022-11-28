package tb.soft;

import java.util.*;

public class Collections {
    public static int numbers=0;
    static Set<Person> hashSetPerson = new HashSet<>();
    static Set<Person> treeSetPerson = new TreeSet<>();
    static List<Person> arrayListPerson = new ArrayList<>();
    static List<Person> linkedListPerson = new LinkedList<>();
    static Map<Integer,Person>  hashMapPerson = new HashMap<>();
    static Map<Integer, Person> treeMapPerson = new TreeMap<>();

    public static void addPeople(Person currentPerson) {

        if (currentPerson != null) {
            numbers++;
            arrayListPerson.add(currentPerson);
            linkedListPerson.add(currentPerson);
            treeSetPerson.add(currentPerson);
            hashSetPerson.add(currentPerson);
            hashMapPerson.put(numbers,currentPerson);
            treeMapPerson.put(numbers,currentPerson);

        }
    }
}