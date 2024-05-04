package org.letheproject.lethecore.node.dao;

public interface ConfigurationDAO {
        long getShardSize();
        void setShardSize(long bytes);
        long getBandwidthPeriodHours();
        void setBandwidthPeriodHours(long hours);
        long getBandwidthPerPeriod();
        void setBandwidthPerPeriod(long bytes);
        long getShardStorageQuota();
        void setShardStorageQuota(long bytes);
        String getDuplicationFormula();
        void setDuplicationFormula(String formula);
}
