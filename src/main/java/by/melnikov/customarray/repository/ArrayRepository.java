package by.melnikov.customarray.repository;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.repository.specification.Specification;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ArrayRepository {
    boolean add(CustomArray array);
    CustomArray get(int index) throws CustomException;
    boolean remove(CustomArray array);
    List<CustomArray> getRepository();
    void setRepository(List<CustomArray> repository);
    void sort(Comparator<CustomArray> comparator);
    List<CustomArray> query(Specification specification);
    List<CustomArray> queryStream(Specification specification);
    List<CustomArray> queryPredicate(Predicate<CustomArray> predicate);
}
