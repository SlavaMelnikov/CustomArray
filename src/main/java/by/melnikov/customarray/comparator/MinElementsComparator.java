package by.melnikov.customarray.comparator;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

import java.util.Comparator;

public class MinElementsComparator implements Comparator<CustomArray> {
    @Override
    public int compare(CustomArray o1, CustomArray o2) {
        ArrayService service = ArrayServiceImpl.getInstance();
        return service.min(o1) - service.min(o2);
    }
}
