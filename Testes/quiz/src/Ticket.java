public class Ticket {
    int number;
    Concert concert;

    public Ticket(int number, Concert concert) throws InvalidTicket {
        if (number > 0)
            this.number = number;
        else
            throw new InvalidTicket(number);
        this.concert = concert;
    }

    public Concert getConcert() {
        return concert;
    }

    public int getNumber() {
        return number;
    }
}
