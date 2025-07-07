public class PrintVisitor implements FSVisitor {
    public void visit(FileItem f) { System.out.println("File: " + f.getName()); }
    public void visit(Folder f) { System.out.println("Folder: " + f.getName()); }
}
