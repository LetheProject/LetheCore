package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.dataprocessing.DataProcessor;
import org.letheproject.lethecore.node.dao.DataProcessorDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MockDataProcessorDAO implements DataProcessorDAO {
    private HashMap<String, DataProcessor> processors;

    public MockDataProcessorDAO() {
        processors = new HashMap<>();
    }

    @Override
    public DataProcessor getDataProcessor(String uuid) {
        return processors.get(uuid);
    }

    @Override
    public String addDataProcessor(DataProcessor dataProcessor) {
        String uuid = UUID.randomUUID().toString();
        processors.put(uuid, dataProcessor);
        return uuid;
    }

    @Override
    public List<String> listUUIDs() {
        return new ArrayList<>(processors.keySet());
    }
}
