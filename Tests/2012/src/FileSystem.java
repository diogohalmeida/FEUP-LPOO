import java.util.ArrayList;

public class FileSystem {
    private String type;
    private Folder root;
    private ArrayList<Node> nodes;
    private static NameFormatter nameFormatter;

    public FileSystem(String type) {
        this.type = type;
        try {
            this.root = new Folder(null, "root");
        } catch (DuplicateNameException e) {
            e.printStackTrace();
        }
        this.nodes = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public Folder getRoot() {
        return root;
    }

    public static NameFormatter getNameFormatter() {
        return nameFormatter;
    }

    public void setNameFormatter(NameFormatter nameFormatter) {
        this.nameFormatter = nameFormatter;
    }
}
