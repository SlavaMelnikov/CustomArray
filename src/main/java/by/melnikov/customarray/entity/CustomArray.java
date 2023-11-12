package by.melnikov.customarray.entity;

import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.observer.CustomArrayObserver;
import by.melnikov.customarray.observer.impl.CustomArrayObserverImpl;
import by.melnikov.customarray.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.StringJoiner;

public class CustomArray {
    public static final Logger logger = LogManager.getLogger();
    private CustomArrayObserver observer;
    private long arrayId;
    private int[] array;


    public CustomArray(int[] array) {
        this.array = array;
        arrayId = IdGenerator.generateId();
        observer = new CustomArrayObserverImpl();
    }

    public long getArrayId() {
        return arrayId;
    }

    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }

    public void addObserver(CustomArrayObserver observer) {
        this.observer = observer;
    }

    public void removeObserver() {
        this.observer = null;
    }

    public void setArray(int[] array) throws CustomException {
        if (array == null && array.length == 0) {
            logger.error("array is null or don't have length");
            throw new CustomException("array must be not null and have some length");
        }
        this.array = Arrays.copyOf(array, array.length);
        notifyObservers();
    }

    public void changeElement(int index, int newElement) throws CustomException {
        if (index <= 0 || index > array.length) {
            logger.error("index is out of array bounds");
            throw new CustomException("index is out of array bounds");
        }
        array[index] = newElement;
        notifyObservers();
    }

    public void notifyObservers() {
        if (observer != null) {
            logger.info("elements of array was changed. notifying observers");
            observer.handleCustomArrayChanging(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomArray that = (CustomArray) o;

        if (arrayId != that.arrayId) return false;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = (int) (arrayId ^ (arrayId >>> 32));
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomArray.class.getSimpleName() + "{", "}")
                .add("arrayId=" + arrayId)
                .add("array=[" + Arrays.toString(array))
                .add("]")
                .toString();
    }
}
