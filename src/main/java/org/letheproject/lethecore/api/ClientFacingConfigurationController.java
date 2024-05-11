package org.letheproject.lethecore.api;

import java.util.List;

/**
 * The client-facing API controller for the configuration of the node.
 */
public interface ClientFacingConfigurationController {
    /**
     * Get this node's maximum degrees of separation for trust.
     * @return the maximum.
     */
    Object getMaximumDegreesOfSeparation();

    /**
     * Set this node's maximum degrees of separation for trust.
     * @param maximum the maximum.
     */
    Object setMaximumDegreesOfSeparation(int maximum);

    /**
     * Get this node's duplication formula for shard duplication.
     * @return the formula.
     */
    Object getDuplicationFormula();

    /**
     * Set this node's duplication formula for shard duplication.
     * @param formula the formula.
     */
    Object setDuplicationFormula(String formula);

    /**
     * Get this node's transformation sequences, in order of creation.
     * A sequence is used for the compression and encryption of files to be stored.
     * @return the sequences.
     */
    Object getTransformationSequences();

    /**
     * Set this node's current transformation sequence.
     * The sequence is used for the compression and encryption of files to be stored.
     * @param sequence the sequence.
     */
    Object setTransformationSequence(String sequence);

    /**
     * Get this node's configured storage quota.
     * @return the quota in bytes.
     */
    Object getStorageQuota();

    /**
     * Set this node's configured storage quota.
     * @param quota the quota in bytes.
     */
    Object setStorageQuota(long quota);

    /**
     * Get this node's hourly bandwidth.
     * @return the bandwidth in bytes.
     */
    Object getHourlyBandwidth();

    /**
     * Set this node's hourly bandwidth.
     * @param bandwidth the bandwidth in bytes.
     */
    Object setHourlyBandwidth(long bandwidth);

    /**
     * Get this node's daily bandwidth.
     * @return the bandwidth in bytes.
     */
    Object getDailyBandwidth();

    /**
     * Set this node's daily bandwidth.
     * @param bandwidth the bandwidth in bytes.
     */
    Object setDailyBandwidth(long bandwidth);
}
