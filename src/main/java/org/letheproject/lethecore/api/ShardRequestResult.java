package org.letheproject.lethecore.api;

import org.letheproject.lethecore.shard.Shard;

/**
 * Represents the result of a shard storage, retrieval, deletion, or modification request.
 * The invalid error code occurs when the format or contents of the request is invalid, such as missing fields or improper field sizes.
 * The unauthorized error code occurs when signatures fail to verify, there is a lack of mutual trust, or either of the node's have the other flagged as excluded.
 * The unknown error code is a fall-back.
 */
public class ShardRequestResult {
    public static final int SUCCESS = 0;
    public static final int FAILURE_INVALID = 1;
    public static final int FAILURE_UNAUTHORIZED = 2;
    public static final int FAILURE_SENDER_BANDWIDTH_EXCEEDED = 3;
    public static final int FAILURE_SENDER_QUOTA_EXCEEDED = 4;
    public static final int FAILURE_RECEIVER_BANDWIDTH_EXCEEDED = 5;
    public static final int FAILURE_RECEIVER_QUOTA_EXCEEDED = 6;
    public static final int FAILURE_SHARD_NOT_FOUND = 7;
    public static final int FAILURE_UNKNOWN = 8;
    private final int code;
    private final Shard shard;

    /**
     * Instantiate a shard request result.
     * @param code the code of the result.
     */
    public ShardRequestResult(int code) {
        this.code = code;
        this.shard = null;
    }

    /**
     * Instantiate a shard request result.
     * @param code the code of the result.
     * @param shard the shard encapsulated by the result.
     */
    public ShardRequestResult(int code, Shard shard) {
        if (code != 0 && shard != null) {
            throw new RuntimeException("A ShardResultRequest cannot contain a shard and have failed");
        }
        this.code = code;
        this.shard = shard;
    }

    /**
     * Determine if the result is a request success.
     * @return the boolean result.
     */
    public boolean succeeded() {
        return code == 0;
    }

    /**
     * Determine if the result is a request failure.
     * @return the boolean result.
     */
    public boolean failed() {
        return code != 0;
    }

    /**
     * Get the code for the result.
     * @return the code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Determine if this encapsulates a shard.
     * @return the boolean result.
     */
    public boolean isEmpty() {
        return shard == null;
    }

    /**
     * Get the encapsulated shard.
     * @return the shard.
     */
    public Shard getShard() {
        return shard;
    }
}
