// Strategy Pattern for Sorting Algorithms
interface SortStrategy { void sort(int[] arr); }
class BubbleSort implements SortStrategy { public void sort(int[] arr) { /*...*/ } }
class QuickSort implements SortStrategy { public void sort(int[] arr) { /*...*/ } }
class MergeSort implements SortStrategy { public void sort(int[] arr) { /*...*/ } }
class Sorter {
    private SortStrategy strategy;
    public void setStrategy(SortStrategy s) { strategy = s; }
    public void sort(int[] arr) { strategy.sort(arr); }
}
public class StrategyPattern {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        sorter.setStrategy(new BubbleSort());
        sorter.sort(new int[]{1,2,3});
    }
}
