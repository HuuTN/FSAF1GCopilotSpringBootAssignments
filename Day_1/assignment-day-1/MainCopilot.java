// Code tối ưu bởi Copilot
import java.util.*;

class Student {
    private String id, name;
    private double score;
    public Student(String id, String name, double score) {
        this.id = id; this.name = name; this.score = score;
    }
    public String toString() {
        return id + " " + name + " " + score;
    }
}

public class MainCopilot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        while (true) {
            System.out.println("1. Thêm sinh viên\n2. Hiển thị\n3. Thoát");
            int c;
            try { c = Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { System.out.println("Sai kiểu!"); continue; }
            switch (c) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    double score;
                    try { 
                        System.out.print("Điểm: ");
                        score = Double.parseDouble(sc.nextLine());
                    } catch (Exception e) { System.out.println("Sai kiểu!"); break; }
                    students.add(new Student(id, name, score));
                }
                case 2 -> students.forEach(System.out::println);
                case 3 -> { return; }
                default -> System.out.println("Chọn lại!");
            }
        }
    }
}