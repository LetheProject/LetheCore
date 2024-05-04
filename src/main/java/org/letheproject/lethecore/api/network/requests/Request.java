package org.letheproject.lethecore.api.network.requests;

import org.letheproject.lethecore.node.Node;

/**
 * Represents how this node should make requests to the network.
 * Abstracts the actual network communication logic.
 * @param <O> the result type  of the request.
 * @param <P> the parameter type of the request.
 */
public interface Request<O, P> {
    /**
     * Run the request.
     * @param node the node making the request.
     * @param param the parameters of the request.
     * @return the result of the request.
     */
    O run(Node node, P param);
}
