package org.letheproject.lethecore.node.dao.mock;

import org.letheproject.lethecore.node.NodeIdentity;
import org.letheproject.lethecore.node.dao.TrustDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockTrustDAO implements TrustDAO {
    private Set<NodeIdentity> identities;
    private Set<NodeIdentity> trusted;

    public MockTrustDAO() {
        identities = new HashSet<>();
        trusted = new HashSet<>();
    }

    @Override
    public List<NodeIdentity> getKnownIdentities() {
        return new ArrayList<>(identities);
    }

    @Override
    public void addKnownIdentity(NodeIdentity identity) {
        identities.add(identity);
    }

    @Override
    public List<NodeIdentity> getTrustedIdentities() {
        return new ArrayList<>(trusted);
    }

    @Override
    public void setAsTrusted(String identity) {
        NodeIdentity id = null;
        for (NodeIdentity i : identities) {
            if (i.getIdentity().equals(identity)) {
                id = i;
                break;
            }
        }
        if (null == id) {
            return;
        }
        identities.remove(id);
        trusted.add(id);
    }

    @Override
    public boolean isKnown(String identity) {
        for (NodeIdentity id : identities) {
            if (identity.equals(id.getIdentity())) {
                return true;
            }
        }
        return false;
    }
}
