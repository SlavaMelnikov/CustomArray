package by.melnikov.customarray.validator;

import by.melnikov.customarray.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  The line is considered valid if:
 *      - only ". , - : ;" and/or any number of spaces as separators;
 *      - all numbers are in integer range;
 *      - contains at least one number.
 *
 *  A number is considered negative if the minus sign is directly in front of it. Examples:
 *      - positive: 4, - 5, ---  3,  5-;
 *      - negative: -3, --1, -- -4-;
 */
public class DataValidator {
    private static final Logger logger = LogManager.getLogger();
    public static final String BIG_NUMBER_TEMPLATE_REGEX = "-?\\b\\d{10,}\\b";
    public static final String INVALID_SYMBOLS_REGEX = "[^0-9.,-:;\\s]";
    private static DataValidator instance;

    private DataValidator() {
    }

    public static DataValidator getInstance() {
        if (instance == null) {
            instance = new DataValidator();
        }
        return instance;
    }
    public boolean fileExists(Path path) throws CustomException {
        if (Files.exists(path)) {
            return true;
        } else {
            logger.error("file not found in " + path);
            throw new CustomException("file not found in " + path);
        }
    }

    public boolean isValid(String line) {
        return !isEmpty(line) && !hasInvalidSymbols(line) && hasNumbersOnlyInRange(line);
    }

    public boolean isEmpty(String line) {
        return line.isEmpty() || line.isBlank();
    }

    public boolean hasInvalidSymbols(String line) {
        return line.length() != line.replaceAll(INVALID_SYMBOLS_REGEX, "").length();
    }

    public boolean hasNumbersOnlyInRange(String line) {
        Pattern pattern = Pattern.compile(BIG_NUMBER_TEMPLATE_REGEX);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            try {
                Integer.parseInt(matcher.group());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
