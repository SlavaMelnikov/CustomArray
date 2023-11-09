package by.melnikov.customarray.repository.specification.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.repository.specification.Condition;
import by.melnikov.customarray.repository.specification.SpecificationPredicate;

public class IdSpecificationPredicate implements SpecificationPredicate {
    private Condition condition;
    private long value;
    private long from;
    private long to;

    public IdSpecificationPredicate(Condition condition, long value) {
        this.condition = condition;
        this.value = value;
    }

    public IdSpecificationPredicate(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean test(CustomArray array) {
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
