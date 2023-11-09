package by.melnikov.customarray.repository.specification.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.repository.specification.Condition;
import by.melnikov.customarray.repository.specification.Specification;
import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

public class SumSpecification implements Specification {
    private Condition condition;
    private int value;
    private int from;
    private int to;

    public SumSpecification(Condition condition, int value) {
        this.condition = condition;
        this.value = value;
    }

    public SumSpecification(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean specify(CustomArray array) {
        ArrayService service = ArrayServiceImpl.getInstance();
        if (condition != null) {
            switch (condition) {
                case MORE -> {
                    return service.sum(array) > value;
                }
                case LESS -> {
                    return service.sum(array) < value;
                }
                case EQUAL -> {
                    return service.sum(array) == value;
                }
                case MORE_OR_EQUAL -> {
                    return service.sum(array) >= value;
                }
                case LESS_OR_EQUAL -> {
                    return service.sum(array) <= value;
                }
            }
        }
        return service.sum(array) >= from && service.sum(array) <= to;
    }
}
