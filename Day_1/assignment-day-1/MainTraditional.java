// Code thông thường
import java.util.*;

public class MainTraditional {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ids = new String[100];
        String[] names = new String[100];
        double[] scores = new double[100];
        int n = 0;
        while (true) {
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiển thị");
            System.out.println("3. Thoát");
            int c = sc.nextInt(); sc.nextLine();
            if (c == 1) {
                System.out.print("ID: ");
                ids[n] = sc.nextLine();
                System.out.print("Tên: ");
                names[n] = sc.nextLine();
                System.out.print("Điểm: ");
                scores[n] = sc.nextDouble(); sc.nextLine();
                n++;
            } else if (c == 2) {
                for (int i = 0; i < n; i++) {
                    System.out.println(ids[i] + " " + names[i] + " " + scores[i]);
                }
            } else break;
        }
    }
}