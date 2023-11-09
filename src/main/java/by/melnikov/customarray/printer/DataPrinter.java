package by.melnikov.customarray.printer;

import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class DataPrinter {
    private static final Logger logger = LogManager.getLogger();
    private static DataPrinter instance;
    private Path output;

    private DataPrinter() {
    }

    public static DataPrinter getInstance() {
        if (instance == null) {
            instance = new DataPrinter();
        }
        return instance;
    }

    public void createOutputFile(Path path) throws CustomException {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            output = path;
            logger.info("all results are stored in " + path);
        } catch (IOException e) {
            logger.error("creating file error");
            throw new CustomException("creating file error", e);
        }

    }

    public void printArray(int[] array) throws CustomException {
        try {
            Files.writeString(output, Arrays.toString(array) + "\n", StandardOpenOption.APPEND);
            Files.writeString(output, "==============================================\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.error("printing to file error");
            throw new CustomException("printing error", e);
        }
    }

    public void printList(List<CustomArray> arrays) throws CustomException {
        try {
            for (CustomArray customArray : arrays) {
                String line = customArray.getArrayId() + ": " + Arrays.toString(customArray.getArray());
                Files.writeString(output,  line + "\n", StandardOpenOption.APPEND);
            }
            Files.writeString(output, "==============================================\n\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.error("printing to file error");
            throw new CustomException("printing error", e);
        }
    }

    public void printString(String str) throws CustomException {
        try {
            Files.writeString(output, str + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.error("printing to file error");
            throw new CustomException("printing error", e);
        }
    }
}
