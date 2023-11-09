package by.melnikov.customarray.comparator;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

import java.util.Comparator;

public class MaxElementsComparator implements Comparator<CustomArray> {
    @Override
    public int compare(CustomArray o1, CustomArray o2) {
        ArrayService service = ArrayServiceImpl.getInstance();
        return service.max(o1) - service.max(o2);
    }
}
