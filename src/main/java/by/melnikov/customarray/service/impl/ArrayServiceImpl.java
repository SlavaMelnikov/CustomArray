package by.melnikov.customarray.service.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.service.ArrayService;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class ArrayServiceImpl implements ArrayService {
    private static ArrayServiceImpl instance;

    private ArrayServiceImpl() {
    }

    public static ArrayServiceImpl getInstance() {
        if (instance == null) {
            instance = new ArrayServiceImpl();
        }
        return instance;
    }
    @Override
    public int max(CustomArray array) {
        return Arrays.stream(array.getArray()).max().getAsInt();
    }

    @Override
    public int min(CustomArray array) {
        return Arrays.stream(array.getArray()).min().getAsInt();
    }

    @Override
    public double average(CustomArray array) {
        return sum(array) * 1.0 / array.getArray().length;
    }

    @Override
    public long sum(CustomArray array) {
        return Arrays.stream(array.getArray()).sum();
    }

    @Override
    public int countPositive(CustomArray array) {
        return (int) Arrays.stream(array.getArray()).filter(n -> n > 0).count();
    }

    @Override
    public int countNegative(CustomArray array) {
        return (int) Arrays.stream(array.getArray()).filter(n -> n < 0).count();
    }

    @Override
    public int[] replaceByCondition(CustomArray array, Condition condition, int of, int to) throws CustomException {
        IntPredicate predicate;
        switch (condition) {
            case MORE -> predicate = n -> n > of;
            case LESS -> predicate = n -> n < of;
            case EQUAL -> predicate = n -> n == of;
            case MORE_OR_EQUAL -> predicate = n -> n >= of;
            case LESS_OR_EQUAL -> predicate = n -> n <= of;
            default -> throw new CustomException("Unknown condition");
        }
        return Arrays.stream(array.getArray())
                .map(n -> predicate.test(n) ? to : n)
                .toArray();
    }
}
