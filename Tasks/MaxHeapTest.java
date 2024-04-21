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

        try (PrintWriter writer = new PrintWriter(new File("output.txt"))) {
        } catch (FileNotFoundException e) {
            System.err.println("Failed to create output file: " + e.getMessage());
        }

        //data_random.txt insertions
        testSequential("C:/Users/garre/OneDrive/Documents/CPP/CS2400/ProjectThree/Tasks/data.txt");
        testOptimal("C:/Users/garre/OneDrive/Documents/CPP/CS2400/ProjectThree/Tasks/data.txt");

        //data_sorted.txt insertions
        testSequential("C:/Users/garre/OneDrive/Documents/CPP/CS2400/ProjectThree/Tasks/data_sorted.txt");
        testOptimal("C:/Users/garre/OneDrive/Documents/CPP/CS2400/ProjectThree/Tasks/data_sorted.txt");
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

    private static void testOptimal(String AbsoluteFilePath) {
        try{
            Scanner scanner = new Scanner(new File(AbsoluteFilePath));
            List<Integer> inputList = new ArrayList<>();
            while (scanner.hasNextInt()) {
                inputList.add(scanner.nextInt());
            }
            scanner.close();

            Integer[] inputArray = inputList.toArray(new Integer[0]);

            MaxHeap<Integer> maxHeap = new MaxHeap<>(inputArray);

            PrintWriter writer = new PrintWriter(new FileWriter("C:/Users/garre/OneDrive/Documents/CPP/CS2400/ProjectThree/output.txt", true));
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
            writer.println("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testSequential(String AbsoluteFilePath) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        ArrayList<Integer> numbers = new ArrayList<>();

        // Read numbers from file
        try (Scanner scanner = new Scanner(new File(AbsoluteFilePath))) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            return;
        }

        // Add numbers to the heap
        for (int number : numbers) {
            maxHeap.add(number);
        }

        // Write results to output file
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true))) {
            // Write the full state of the heap after building
            writer.println("Heap built using sequential insertions: " + getHeapArray(maxHeap));

            // Write the number of swaps
            writer.println("Number of swaps in the heap creation: " + maxHeap.getSwapCount());

            // Remove the max element 10 times
            for (int i = 0; i < 10; i++) {
                maxHeap.removeMax();
            }

            // Write the full state of the heap after 10 removals
            
            writer.println("Heap after 10 removals: " + getHeapArray(maxHeap));
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getHeapArray(MaxHeap<Integer> maxHeap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if (i > 1) sb.append(",");
            sb.append(maxHeap.getElement(i));
        }
        return sb.toString();
    }
}
