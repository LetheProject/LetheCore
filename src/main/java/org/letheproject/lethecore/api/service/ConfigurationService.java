package org.letheproject.lethecore.api.service;

import java.util.List;

public interface ConfigurationService {
    /**
     * Get this node's maximum degrees of separation for trust.
     * @return the maximum.
     */
    int getMaximumDegreesOfSeparation();

    /**
     * Set this node's maximum degrees of separation for trust.
     * @param maximum the maximum.
     */
    void setMaximumDegreesOfSeparation(int maximum);

    /**
     * Get this node's duplication formula for shard duplication.
     * @return the formula.
     */
    String getDuplicationFormula();

    /**
     * Set this node's duplication formula for shard duplication.
     * @param formula the formula.
     */
    void setDuplicationFormula(String formula);

    /**
     * Get this node's transformation sequences, in order of creation.
     * A sequence is used for the compression and encryption of files to be stored.
     * @return the sequences.
     */
    List<String> getTransformationSequences();

    /**
     * Set this node's current transformation sequence.
     * The sequence is used for the compression and encryption of files to be stored.
     * @param sequence the sequence.
     */
    void setTransformationSequence(String sequence);

    /**
     * Get this node's configured storage quota.
     * @return the quota in bytes.
     */
    long getStorageQuota();

    /**
     * Set this node's configured storage quota.
     * @param quota the quota in bytes.
     */
    void setStorageQuota(long quota);

    /**
     * Get this node's hourly bandwidth.
     * @return the bandwidth in bytes.
     */
    long getHourlyBandwidth();

    /**
     * Set this node's hourly bandwidth.
     * @param bandwidth the bandwidth in bytes.
     */
    void setHourlyBandwidth(long bandwidth);

    /**
     * Get this node's daily bandwidth.
     * @return the bandwidth in bytes.
     */
    long getDailyBandwidth();

    /**
     * Set this node's daily bandwidth.
     * @param bandwidth the bandwidth in bytes.
     */
    void setDailyBandwidth(long bandwidth);
}
