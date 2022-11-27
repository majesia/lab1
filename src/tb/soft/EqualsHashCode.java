package tb.soft;

import java.util.Objects;

public class EqualsHashCode extends Person{

    public EqualsHashCode(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof Person){
            Person object =(Person) o;
            if(this.getFirstName().equals(((Person) o).getFirstName()) && this.getLastName().equals(((Person) o).getLastName()) && this.getBirthYear() == ((Person) o).getBirthYear()) return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
