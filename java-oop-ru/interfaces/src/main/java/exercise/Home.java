package exercise;

public interface Home {
    double getArea();
    default int compareTo(Home home) {
        return Double.compare(this.getArea(), home.getArea());
    }
}
