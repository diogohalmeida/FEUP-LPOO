public class InvalidTicket extends Throwable {
    private int number;

    public InvalidTicket(int number){
        this.number = number;
    }
}
