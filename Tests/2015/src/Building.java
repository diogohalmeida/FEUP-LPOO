import java.util.ArrayList;

public class Building extends Facility {
    private int minFloor, maxFloor;
    private ArrayList<Room> rooms;

    public Building(String name, int minFloor, int maxFloor){
        this.name = name;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.capacity = 0;
        this.rooms = new ArrayList<>();

        if (minFloor > maxFloor){
            throw new IllegalArgumentException();
        }
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean canEnter(User user) {
        for (Room room: rooms){
            if (room.canEnter(user)){
                return true;
            }
        }
        return false;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public void addCapacity(int added){
        capacity += added;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Building(" + this.name + ")";
    }
}
