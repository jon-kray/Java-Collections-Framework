package numbersbigandsmall;

import java.util.List;
import java.util.stream.Collectors;

public class ConverterNumbers {

    /**
     * Этот метод возвращает список типов чисел.
     * @param numbers
     * @return SizeNumber
     */
    public List<SizeNumber> getTypeNumbersByNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(integer -> {
                    if (integer >= 1000) {
                        return SizeNumber.BIG;
                    }else if (integer == 0) {
                        return SizeNumber.ZERO;
                    }else {
                        return SizeNumber.SMALL;
                    }
                })
                .collect(Collectors.toList());
    }
}
