package org.letheproject.lethecore.node;

public record NodeConfiguration(
        long shardSize,
        long maxReceivingShardSize,
        long bandwidthPeriod,
        long bandwidthQuota,
        long storageQuota
){}
