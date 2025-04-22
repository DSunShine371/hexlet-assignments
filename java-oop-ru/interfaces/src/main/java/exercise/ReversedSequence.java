package exercise;

public class ReversedSequence implements CharSequence {
    private final String reversedText;
    private final int length;

    public ReversedSequence(String text) {
        StringBuilder revText = new StringBuilder(text);
        this.reversedText = revText.reverse().toString();
        this.length = text.length();
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public char charAt(int index) {
        if ((index < 0) || index >= length()) {
            throw new IndexOutOfBoundsException();
        }
        return this.reversedText.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0) {
            throw new IndexOutOfBoundsException("start index cannot be negative");
        }
        if (end > length()) {
            throw new IndexOutOfBoundsException("end index cannot be greater than length");
        }
        if (start > end) {
            throw new IllegalArgumentException("start index cannot be greater than end index");
        }
        return this.reversedText.substring(start, end);
    }

    @Override
    public String toString() {
        return this.reversedText;
    }
}
