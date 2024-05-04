package org.letheproject.lethecore.api.network.requests;

import org.letheproject.lethecore.node.Node;

public interface Response<P> {
    void run(Node node, String targetIdentity, P parameter);
}
