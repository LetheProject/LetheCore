package org.letheproject.lethecore.node;

public class Node {
    private final IdentityDAO identityDAO;
    private final TrustDAO trustDAO;
    private final KeyDAO keyDAO;
    private final DataProcessorDAO dataProcessorDAO;
    private final ShardDAO shardDAO;
    private final ShardMetadataDAO shardMetadataDAO;
    private final NodeConfiguration configuration;

    public Node(IdentityDAO identityDAO, TrustDAO trustDAO, KeyDAO keyDAO, DataProcessorDAO dataProcessorDAO, ShardDAO shardDAO, ShardMetadataDAO shardMetadataDAO, NodeConfiguration configuration) {
        this.identityDAO = identityDAO;
        this.trustDAO = trustDAO;
        this.keyDAO = keyDAO;
        this.dataProcessorDAO = dataProcessorDAO;
        this.shardDAO = shardDAO;
        this.shardMetadataDAO = shardMetadataDAO;
        this.configuration = configuration;
    }

    public IdentityDAO getIdentityDAO() {
        return identityDAO;
    }

    public TrustDAO getTrustDAO() {
        return trustDAO;
    }

    public KeyDAO getKeyDAO() {
        return keyDAO;
    }

    public DataProcessorDAO getDataProcessorDAO() {
        return dataProcessorDAO;
    }

    public ShardDAO getShardDAO() {
        return shardDAO;
    }

    public ShardMetadataDAO getShardMetadataDAO() {
        return shardMetadataDAO;
    }

    public NodeConfiguration getConfiguration() {
        return configuration;
    }
}
