import java.util.ArrayList;
import java.util.Objects;

public class Session extends Countable{
    private ATM atm;
    private ArrayList<Transaction> transactions;

    public Session(ATM atm) {
        this.atm = atm;
        transactions = new ArrayList<>();
    }

    public ATM getATM() {
        return atm;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public int count() {
        return transactions.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return atm.equals(session.atm) &&
                transactions.equals(session.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atm, transactions);
    }
}
