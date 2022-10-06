package set.mathset;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Выполняет операции с множествами
 */

public class Operations {

    /**
     * Пересечение множеств
     * @param firstSet первое множество
     * @param secondSet второе множество
     * @return resultSet новое множество
     */
    public Set<Integer> intersection(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultSet = new HashSet<>(firstSet);
        if (resultSet.retainAll(secondSet)) {
            return resultSet;
        }
        return Set.of();
    }

    /**
     * Симметричная разность множеств
     * @param firstSet
     * @param secondSet
     * @return resultSet
     */
    public Set<Integer> simmetricalDifference(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultSet = new HashSet<>(firstSet);
        resultSet.addAll(secondSet);
        Set<Integer> rsl = new HashSet<>(firstSet);
        rsl.retainAll(secondSet);
        resultSet.removeAll(rsl);

        return resultSet;

    }

    /**
     * Объединение множеств
     * @param firstSet
     * @param secondSet
     * @return resultSet
     */
    public Set<Integer> union(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultSet = new HashSet<>(firstSet);
        if (resultSet.addAll(secondSet)) {
            return resultSet;
        }

        return Set.of();
    }

    /**
     * Разность множеств
     */
    public Set<Integer> subtract(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultSet = new HashSet<>(firstSet);
        if (resultSet.removeAll(secondSet)) {
            return resultSet;
        }

        return Set.of();
    }
}
