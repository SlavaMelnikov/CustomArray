package by.melnikov.customarray.service.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.service.SortArrayService;

public class QuickSortImpl implements SortArrayService {
    @Override
    public int[] sort(CustomArray array) {
        int[] customArray = array.getArray();
        return quickSort(customArray, 0, customArray.length - 1);
    }

    private int[] quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
        return array;
    }

    private int partition(int[] array, int left, int right) {
        int pivot = right;
        while (left < pivot) {
            if (array[left] > array[pivot]) {
                int temp  = array[pivot - 1];
                array[pivot - 1] = array[pivot];
                array[pivot] = temp;

                if (left + 1 != pivot) {
                    temp = array[left];
                    array[left] = array[pivot];
                    array[pivot] = temp;
                }
                pivot--;
                left--;
            }
            left++;
        }
        return pivot;
    }
}
