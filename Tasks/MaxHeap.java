import java.util.Arrays;
public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{
    private T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap()
    {
        this(DEFAULT_CAPACITY);
    }
    public MaxHeap(int initialCapacity)
    {
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
}

