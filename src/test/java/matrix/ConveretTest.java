package matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConveretTest {

    private final Converter converter = new Converter();

    @Test
    public void convertMatrix() {
        int [][] matrix = {{3, 4, 5}, {6, 6, 7}, {8, 9, 11}};

        List<Integer> result = converter.convertToList(matrix);
        List<Integer> expected = List.of(3, 4, 5, 6, 6, 7, 8, 9, 11);

        Assertions.assertIterableEquals(result, expected);
    }
}
