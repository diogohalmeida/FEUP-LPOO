import java.util.ArrayList;
import java.util.Objects;

public class Room extends Facility {
    private Building building;
    private int floor;
    private String number;
    private ArrayList<User> authorized;

    public Room(Building building, String number, int floor) throws DuplicateRoomException {
        this.building = building;
        this.number = number;
        this.floor = floor;
        this.name = building.getName() + number;
        this.capacity = 0;
        this.authorized = new ArrayList<>();

        for (Room room: building.getRooms()){
            if (room.equals(this)){
                throw new DuplicateRoomException();
            }
        }


        this.building.addRoom(this);



        if (floor > building.getMaxFloor()){
            throw new IllegalArgumentException();
        }
    }

    public Room(Building building, String number, int floor, int capacity) throws DuplicateRoomException {
        this.building = building;
        this.number = number;
        this.floor = floor;
        this.name = building.getName() + number;
        this.capacity = capacity;
        this.authorized = new ArrayList<>();

        for (Room room: building.getRooms()){
            if (room.equals(this)){
                throw new DuplicateRoomException();
            }
        }


        this.building.addCapacity(capacity);
        this.building.addRoom(this);



        if (floor > building.getMaxFloor()){
            throw new IllegalArgumentException();
        }
    }

    public Building getBuilding() {
        return building;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return "Room(" + this.getBuilding().getName() + "," + this.getNumber() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return floor == room.floor &&
                building.equals(room.building) &&
                number.equals(room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(building, floor, number);
    }

    public void authorize(User user) {
        authorized.add(user);
    }

    public boolean canEnter(User user) {
        for (User u: authorized){
            if (u.equals(user)){
                return true;
            }
        }
        return false;
    }
}
