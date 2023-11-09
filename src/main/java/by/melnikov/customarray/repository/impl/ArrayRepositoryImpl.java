package by.melnikov.customarray.repository.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.repository.ArrayRepository;
import by.melnikov.customarray.repository.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class ArrayRepositoryImpl implements ArrayRepository {
    private static ArrayRepositoryImpl instance;
    private List<CustomArray> repository = new ArrayList<>();

    private ArrayRepositoryImpl() {
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public boolean add(CustomArray array) {
        return repository.add(array);
    }

    @Override
    public CustomArray get(int index) throws CustomException {
        if (index >= 0 && index < repository.size()){
            return repository.get(index);
        }
        throw new CustomException("index " + index + " is out of bounds of repository", new IndexOutOfBoundsException());
    }

    @Override
    public boolean remove(CustomArray array) {
        return repository.remove(array);
    }

    @Override
    public List<CustomArray> getRepository() {
        return repository;
    }

    @Override
    public void setRepository(List<CustomArray> repository) {
        if (repository != null) {
            this.repository = repository;
        }
    }

    @Override
    public void sort(Comparator<CustomArray> comparator) {
        repository.sort(comparator);
    }

    @Override
    public List<CustomArray> query(Specification specification) {
        List<CustomArray> specified = new ArrayList<>();
        for (CustomArray array : repository) {
            if (specification.specify(array)) {
                specified.add(array);
            }
        }
        return specified;
    }

    @Override
    public List<CustomArray> queryStream(Specification specification) {
        return repository.stream()
                .filter(specification::specify)
                .toList();
    }

    @Override
    public List<CustomArray> queryPredicate(Predicate<CustomArray> predicate) {
        return repository.stream()
                .filter(predicate)
                .toList();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ArrayRepositoryImpl.class.getSimpleName() + "[", "]")
                .add("repository=" + repository)
                .toString();
    }
}
