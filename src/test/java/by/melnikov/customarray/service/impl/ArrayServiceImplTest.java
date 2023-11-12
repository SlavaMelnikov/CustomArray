package by.melnikov.customarray.service.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.service.ArrayService;
import org.junit.jupiter.api.Test;

import static by.melnikov.customarray.service.ArrayService.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayServiceImplTest {

    ArrayService service = ArrayServiceImpl.getInstance();

    @Test
    void arrayWithDifferentValues() {
        int[] array = new int[]{1, 12, -3, 8, 0, -5, 4, 7};
        CustomArray customArray = new CustomArray(array);
        assertAll(
                () -> assertEquals(12,  service.max(customArray)),
                () -> assertEquals(-5,  service.min(customArray)),
                () -> assertEquals(3.0, service.average(customArray)),
                () -> assertEquals(24,  service.sum(customArray)),
                () -> assertEquals(5,   service.countPositive(customArray)),
                () -> assertEquals(2,   service.countNegative(customArray)),
                () -> assertArrayEquals(new int[]{1, 12, 100, 8, 0, 100, 4, 7}, service.replaceByCondition(customArray, LESS, 0, 100))
        );
    }

    @Test
    void arrayWithOnlyPositiveValues() {
        int[] array = new int[]{1, 12, 3, 8, 1, 5, 4, 6};
        CustomArray customArray = new CustomArray(array);
        assertAll(
                () -> assertEquals(12,  service.max(customArray)),
                () -> assertEquals(1,   service.min(customArray)),
                () -> assertEquals(5.0, service.average(customArray)),
                () -> assertEquals(40,  service.sum(customArray)),
                () -> assertEquals(8,   service.countPositive(customArray)),
                () -> assertEquals(0,   service.countNegative(customArray)),
                () -> assertArrayEquals(new int[]{1, 100, 3, 8, 1, 5, 4, 6}, service.replaceByCondition(customArray, ArrayService.Condition.MORE, 10, 100))
        );
    }

    @Test
    void arrayWithOnlyNegativeValues() {
        int[] array = new int[]{-1, -12, -3, -8, -2, -5, -4, -5};
        CustomArray customArray = new CustomArray(array);
        assertAll(
                () -> assertEquals(-1, service.max(customArray)),
                () -> assertEquals(-12, service.min(customArray)),
                () -> assertEquals(-5.0, service.average(customArray)),
                () -> assertEquals(-40, service.sum(customArray)),
                () -> assertEquals(0, service.countPositive(customArray)),
                () -> assertEquals(8, service.countNegative(customArray)),
                () -> assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, service.replaceByCondition(customArray, LESS, 0, 1))
        );
    }

    @Test
    void arrayWithEqualValues() {
        int[] array = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        CustomArray customArray = new CustomArray(array);
        assertAll(
                () -> assertEquals(0, service.max(customArray)),
                () -> assertEquals(0, service.min(customArray)),
                () -> assertEquals(0.0, service.average(customArray)),
                () -> assertEquals(0, service.sum(customArray)),
                () -> assertEquals(0, service.countPositive(customArray)),
                () -> assertEquals(0, service.countNegative(customArray)),
                () -> assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, service.replaceByCondition(customArray, EQUAL, 0, 1))
        );
    }

    @Test
    void conditionIsNullTest() {
        int[] array = new int[]{1, 12, -3, 8, 0, -5, 4, 7};
        CustomArray customArray = new CustomArray(array);
        assertThrows(CustomException.class,
                () -> service.replaceByCondition(customArray, null , 0, 0));
    }
}