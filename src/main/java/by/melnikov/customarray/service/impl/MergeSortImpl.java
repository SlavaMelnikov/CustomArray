package by.melnikov.customarray.service.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.service.SortArrayService;

public class MergeSortImpl implements SortArrayService {

    public int[] sort(CustomArray customArray) {
        return split(customArray.getArray());
    }

    private int[] split(int[] array) {
        if (array.length < 2) {
            return new int[0];
        }
        int middle = array.length / 2;
        int[] left = new int[middle];
        int[] right = new int[array.length - middle];
        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }
        split(left);
        split(right);
        return merge(array, left, right);
    }

    private int[] merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
        return array;
    }
}
