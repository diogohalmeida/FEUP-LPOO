public class Attendee extends Person{
    private boolean hasPaid;

    public Attendee(String name) {
        super(name);
        this.hasPaid = false;
    }


    public Attendee(String name, int age) {
        super(name, age);
        this.hasPaid = false;
    }

    public boolean hasPaid() {
        return this.hasPaid;
    }

    @Override
    public String toString() {
        if (hasPaid)
            return "Attendee " + name + " has paid its registration.";
        else
            return "Attendee " + name + " hasn't paid its registration.";
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
