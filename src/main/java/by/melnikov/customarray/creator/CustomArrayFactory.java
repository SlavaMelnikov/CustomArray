package by.melnikov.customarray.creator;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayFactory {
    public static CustomArray create(int[] array) throws CustomException {
        return new CustomArray(array);
    }

    public static List<CustomArray> create(List<int[]> listOfArrays) throws CustomException {
        if (listOfArrays == null) {
            throw new CustomException("list of arrays is null");
        }
        List<CustomArray> result = new ArrayList<>();
        for (int[] array : listOfArrays) {
            result.add(create(array));
        }
        return result;
    }
}
