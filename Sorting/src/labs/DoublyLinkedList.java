package labs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T>, Cloneable {
    private DNode<T> head;
    private DNode<T> tail;
    private int size;

    public DoublyLinkedList() { // creates an empty list
        head = tail = null;
        size = 0;
    }

    public DoublyLinkedList(T[] items) {
        this();
        addAll(items);
    }

    // In general, the linked-list class should hav more methods.
    // Particularly, the methods delete (T item), contains (T item),
    // delete (int index), add (int index, T item), getFrequencyOf (T item).
    // For the purpose od testing the sorting functionality, only the methods
    // that are listed here are needed. You may volunteer to add other
    // methods as well.

    public int getSize() {
        return size;
    }

    protected DNode<T> getHead() {
        return head;
    }

    protected DNode<T> getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T item) {
        if (head == null) {
            head = new DNode<>(item);
        } else {
            if (tail == null) {
                tail = new DNode<>(item);
                head.setNext(tail);
                tail.setPrev(head);
            } else {
                DNode<T> newNode = new DNode<>(item);
                tail.setNext(newNode);
                newNode.setPrev(tail);
                tail = newNode;
            }
        }

        size++;
    }

    public void addAll(T[] items) {
        for (T t : items) add(t);
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public T[] toArray(T[] a) {
        T[] arr = Arrays.copyOf(a, size);
        int i = 0;
        for (DNode<T> node = head; node != null; node = node.getNext(), i++) {
            arr[i] = node.getData();
        }

        return arr;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray((T[]) new Object[0]));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            DNode<T> node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                DNode<T> curr = node;
                node = node.getNext();
                return curr.getData();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        Iterable.super.forEach(consumer);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public DoublyLinkedList<T> clone() {
        DoublyLinkedList<T> clone = new DoublyLinkedList<T>();
        forEach(clone::add);
        return clone;
    }
}
