package Tasks;

public class Element implements Comparable<Element> {
    private int value;

    public Element(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Element other) {
        // Compare based on 'value' field
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
