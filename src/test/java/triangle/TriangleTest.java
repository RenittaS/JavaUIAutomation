package triangle;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import triangle.utils.TimingExtention;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import java.util.stream.Stream;

//@ExtendWith(TimingExtention.class)

public class TriangleTest {

    @Test
    @DisplayName("Расчет площади треугольника")
    void getSquare1 () throws TriangleException {
    double square = Triangle.getSquare(5, 5, 5);
    double result = Math.round(square * 100.0) / 100.0;
    Assertions.assertEquals(10.83, result);
    }

    @ParameterizedTest
    @MethodSource("testParametrizedByInt")
    void testParametrizedByObject(int a, int b, int c, double result) throws TriangleException {
    double square = Triangle.getSquare(a, b, c);
    double resultTest = Math.round(square * 100.0) / 100.0;
    Assertions.assertEquals(result, resultTest);
    }

    private static Stream<Arguments> testParametrizedByInt() {
        return Stream.of(
                Arguments.of(5, 5, 5, 10.83),
                Arguments.of(3, 7, 7, 10.26),
                Arguments.of(16, 16, 16, 110.85)
        );
    }

    @Test
    void getSquare3() throws TriangleException {
        double square = Triangle.getSquare(3, 15, 10);
        double result = Math.round(square * 100.0) / 100.0;
        Assertions.assertTrue(result == 0);
    }

    @Test
    void badTriangleTest() {
        assertThatExceptionOfType(TriangleException.class).isThrownBy(
                () -> Triangle.getSquare(-1, 1, 1));
    }

}
