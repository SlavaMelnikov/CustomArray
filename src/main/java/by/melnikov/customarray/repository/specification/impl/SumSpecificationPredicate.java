package by.melnikov.customarray.repository.specification.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.repository.specification.Condition;
import by.melnikov.customarray.repository.specification.SpecificationPredicate;
import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

public class SumSpecificationPredicate implements SpecificationPredicate {
    private Condition condition;
    private int value;
    private int from;
    private int to;

    public SumSpecificationPredicate(Condition condition, int value) {
        this.condition = condition;
        this.value = value;
    }

    public SumSpecificationPredicate(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean test(CustomArray array) {
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
