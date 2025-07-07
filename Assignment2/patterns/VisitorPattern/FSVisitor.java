public interface FSVisitor {
    void visit(FileItem f);
    void visit(Folder f);
}
