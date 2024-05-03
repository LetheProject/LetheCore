package org.letheproject.lethecore.node;

import org.letheproject.lethecore.dataprocessing.DataProcessor;

import java.util.List;

public interface DataProcessorDAO {
    DataProcessor getDataProcessor(String uuid);
    String addDataProcessor(DataProcessor dataProcessor);
    List<String> listUUIDs();
}
