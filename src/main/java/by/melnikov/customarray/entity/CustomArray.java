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
    private long arrayId;
    private int[] array;
    CustomArrayObserver observer;

    public CustomArray(int[] array) throws CustomException {
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

    public void setArray(int[] array) throws CustomException {
        if (array != null && array.length > 0) {
            this.array = Arrays.copyOf(array, array.length);
            notifyObservers();
        } else {
            logger.error("array is null or don't have length");
            throw new CustomException("array must be not null and have some length", new IllegalArgumentException());
        }
    }

    public void changeElement(int index, int newElement) throws CustomException {
        if (index >= 0 && index < array.length) {
            array[index] = newElement;
            notifyObservers();
        } else {
            logger.error("index is out of array bounds");
            throw new CustomException("index is out of array bounds", new IndexOutOfBoundsException());
        }
    }

    public void notifyObservers() {
        logger.info("elements of array was changed. notifying observers");
        observer.handleCustomArrayChanging(this);
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
