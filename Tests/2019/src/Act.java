import java.util.Objects;

public abstract class Act {
    protected String name, country;

    public Act(String name, String country){
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Act act = (Act) o;
        return name.equals(act.name) &&
                country.equals(act.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
