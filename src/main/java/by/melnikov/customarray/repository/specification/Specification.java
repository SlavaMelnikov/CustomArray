package by.melnikov.customarray.repository.specification;

import by.melnikov.customarray.entity.CustomArray;

public interface Specification {
    boolean specify(CustomArray array);
}
