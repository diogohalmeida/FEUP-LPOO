public abstract class Facility {
    protected String name;
    protected int capacity;

    public abstract String getName();

    public int getCapacity() {
        return capacity;
    }

    public abstract boolean canEnter(User user);
}
