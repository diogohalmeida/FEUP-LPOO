public class Deposit extends Transaction{
    private ATM atm;
    private Session session;
    private Card card;
    private double amount;


    public Deposit(ATM atm, Session session, Card card, double amount) {
        this.atm = atm;
        this.session = session;
        this.card = card;
        this.amount = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US,"Deposit at ATM " + atm.toString() + " of %.2f", amount);
    }
}
