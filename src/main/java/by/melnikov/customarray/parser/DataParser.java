package by.melnikov.customarray.parser;

import by.melnikov.customarray.validator.DataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    public static final String NUMBER_TEMPLATE_REGEX = "-?\\b\\d{1,10}\\b";

    private static DataParser instance;

    private DataParser() {
    }

    public static DataParser getInstance() {
        if (instance == null) {
            instance = new DataParser();
        }
        return instance;
    }

    public List<int[]> parse(List<String> lines) {
        List<int[]> result = new ArrayList<>();
        DataValidator validator = DataValidator.getInstance();
        for (String line : lines) {
            if (validator.isArrayInFileValid(line)) {
                result.add(parseLine(line));
            }
        }
        return result;
    }

    public int[] parseLine(String line) {
        Pattern pattern = Pattern.compile(NUMBER_TEMPLATE_REGEX);
        Matcher matcher = pattern.matcher(line);
        List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
