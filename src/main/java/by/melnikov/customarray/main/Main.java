package by.melnikov.customarray.main;

import by.melnikov.customarray.creator.CustomArrayFactory;
import by.melnikov.customarray.entity.CustomArray;
import by.melnikov.customarray.exception.CustomException;
import by.melnikov.customarray.parser.DataParser;
import by.melnikov.customarray.printer.DataPrinter;
import by.melnikov.customarray.reader.DataReader;
import by.melnikov.customarray.repository.ArrayRepository;
import by.melnikov.customarray.repository.impl.ArrayRepositoryImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;


public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws CustomException {
        logger.info("application is running");

        String file = "arrays.txt";
        DataReader reader = DataReader.getInstance();
        logger.info("reading arrays from " + file);
        List<String> data = reader.read(file);

        DataParser parser = DataParser.getInstance();
        List<int[]> arrays = parser.parse(data);

        logger.info("creating custom arrays");
        List<CustomArray> customArrays = CustomArrayFactory.create(arrays);

        ArrayRepository repository = ArrayRepositoryImpl.getInstance();
        logger.info("saving custom arrays to repository");
        repository.setRepository(customArrays);

        DataPrinter printer = DataPrinter.getInstance();
        Path output = Path.of("output.txt");
        printer.createOutputFile(output);
        printer.printString("all valid custom arrays");
        printer.printList(repository.getRepository());

        logger.info("do some operations with specifications and comparators");
        //
        //
        //
        repository.get(0).changeElement(0, 1000);
        logger.info("application is complete");
    }
}
