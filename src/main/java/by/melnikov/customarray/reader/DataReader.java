package by.melnikov.customarray.reader;

import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private static final Logger logger = LogManager.getLogger();
    private static DataReader instance;

    private DataReader() {
    }

    public static DataReader getInstance() {
        if (instance == null) {
            instance = new DataReader();
        }
        return instance;
    }

    public List<String> read(String source) throws CustomException {
        List<String> lines = new ArrayList<>();
        DataValidator validator = DataValidator.getInstance();
        Path path = Path.of(source);
        if (validator.fileExists(path)) {
            try {
                lines = Files.readAllLines(path);
            } catch (IOException e) {
                logger.error("reading arrays from file error");
                throw new CustomException("reading error" + e);
            }
        }
        return lines;
    }
}
