package pro.sky.algorithms;

import pro.sky.algorithms.exception.ElementNotFoundException;
import pro.sky.algorithms.exception.InvalidIndexException;
import pro.sky.algorithms.exception.NullItemsException;
import pro.sky.algorithms.exception.StorageIsFullException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] storage;
    private int size;

    public StringListImpl(String[] storage) {
        this.storage = storage;
    }

    public StringListImpl() {
        storage = new String[10];
    }

    public StringListImpl(int initSize) {
        storage = new String[initSize];
    }

    private void validateItem(String Items) {
        if (item == null) {
            throw new NullItemsException();
        }
    }

    public void validateSize() {
        if (item == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException();
        }
    }



    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        storage [size++]= item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateItem(item);

        if (index==size) {
            storage [size++]= item;
        }
        return item;

        System.arraycopy(storage, index, storage, index+1, size-index);

        storage[index]=item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        storage[index]=item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Element " + item + " is not present in the list");
        }
        if(index != size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateItem(index);
        String item = storage[index];
        if(index != size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (storage(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}