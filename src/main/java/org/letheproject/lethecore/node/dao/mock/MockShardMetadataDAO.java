package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.node.dao.ShardMetadataDAO;
import org.letheproject.lethecore.shard.ShardMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockShardMetadataDAO implements ShardMetadataDAO {
    private Map<String, ShardMetadata> map;

    public MockShardMetadataDAO() {
        map = new HashMap<>();
    }

    @Override
    public ShardMetadata get(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void add(ShardMetadata metadata) {
        map.put(metadata.getShardUUID(), metadata);
    }

    @Override
    public void remove(String uuid) {
        map.remove(uuid);
    }

    @Override
    public void update(ShardMetadata metadata) {
        map.put(metadata.getShardUUID(), metadata);
    }

    @Override
    public List<String> list() {
        return new ArrayList<>(map.keySet());
    }

    @Override
    public List<String> list(String fileUUID) {
        List<String> uuids = new ArrayList<>();
        for (ShardMetadata metadata : map.values()) {
            if (metadata.fileUUID().equals(fileUUID)) {
                uuids.add(metadata.getShardUUID());
            }
        }
        return uuids;
    }
}
