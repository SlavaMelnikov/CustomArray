package by.melnikov.customarray.observer.impl;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.entity.CustomArrayStatistic;
import by.melnikov.customarray.entity.Warehouse;
import by.melnikov.customarray.observer.CustomArrayObserver;

public class CustomArrayObserverImpl implements CustomArrayObserver {
    @Override
    public void handleCustomArrayChanging(CustomArray array) {
        CustomArrayStatistic changedStatistic = new CustomArrayStatistic(array);
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.put(array.getArrayId(), changedStatistic);
    }
}
