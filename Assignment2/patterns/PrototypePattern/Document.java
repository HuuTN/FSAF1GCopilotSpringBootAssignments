public class Document implements Prototype<Document> {
    private String content;
    public Document(String content) { this.content = content; }
    public Document clone() { return new Document(this.content); }
    public String toString() { return "Document: " + content; }
}
