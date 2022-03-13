package triangle;

//1. Написать функцию, вычисляющую площадь треугольника по трём сторонам (int a, int b, int c). Разместить класс с
// функцией в src/main/java.
//2. Разместить тесты на эту функцию в классе src/test/java/.../TriangleTest.java.
//3. Настроить генерацию отчёта и логирование.

public class Triangle  {
    public static double getSquare (int a, int b, int c) throws TriangleException {
        if (a <= 0 || b <= 0 || c <= 0) throw new TriangleException();
        double p = (a + b + c) / 2.0;
        double square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return square;
    }
}
