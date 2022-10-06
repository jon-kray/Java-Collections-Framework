package set.uniquenumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueTerminalTest {

    @Test
    public void uniqueNumbers() {

        int[] numbers = {5, -3, 12, 10, 83, -1, 0, 8, 53, 6, 17, -33};

        UniqueTerminal uniqueTerminal = new UniqueTerminal();

        Set<Integer> result = uniqueTerminal.getUniqueNumbersByDesc(numbers);
        LinkedHashSet<Integer> expected = new LinkedHashSet<>(List.of(83, 53, 17, 12, 10, 8, 6, 5, 0, -1, -3, -33));

        Assertions.assertIterableEquals(result, expected);
    }
}
