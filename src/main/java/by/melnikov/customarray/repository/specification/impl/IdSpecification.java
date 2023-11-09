package by.melnikov.customarray.repository.specification.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.repository.specification.Condition;
import by.melnikov.customarray.repository.specification.Specification;

public class IdSpecification implements Specification {
    private Condition condition;
    private long value;
    private long from;
    private long to;

    public IdSpecification(Condition condition, long value) {
        this.condition = condition;
        this.value = value;
    }

    public IdSpecification(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean specify(CustomArray array) {
        if (condition != null) {
            switch (condition) {
                case MORE -> {
                    return array.getArrayId() > value;
                }
                case LESS -> {
                    return array.getArrayId() < value;
                }
                case EQUAL -> {
                    return array.getArrayId() == value;
                }
                case MORE_OR_EQUAL -> {
                    return array.getArrayId() >= value;
                }
                case LESS_OR_EQUAL -> {
                    return array.getArrayId() <= value;
                }
            }
        }
        return array.getArrayId() >= from && array.getArrayId() < to;
    }
}
