import java.util.Objects;

public class Speaker extends Person {
    private int fee;

    public Speaker(String name, int age) {
        super(name, age);
        this.fee = 0;
    }

    public Speaker(String name) {
        super(name);
        this.fee = 0;
    }

    public int getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return "Speaker " + name + " has a fee value of " + fee + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Speaker speaker = (Speaker) o;
        return fee == speaker.fee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fee);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
