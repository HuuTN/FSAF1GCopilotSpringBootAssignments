public class FileItem implements FSItem {
    private String name;
    public FileItem(String name) { this.name = name; }
    public void accept(FSVisitor v) { v.visit(this); }
    public String getName() { return name; }
}
