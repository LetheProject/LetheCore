package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.dataprocessing.DataProcessor;
import org.letheproject.lethecore.node.dao.DataProcessorDAO;

import java.util.HashMap;
import java.util.List;

public class MockDataProcessorDAO implements DataProcessorDAO {
    private HashMap<String, DataProcessor> processors;

    public MockDataProcessorDAO() {

    }

    @Override
    public DataProcessor getDataProcessor(String uuid) {
        return null;
    }

    @Override
    public String addDataProcessor(DataProcessor dataProcessor) {
        return null;
    }

    @Override
    public List<String> listUUIDs() {
        return null;
    }
}
