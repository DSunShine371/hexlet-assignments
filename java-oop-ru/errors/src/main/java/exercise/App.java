package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        try {
            int square = (int) Math.round(circle.getSquare());
            System.out.println(square);
        } catch (Exception e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
