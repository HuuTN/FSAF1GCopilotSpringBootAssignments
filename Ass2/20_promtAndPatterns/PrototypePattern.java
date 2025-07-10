// Prototype Pattern for Book
class Author {
    public String name;
    public Author(String n) { name = n; }
}
public class PrototypePattern implements Cloneable {
    public String title;
    public Author author;
    public PrototypePattern(String title, Author author) {
        this.title = title;
        this.author = author;
    }
    public PrototypePattern clone() throws CloneNotSupportedException {
        return (PrototypePattern) super.clone(); // shallow copy
    }
    public PrototypePattern deepClone() {
        return new PrototypePattern(this.title, new Author(this.author.name));
    }
}
