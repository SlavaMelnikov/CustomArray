package by.melnikov.customarray.repository.specification.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.repository.specification.Condition;
import by.melnikov.customarray.repository.specification.Specification;
import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

public class MaxSpecification implements Specification {
    private Condition condition;
    private int value;
    private int from;
    private int to;

    public MaxSpecification(Condition condition, int value) {
        this.condition = condition;
        this.value = value;
    }

    public MaxSpecification(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean specify(CustomArray array) {
        ArrayService service = ArrayServiceImpl.getInstance();
        if (condition != null) {
            switch (condition) {
                case MORE -> {
                    return service.max(array) > value;
                }
                case LESS -> {
                    return service.max(array) < value;
                }
                case EQUAL -> {
                    return service.max(array) == value;
                }
                case MORE_OR_EQUAL -> {
                    return service.max(array) >= value;
                }
                case LESS_OR_EQUAL -> {
                    return service.max(array) <= value;
                }
            }
        }
        return service.max(array) >= from && service.max(array) < to;
    }
}
