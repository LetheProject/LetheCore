package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.node.dao.ConfigurationDAO;

public class MockConfigurationDAO implements ConfigurationDAO {
    private long shardSize;
    private long bandwidthPeriodHours;
    private long bandwidthPerPeriod;
    private long shardQuota;
    private String duplicationFormula;

    @Override
    public long getShardSize() {
        return shardSize;
    }

    @Override
    public void setShardSize(long bytes) {
        shardSize = bytes;
    }

    @Override
    public long getBandwidthPeriodHours() {
        return bandwidthPeriodHours;
    }

    @Override
    public void setBandwidthPeriodHours(long hours) {
        bandwidthPeriodHours = hours;
    }

    @Override
    public long getBandwidthPerPeriod() {
        return bandwidthPerPeriod;
    }

    @Override
    public void setBandwidthPerPeriod(long bytes) {
        bandwidthPerPeriod = bytes;
    }

    @Override
    public long getShardStorageQuota() {
        return shardQuota;
    }

    @Override
    public void setShardStorageQuota(long bytes) {
        shardQuota = bytes;
    }

    @Override
    public String getDuplicationFormula() {
        return duplicationFormula;
    }

    @Override
    public void setDuplicationFormula(String formula) {
        duplicationFormula = formula;
    }
}
