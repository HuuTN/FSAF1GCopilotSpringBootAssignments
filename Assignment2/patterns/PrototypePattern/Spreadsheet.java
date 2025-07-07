public class Spreadsheet implements Prototype<Spreadsheet> {
    private String data;
    public Spreadsheet(String data) { this.data = data; }
    public Spreadsheet clone() { return new Spreadsheet(this.data); }
    public String toString() { return "Spreadsheet: " + data; }
}
