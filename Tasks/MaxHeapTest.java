package Tasks;
import java.io.*;
import java.util.*;

public class MaxHeapTest {
    public static void main(String[] args) {
        testIsEmptyOnNewHeap();
        testAddAndGetSize();
        testGetMax();
        testRemoveMax();
        testClear();
        testEnsureCapacity();
        testOptimal();
    }

    private static void testIsEmptyOnNewHeap() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        assert maxHeap.isEmpty() : "Heap should be empty";
        System.out.println("Test isEmpty on new heap passed.");
    }

    private static void testAddAndGetSize() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(10);
        assert !maxHeap.isEmpty() : "Heap should not be empty";
        assert maxHeap.getSize() == 1 : "Heap size should be 1";
        System.out.println("Test add and getSize passed.");
    }

    private static void testGetMax() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(20);
        maxHeap.add(30);
        assert maxHeap.getMax().equals(30) : "Max should be 30";
        System.out.println("Test getMax passed.");
    }

    private static void testRemoveMax() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(40);
        maxHeap.add(50);
        maxHeap.add(60);
        assert maxHeap.removeMax().equals(60) : "Removed max should be 60";
        assert maxHeap.getSize() == 2 : "Size should be 2 after removal";
        assert maxHeap.getMax().equals(50) : "New max should be 50";
        System.out.println("Test removeMax passed.");
    }

    private static void testClear() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(70);
        maxHeap.add(80);
        maxHeap.clear();
        assert maxHeap.isEmpty() : "Heap should be empty after clear";
        assert maxHeap.getSize() == 0 : "Heap size should be 0 after clear";
        System.out.println("Test clear passed.");
    }

    private static void testEnsureCapacity() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 0; i < 1000; i++) {
            maxHeap.add(i);
        }
        assert maxHeap.getSize() == 1000 : "Heap should contain 1000 elements";
        System.out.println("Test ensure capacity passed.");
    }

    private static void testOptimal() {
        try{
            Scanner scanner = new Scanner(new File("data.txt"));
            List<Integer> inputList = new ArrayList<>();
            while (scanner.hasNextInt()) {
                inputList.add(scanner.nextInt());
            }
            scanner.close();

            Integer[] inputArray = inputList.toArray(new Integer[0]);

            MaxHeap<Integer> maxHeap = new MaxHeap<>(inputArray);

            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));
            writer.print("Heap built using optimal method: ");
            for (int i = 0; i < 10; i++) {
                writer.print(maxHeap.getElement(i + 1) + " ");
            }
            writer.println();
            writer.println("Number of swaps in the heap creation: " + maxHeap.getSwapCount());

            writer.print("Heap after 10 removals: ");
            for (int i = 0; i < 10; i++) {
                Integer removed = maxHeap.removeMax();
            }
            for (int i = 0; i < 10; i++) {
                writer.print(maxHeap.getElement(i + 1) + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
