import java.util.ArrayList;

public class File extends Node {

    public File(Folder parent, String name) throws DuplicateNameException {
        super(parent, name);
    }

    @Override
    public String getPath() {
        if (parent.getName() == "root") {
            return name;
        }
        return parent.getPath() + FileSystem.getNameFormatter().getSeparator() + name;
    }

    @Override
    public void move(Folder parent, String name) throws CycleException{
        this.setParent(parent);
        this.setName(name);
    }

    public File(Folder parent, String name, int size) throws DuplicateNameException {
        super(parent, name);
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return size == node.size &&
                name.equals(node.name);
    }
}
