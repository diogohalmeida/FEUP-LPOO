import java.util.Objects;

public abstract class Node {
    protected String name;
    protected Folder parent;
    protected static int number = 0;
    protected int size;

    public Node(Folder parent, String name) throws DuplicateNameException {
        this.parent = parent;
        this.name = name;
        number++;

        if (parent != null)
            parent.addChild(this);


    }

    public static void resetNumbering(int i) {
        number = i;
    }


    public Folder getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public static int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public abstract String getPath();


    public abstract void move(Folder parent, String name) throws CycleException;
}
