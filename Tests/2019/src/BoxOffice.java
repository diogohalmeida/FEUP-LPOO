import java.util.ArrayList;

public class BoxOffice {
    public static ArrayList<Ticket> buy(Concert concert, int quant) throws InvalidTicket {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < quant; i++){
            tickets.add(new Ticket(concert.getNumber(), concert));
            concert.incrementNumber();
        }

        return tickets;
    }
}
