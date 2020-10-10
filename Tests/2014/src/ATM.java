import java.util.Objects;

public class ATM {
    private int id;
    private String location, bank;


    public ATM(int id, String location, String bank) {
        this.id = id;
        this.location = location;
        this.bank = bank;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return this.id + " (" + this.location + ", " +  this.bank + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return id == atm.id &&
                location.equals(atm.location) &&
                bank.equals(atm.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, bank);
    }
}
