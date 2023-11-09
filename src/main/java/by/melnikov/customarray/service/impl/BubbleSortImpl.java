package by.melnikov.customarray.service.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.service.SortArrayService;

public class BubbleSortImpl implements SortArrayService {
    @Override
    public int[] sort(CustomArray customArray) {
        int[] sorted = customArray.getArray();
        for (int i = 0; i < sorted.length; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }
}
