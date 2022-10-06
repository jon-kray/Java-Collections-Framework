package set.uniquenumbers;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueTerminal {

    /**
     * Этот метод возвращает множество чисел отсортированных в обратном порядке.
     * @param numbers
     * @return
     */
    public Set<Integer> getUniqueNumbersByDesc(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new))
                .descendingSet();


    }
}
