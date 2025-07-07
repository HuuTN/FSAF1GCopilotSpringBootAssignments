interface FileElement {
    void accept(FileVisitor visitor);
}

class TextFile implements FileElement {
    public void accept(FileVisitor visitor) { visitor.visit(this); }
}

class ImageFile implements FileElement {
    public void accept(FileVisitor visitor) { visitor.visit(this); }
}

interface FileVisitor {
    void visit(TextFile file);
    void visit(ImageFile file);
}

class PrintVisitor implements FileVisitor {
    public void visit(TextFile file) { System.out.println("Printing TextFile"); }
    public void visit(ImageFile file) { System.out.println("Printing ImageFile"); }
}

class SerializeVisitor implements FileVisitor {
    public void visit(TextFile file) { System.out.println("Serializing TextFile"); }
    public void visit(ImageFile file) { System.out.println("Serializing ImageFile"); }
}

class CompressVisitor implements FileVisitor {
    public void visit(TextFile file) { System.out.println("Compressing TextFile"); }
    public void visit(ImageFile file) { System.out.println("Compressing ImageFile"); }
}
