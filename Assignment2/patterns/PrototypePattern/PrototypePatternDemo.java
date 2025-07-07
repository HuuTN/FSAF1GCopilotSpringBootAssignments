public class PrototypePatternDemo {
    public static void main(String[] args) {
        Document doc1 = new Document("Report");
        Document doc2 = doc1.clone();
        System.out.println(doc1);
        System.out.println(doc2);
        Spreadsheet sheet1 = new Spreadsheet("Data");
        Spreadsheet sheet2 = sheet1.clone();
        System.out.println(sheet1);
        System.out.println(sheet2);
    }
}
