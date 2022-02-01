package labs;

import java.util.function.Consumer;

public class QuadraticSorting {
    public static <T extends Comparable<? super T>> void insertionSort(T[] list) {
        for (int j = 1; j < list.length; j++) {
            T key = list[j];
            int i = j - 1;
            while (i >= 0 && list[i].compareTo(key) > 0) {
                list[i + 1] = list[i];
                i--;
            }
            list[i + 1] = key;
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(DoublyLinkedList<T> list) {
        sortWrapper(list, QuadraticSorting::insertionSort);
    }

    public static <T extends Comparable<? super T>> void selectionSort(T[] list) {
        for (int j = 0; j < list.length - 1; j++) {
            T key = list[j];
            int index = j;
            for (int i = j + 1; i < list.length; i++) {
                if (list[i].compareTo(key) < 0) {
                    key = list[i];
                    index = i;
                }
            }

            T tmp = list[j];
            list[j] = list[index];
            list[index] = tmp;
        }
    }

    public static <T extends Comparable<? super T>> void selectionSort(DoublyLinkedList<T> list) {
        sortWrapper(list, QuadraticSorting::selectionSort);
    }

    private static <T extends Comparable<? super T>> void sortWrapper(DoublyLinkedList<T> list, Consumer<T[]> algorithm) {
        T[] arr = list.toArray((T[]) new Comparable[0]);
        algorithm.accept(arr);
        list.clear();
        list.addAll(arr);
    }
}
