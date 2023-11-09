package by.melnikov.customarray.comparator;

import by.melnikov.customarray.entity.CustomArray;

import java.util.Comparator;

public class ArraysSizeComparator implements Comparator<CustomArray> {
    @Override
    public int compare(CustomArray o1, CustomArray o2) {
        return o1.getArray().length - o2.getArray().length;
    }
}
