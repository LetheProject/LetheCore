package org.letheproject.lethecore.api;

import java.util.List;
import java.util.Optional;

/**
 * The client-facing API controller for file storage on the node's network.
 */
public interface ClientFacingStorageController {
    /**
     * Attempt to store the provided file in the node's network.
     * @param file the file to store on the node's network.
     * @return the result code.
     */
    Object storeFile(VirtualFile file);

    /**
     * Attempt to update the provided file in the node's network.
     * Presumes it is already on the network, but will not fail if it is.
     * This method is an idempotent version of ClientFacingStorageController.storeFile.
     * @param file the file to update on the node's network.
     * @return the result code.
     */
    Object updateFile(VirtualFile file);

    /**
     * Attempt to delete the file at the absolute virtual path in the node's network.
     * @param absoluteVirtualPath the absolute virtual path of the file in the form <virtualLocation>/<name>.
     * @return the result code.
     */
    Object deleteFile(String absoluteVirtualPath);

    /**
     * List all files stored on the node's network in the form <virtualLocation>/<name>.
     * @return the list.
     */
    Object listFiles();

    /**
     * Attempt to get the file at the absolute virtual path in the node's network.
     * @param absoluteVirtualPath the absolute virtual path of the file in the form <virtualLocation>/<name>.
     * @return the file if successful, empty otherwise.
     */
    Object getFile(String absoluteVirtualPath);

    /**
     * The remaining storage quota of this node for its network.
     * @return the remaining quota in bytes.
     */
    Object remainingQuota();

    /**
     * The remaining hourly storage bandwidth of this node for its network.
     * @return the remaining bandwidth in bytes.
     */
    Object remainingHourlyBandwidth();

    /**
     * The remaining daily storage bandwidth of this node for its network.
     * @return the remaining bandwidth in bytes.
     */
    Object remainingDailyBandwidth();

    /**
     * The remaining storage quota share this node has on its network.
     * @return the remaining quota in bytes.
     */
    Object remainingQuotaShare();

    /**
     * The remaining hourly storage bandwidth share this node has on its network.
     * @return the remaining bandwidth in bytes.
     */
    Object remainingHourlyBandwidthShare();

    /**
     * The remaining daily storage bandwidth share this node has on its network.
     * @return the remaining bandwidth in bytes.
     */
    Object remainingDailyBandwidthShare();
}
