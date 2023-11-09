package by.melnikov.customarray.service;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;

public interface ArrayService {
    enum Condition {
        MORE,
        LESS,
        EQUAL,
        MORE_OR_EQUAL,
        LESS_OR_EQUAL
    }


    int max(CustomArray array);
    int min(CustomArray array);
    double average(CustomArray array);
    long sum(CustomArray array) ;
    int countPositive(CustomArray array);
    int countNegative(CustomArray array);
    int[] replaceByCondition(CustomArray array, Condition condition, int ofNumber, int toNumber) throws CustomException;
}
