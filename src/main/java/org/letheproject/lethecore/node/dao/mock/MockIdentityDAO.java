package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.node.NodeIdentity;
import org.letheproject.lethecore.node.dao.IdentityDAO;

public class MockIdentityDAO implements IdentityDAO {
    private NodeIdentity identity;

    public MockIdentityDAO(NodeIdentity identity) {
        this.identity = identity;
    }

    @Override
    public NodeIdentity getIdentity() {
        return identity;
    }
}
