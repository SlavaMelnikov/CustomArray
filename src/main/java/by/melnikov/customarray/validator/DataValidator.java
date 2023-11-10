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
    public boolean fileExists(String path) throws CustomException {
        return Files.exists(Path.of(path));
    }

    public boolean isArrayInFileValid(String arrayInFile) {
        return (!arrayInFile.isBlank() && !hasInvalidSymbols(arrayInFile) && hasNumbersOnlyInRange(arrayInFile));
    }

    public boolean hasInvalidSymbols(String arrayInFile) {
        return arrayInFile.length() != arrayInFile.replaceAll(INVALID_SYMBOLS_REGEX, "").length();
    }

    public boolean hasNumbersOnlyInRange(String arrayInFile) {
        Pattern pattern = Pattern.compile(BIG_NUMBER_TEMPLATE_REGEX);
        Matcher matcher = pattern.matcher(arrayInFile);
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
