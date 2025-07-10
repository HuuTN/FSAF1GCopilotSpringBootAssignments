package patterns.behavioral.strategy;

import java.util.Arrays;

public class StrategyDemo {
    public static void main(String[] args) {
        int[] data = {5, 2, 9, 1, 5, 6};
        SortContext context = new SortContext();
        context.setStrategy(new QuickSortStrategy());
        int[] quickSorted = data.clone();
        context.sortArray(quickSorted);
        System.out.println("QuickSort: " + Arrays.toString(quickSorted));

        context.setStrategy(new MergeSortStrategy());
        int[] mergeSorted = data.clone();
        context.sortArray(mergeSorted);
        System.out.println("MergeSort: " + Arrays.toString(mergeSorted));
    }
}
