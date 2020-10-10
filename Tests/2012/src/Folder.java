import java.util.ArrayList;
import java.util.Objects;

public class Folder extends Node{
    private ArrayList<Node> child;


    public Folder(Folder parent, String name) throws DuplicateNameException {
        super(parent, name);
        child = new ArrayList<>();
    }

    public Folder(Folder folder, String name, Folder parent) throws DuplicateNameException {
        super(parent,name);
        child = new ArrayList<>();
    }

    public Folder getParent() {
        return parent;
    }

    public void addChild(Node node) throws DuplicateNameException {
        for (Node toAdd: child){
            if (node.getName().equals(toAdd.getName())){
                throw new DuplicateNameException();
            }
        }
        child.add(node);
    }

    public Node[] getChild() {
        Node[] nodes = new Node[child.size()];

        int i = 0;
        for (Node node: child){
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public ArrayList<Node> getChildArray(){
        return child;
    }

    public void setChild(ArrayList<Node> child) {
        this.child = child;
    }

    public Node getChildByName(String name) {
        for (Node node: child){
            if (node.getName() == name){
                return node;
            }
        }

        return null;
    }

    @Override
    public int getSize() {
        int result = 0;
        for (Node node: child){
            result += node.getSize();
        }
        return result;
    }

    @Override
    public String getPath() {
        if (parent.getName() == "root"){
            return FileSystem.getNameFormatter().getSeparator() + name;
        }
        return parent.getPath() + FileSystem.getNameFormatter().getSeparator() + name;
    }

    @Override
    public void move(Folder parent, String name) throws CycleException {
        for (Node node: child){
            if (parent.equals(node)){
                throw new CycleException();
            }
        }
        this.setParent(parent);
        this.setName(name);
    }

    public Folder clone(Folder parent, String name) throws DuplicateNameException {
        Folder folder = new Folder(this, name, parent);
        ArrayList<Node> childsAux = new ArrayList<>();

        for (Node node: this.getChild()){
            if (node instanceof File){
                File toCopy = (File) node;
                childsAux.add(new File(folder,toCopy.getName(),toCopy.getSize()));
            }
            else{
                childsAux.add(((Folder) node).clone(folder, node.getName()));
            }
        }

        folder.setChild(childsAux);
        return folder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(child, folder.child) && name.equals(folder.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(child);
    }
}
