package org.letheproject.lethecore.node;

import java.util.List;

public interface TrustDAO {
    List<NodeIdentity> getTrustedIdentities();
}
