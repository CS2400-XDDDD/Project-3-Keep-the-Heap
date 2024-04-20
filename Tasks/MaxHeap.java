package Tasks;

import java.util.Arrays;

public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{
    private T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    private int swapCount;

    public MaxHeap()
    {
        this(DEFAULT_CAPACITY);
    }
    public MaxHeap(int initialCapacity)
    {
        swapCount = 0;
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    public T getMax()
    {
        checkInitialization();
        T root = null;
        if (!isEmpty())
            root = heap[1];
        return root;
    }

    public boolean isEmpty()
    {
        return lastIndex < 1;
    }

    public int getSize()
    {
        return lastIndex;
    }

    public void clear()
    {
        checkInitialization();
        while (lastIndex > -1)
        {
            heap[lastIndex] = null;
            lastIndex--;

        }
        lastIndex = 0;
    }

    public void add(T newEntry)
    {
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }

    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            }
            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
                swapCount++;
            }
            else
                done = true;
        }
        heap[rootIndex] = orphan;
    }

    public T removeMax()
    {
        checkInitialization();
        T root = null;
        if (!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }
    
    private void ensureCapacity()
    {
        if (lastIndex >= heap.length - 1)  // Check if array is full
        {
            int newCapacity = 2 * heap.length;
            checkCapacity(newCapacity);  // Ensure the new capacity does not exceed maximum
            heap = Arrays.copyOf(heap, newCapacity);
        }
    }

    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("Attempted to create a heap larger than the maximum allowed capacity.");
        }
    }

    private void checkInitialization()
    {
        if (!initialized)
        {
            throw new IllegalStateException("MaxHeap not initialized.");
        }
    }

    public MaxHeap(T[] entries)
    {
        this(entries.length);
        lastIndex = entries.length;

        for (int index = 0; index < entries.length; index++)
            heap[index + 1] = entries[index];

        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);
    }

    public int getSwapCount() {
        return swapCount;
    }

    public T getElement(int index) {
        if (index < 1 || index > lastIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return heap[index];
    }

}
