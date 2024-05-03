package org.letheproject.lethecore.node;

public record NodeConfiguration(
        long shardSize,
        long bandwidthPeriodHours,
        long bandwidthPerPeriod,
        long shardStorageQuota,
        String duplicationFormula
){}
