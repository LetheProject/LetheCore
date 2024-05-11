package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.node.dao.ShardDAO;
import org.letheproject.lethecore.shard.Shard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MockShardDAO implements ShardDAO {
    private HashMap<String, Shard> shards;

    public MockShardDAO() {
        this.shards = new HashMap<>();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean containsShard(String uuid) {
        return shards.containsKey(uuid);
    }

    @Override
    public Optional<Shard> getShard(String uuid) {
        return Optional.ofNullable(shards.get(uuid));
    }

    @Override
    public List<String> getShardsOfOwner(String ownerIdentity) {
        List<String> uuids = new ArrayList<>();
        shards.values().forEach(shard -> {
            if (shard.getShardPublicData().ownerIdentity().equals(ownerIdentity)) {
                uuids.add(shard.getShardPublicData().metadata().getShardUUID());
            }
        });
        return uuids;
    }

    @Override
    public boolean removeShard(String uuid) {
        return null != shards.remove(uuid);
    }

    @Override
    public List<String> getAllShardUUIDs() {
        return new ArrayList<>(shards.keySet());
    }

    @Override
    public List<String> getAllShardOwnerUUIDs() {
        List<String> uuids = new ArrayList<>();
        shards.values().forEach(shard -> uuids.add(shard.getShardPublicData().ownerIdentity()));
        return uuids;
    }

    @Override
    public long spaceUsedByOwner(String ownerIdentity) {
        long space = 0;
        for (Shard shard : shards.values()) {
            if (shard.getShardPublicData().ownerIdentity().equals(ownerIdentity)) {
                space += shard.size();
            }
        }
        return space;
    }

    @Override
    public long bandwidthUsedByOwner(String identity, long period) {
        long now = System.currentTimeMillis();
        long periodStart = now - period;
        long bandwidth = 0;
        for (Shard shard : shards.values()) {
            if (shard.getShardPublicData().created() >= periodStart) {
                bandwidth += shard.size();
            }
        }
        return bandwidth;
    }

    @Override
    public long usedSpace() {
        long space = 0;
        for (Shard shard : shards.values()) {
            space += shard.size();
        }
        return space;
    }
}
