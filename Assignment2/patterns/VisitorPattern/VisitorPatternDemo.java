public class VisitorPatternDemo {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new FileItem("a.txt"));
        Folder sub = new Folder("sub");
        sub.add(new FileItem("b.txt"));
        root.add(sub);
        PrintVisitor visitor = new PrintVisitor();
        root.accept(visitor);
    }
}
