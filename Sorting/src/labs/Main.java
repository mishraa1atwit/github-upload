package labs;

import java.io.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final String FILENAME = "InnocentsAbroad.txt";

    public static void main(String[] args) {
        final DoublyLinkedList<String> list = new DoublyLinkedList<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            reader.lines()
                    .flatMap(it -> Arrays.stream(it.trim().split(" ")))
                    .map(String::trim)
                    .filter(it -> !it.equals(""))
                    .collect(Collectors.toSet())
                    .forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeToFile("Insertion", list.clone(), QuadraticSorting::insertionSort);
        writeToFile("Selection", list.clone(), QuadraticSorting::selectionSort);
    }

    private static void writeToFile(
            String prefix,
            DoublyLinkedList<String> list,
            Consumer<DoublyLinkedList<String>> algorithm
    ) {
        algorithm.accept(list);
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(prefix + "Vocab" + FILENAME))) {
            for (String word : list) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
