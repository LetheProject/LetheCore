package org.letheproject.lethecore.node.dao;

import org.letheproject.lethecore.shard.Shard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MariaDBShardDAO implements ShardDAO {
    private Connection connection;

    public MariaDBShardDAO(String databaseAddress, String databaseUsername, String databasePassword) {
        try {
            connection = DriverManager.getConnection(databaseAddress, databaseUsername, databasePassword);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsShard(String uuid) {
        return false;
    }

    @Override
    public Optional<Shard> getShard(String uuid) {
        return Optional.empty();
    }

    @Override
    public List<String> getShardsOfOwner(String ownerIdentity) {
        return null;
    }

    @Override
    public boolean removeShard(String uuid) {
        return false;
    }

    @Override
    public List<String> getAllShardUUIDs() {
        return null;
    }

    @Override
    public List<String> getAllShardOwnerUUIDs() {
        return null;
    }

    @Override
    public long spaceUsedByOwner(String ownerIdentity) {
        return 0;
    }

    @Override
    public long bandwidthUsedByOwner(String identity, long period) {
        return 0;
    }

    @Override
    public long usedSpace() {
        return 0;
    }
}
