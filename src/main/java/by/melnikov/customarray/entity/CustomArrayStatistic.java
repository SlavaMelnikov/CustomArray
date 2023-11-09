package by.melnikov.customarray.entity;

import by.melnikov.customarray.service.ArrayService;
import by.melnikov.customarray.service.impl.ArrayServiceImpl;

public class CustomArrayStatistic {
    private double average;
    private long sum;
    private int max;
    private int min;

    public CustomArrayStatistic(CustomArray array) {
        ArrayService service = ArrayServiceImpl.getInstance();
        this.average = service.average(array);
        this.sum = service.sum(array);
        this.max = service.max(array);
        this.min = service.min(array);
    }

    public double getAverage() {
        return average;
    }

    public long getSum() {
        return sum;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
