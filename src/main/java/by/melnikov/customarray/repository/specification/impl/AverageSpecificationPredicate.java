package by.melnikov.customarray.repository.specification.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.repository.specification.Condition;
import by.melnikov.customarray.repository.specification.SpecificationPredicate;
import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

public class AverageSpecificationPredicate implements SpecificationPredicate {
    private Condition condition;
    private double value;
    private double from;
    private double to;

    public AverageSpecificationPredicate(Condition condition, double value) {
        this.condition = condition;
        this.value = value;
    }

    public AverageSpecificationPredicate(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean test(CustomArray array) {
        ArrayService service = ArrayServiceImpl.getInstance();
        if (condition != null) {
            switch (condition) {
                case MORE -> {
                    return service.average(array) > value;
                }
                case LESS -> {
                    return service.average(array) < value;
                }
                case EQUAL -> {
                    return service.average(array) == value;
                }
                case MORE_OR_EQUAL -> {
                    return service.average(array) >= value;
                }
                case LESS_OR_EQUAL -> {
                    return service.average(array) <= value;
                }
            }
        }
        return service.average(array) >= from && service.average(array) < to;
    }
}
