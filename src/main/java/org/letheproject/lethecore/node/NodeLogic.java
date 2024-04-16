package org.letheproject.lethecore.node;

public interface NodeLogic {
    void receiveSignatureRequest(byte[] signature);
    void sendSignatureRequest();

}
