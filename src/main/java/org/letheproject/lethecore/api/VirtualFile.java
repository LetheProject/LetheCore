package org.letheproject.lethecore.api;

import java.io.InputStream;

public class VirtualFile {
    private final String name;
    private final String virtualLocation;
    private final InputStream contents;

    public VirtualFile(String name, String virtualLocation, InputStream contents) {
        this.name = name;
        this.virtualLocation = virtualLocation;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public String getVirtualLocation() {
        return virtualLocation;
    }

    public InputStream getContents() {
        return contents;
    }
}
