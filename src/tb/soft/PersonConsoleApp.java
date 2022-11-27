package tb.soft;
import javax.xml.namespace.QName;
import java.awt.desktop.SystemEventListener;
import java.lang.Comparable;
import java.util.*;
/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {
	/*public Set<Person> getHashset_person() {
		return hashset_person;
	}
	public Set<Person> getTreeset_person() {
		return treeset_person;
	}

	Set<Person> hashset_person = new HashSet<>();
	Set<Person> treeset_person = new TreeSet<>();
	/*List<Person> arraylist_person = new ArrayList<>();
	List<Person> linkedlist_person = new LinkedList<>();
	Map<Integer,Person>  hashmap_person = new HashMap<>();
	Map<Integer, Person> treemap_person = new TreeMap<>();*/
	public static Collections collections = new Collections();
	public static int numbers=0;
	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";


	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń dane osoby        \n" +
			"3 - Modyfikuj dane osoby   \n" +
			"4 - Wczytaj dane z pliku   \n" +
			"5 - Zapisz dane do pliku   \n" +
			"6 - Porównanie osób \n" +
					"7 - Wypisz wszystkie dane \n" +
			"0 - Zakończ program        \n";	
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";

	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new JOptionUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;




	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					//dodawanie nowej osoby do kazdej kolekcji
					if(currentPerson != null){
						numbers++;
						collections.arraylist_person.add((Person) currentPerson);
						collections.linkedlist_person.add((Person) currentPerson);
						collections.hashset_person.add((Person) currentPerson);
						collections.treeset_person.add((Person)currentPerson);
						collections.hashmap_person.put((Integer) numbers,(Person) currentPerson);
						collections.treemap_person.put((Integer) numbers,(Person) currentPerson);;
					}
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					collections.arraylist_person.remove((Person) currentPerson);
					collections.linkedlist_person.remove((Person) currentPerson);
					collections.hashset_person.remove((Person) currentPerson);
					collections.treeset_person.remove((Person)currentPerson);
					collections.hashmap_person.remove((Integer) numbers,(Person) currentPerson);
					collections.treemap_person.remove((Integer) numbers,(Person) currentPerson);;
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					currentPerson = Person.readFromFile(file_name);
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
				}
					break;
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
				}

					break;
				//porownanie osob
				case 6: {
					int a, b,c;
					System.out.println("Podaj numery indeksów osób ktore chcesz porównać:");
					System.out.println("pierwszy:");
					Scanner scanner = new Scanner(System.in);
					a = scanner.nextInt();
					System.out.println("\ndrugi:");
					Scanner scannerb = new Scanner(System.in);
					b = scanner.nextInt();
					System.out.println("Wybierz którą metodą chcesz porównać obiekty: \n 1 - equals \n 2 - equals zdefiniowaną \n 3 - hashCode \n 4 - hashCode zdefiniowaną");
					Scanner scannerc = new Scanner(System.in);
					c = scannerc.nextInt();

					switch(c){
						case 1 : {
							System.out.println(collections.linkedlist_person.get(a).equals(collections.linkedlist_person.get(b)));
							break;
						}
						case 2 : {
							Person person1 = collections.linkedlist_person.get(a);
							String firstName = person1.getFirstName();
							String lastName = person1.getLastName();
							int year = person1.getBirthYear();
							PersonJob job = person1.getJob();
							EqualsHashCode person2 = new EqualsHashCode(firstName, lastName);
							person2.setBirthYear(year);
							person2.setJob(job);

							Person person3 = collections.linkedlist_person.get(b);
							String firstName3 = person3.getFirstName();
							String lastName3 = person3.getLastName();
							int year3 = person3.getBirthYear();
							PersonJob job3 = person3.getJob();
							EqualsHashCode person4 = new EqualsHashCode(firstName3, lastName3);
							person4.setBirthYear(year3);
							person4.setJob(job3);

							System.out.println(person2.equals(person4));
							break;
						}
						case 3: {
							System.out.println(collections.linkedlist_person.get(a).hashCode());
							break;
						}
						case 4: {
							break;
						}
					}


					break;
				}
				case 7: {
					System.out.println("Wybierz elementy jakiej kolejkcji chcialbys wyświetlić:");
					System.out.println( "1 - HashSet\n" +
							"2 - TreeSet\n" +
							"3 - ArrayList\n" +
							"4 - LinkedList\n" +
							"5 - HashMap\n" +
							"6 - TreeMap\n");
					Scanner scanner = new Scanner(System.in);
					int numberCollection =  scanner.nextInt();

					switch (numberCollection){
						case 1:{
							for (Person person1: collections.hashset_person) {
								System.out.println(person1.getFirstName() + " " +person1.getLastName() +" " +person1.getBirthYear() +" " + person1.getJob());
							}
							break;
						}
						case 2:{
							break;
						}
						case 3:{
							for (Person person1: collections.arraylist_person) {System.out.println(person1.getFirstName() + " " +person1.getLastName() +" " +person1.getBirthYear() +" " + person1.getJob());
							}
							break;
						}
						case 4:{
							for (Person person1: collections.linkedlist_person) {
								System.out.println(person1.getFirstName() + " " +person1.getLastName() +" " +person1.getBirthYear() +" " + person1.getJob());
							}
							break;
						}
							//nwm jak zrobic bo tam trzeba jeszcze kod dostepu
						case 5:{
							for (int i=0; i<collections.treemap_person.size();i++) {
								System.out.println(collections.treemap_person.get(i+1).getFirstName() + " " +collections.treemap_person.get(i+1).getLastName()
								+ " " + collections.treemap_person.get(i+1).getBirthYear() + " " + collections.treemap_person.get(i+1).getJob());
							}
							break;
						}
						case 6: {
							for (int i=0; i<collections.hashmap_person.size();i++) {
								System.out.println(collections.hashmap_person.get(i+1).getFirstName() + " " +collections.hashmap_person.get(i+1).getLastName()
										+ " " + collections.hashmap_person.get(i+1).getBirthYear() + " " + collections.hashmap_person.get(i+1).getJob());
							}
							break;
						}
					}

					}

				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) { 
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}
	
	
	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}



		return person;
	}
	
	
	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
}  // koniec klasy PersonConsoleApp
