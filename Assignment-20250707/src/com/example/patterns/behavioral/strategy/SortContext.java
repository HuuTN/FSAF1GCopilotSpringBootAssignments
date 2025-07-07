package patterns.behavioral.strategy;

public class SortContext {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortArray(int[] arr) {
        if (strategy == null) {
            throw new IllegalStateException("Sort strategy not set");
        }
        strategy.sort(arr);
    }

    public void sortAndPrint(int[] clone) {
        sortArray(clone);
        for (int num : clone) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
