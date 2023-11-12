package by.melnikov.customarray.service;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.service.impl.BubbleSortImpl;
import by.melnikov.customarray.service.impl.MergeSortImpl;
import by.melnikov.customarray.service.impl.QuickSortImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortArrayServiceTest {

    @Test
    void sort() {
        int[] array = {2, 12, 5, 48, 0, 4, -10};
        int[] expected = {-10, 0, 2, 4, 5, 12, 48};
        CustomArray customArray = new CustomArray(array);
        assertAll(
                () -> assertArrayEquals(expected, new BubbleSortImpl().sort(customArray)),
                () -> assertArrayEquals(expected, new MergeSortImpl().sort(customArray)),
                () -> assertArrayEquals(expected, new QuickSortImpl().sort(customArray))
        );
    }
}