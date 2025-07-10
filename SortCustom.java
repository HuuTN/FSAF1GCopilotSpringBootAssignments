public class SortCustom {
    public static void sortArray(int[] arr) {
    java.util.Arrays.sort(arr);
}
    // Hàm sortArray sẽ sắp xếp mảng arr theo thứ tự tăng dần
    // Ví dụ: nếu arr là {5, 2, 9, 1, 3}, sau khi gọi hàm này,
    // mảng arr sẽ trở thành {1, 2, 3, 5, 9}
    // Bạn có thể gọi hàm này trong phương thức main để kiểm tra kết quả

    // Ví dụ sử dụng:
    // int[] numbers = {5, 2, 9, 1, 3};
    // sortArray(numbers);
// Sau khi gọi hàm, mảng numbers sẽ là {1, 2, 3, 5, 9}

    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 3};
        sortArray(numbers);
        System.out.println(java.util.Arrays.toString(numbers));
    }
}
