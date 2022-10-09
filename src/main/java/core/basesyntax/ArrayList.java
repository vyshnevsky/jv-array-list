package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elements;
    private int size = 0;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, 0, size);
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, 0, size - 1);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, 0, size - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, 0, size - 1);
        T removedValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newSize = (int) (elements.length * 1.5);
        Object[] expandedArray = new Object[newSize];
        System.arraycopy(elements, 0,expandedArray, 0, elements.length);
        elements = expandedArray;
    }

    private void checkIndex(int index, int lowerBound, int upperBound) {
        if (index < lowerBound || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Index not valid: " + index
                    + ", for lower bound: " + lowerBound + ", and upper bound: " + upperBound);
        }
    }
}
