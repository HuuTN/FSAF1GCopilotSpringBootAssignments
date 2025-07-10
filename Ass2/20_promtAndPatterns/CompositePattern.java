// Composite Pattern for Folders/Files
import java.util.*;
interface FileComponent { void show(); }
class FileLeaf implements FileComponent { private String name; public FileLeaf(String n) { name = n; } public void show() { System.out.println(name); } }
class FolderComposite implements FileComponent {
    private List<FileComponent> children = new ArrayList<>();
    public void add(FileComponent c) { children.add(c); }
    public void show() { for (FileComponent c : children) c.show(); }
}
public class CompositePattern {
    public static void main(String[] args) {
        FolderComposite root = new FolderComposite();
        root.add(new FileLeaf("file1.txt"));
        FolderComposite sub = new FolderComposite();
        sub.add(new FileLeaf("file2.txt"));
        root.add(sub);
        root.show();
    }
}
