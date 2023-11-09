package by.melnikov.customarray.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger();
    private static Warehouse instance;
    private HashMap<Long, CustomArrayStatistic> observed = new HashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(Long id, CustomArrayStatistic statistic) {
        observed.put(id, statistic);
        logger.info("parameters of array with id: " + id + " were recalculated");
    }

    public void remove(Long id) {
        observed.remove(id);
        logger.info("array " + id + " was removed from observed");
    }
}
