package org.letheproject.lethecore.node.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MariaDBShardDAOTest {

    @Test
    void test() {
        ShardDAO dao = new MariaDBShardDAO(
                "jdbc:mariadb://localhost:3306/test",
                "test",
                "test"
        );

    }
}