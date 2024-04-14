package org.letheproject.lethecore.compression;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Deflate implements Compressor {
    private final Deflater deflater;
    private final Inflater inflater;

    public Deflate(int level) {
        if (level < 0) {
            throw new RuntimeException(String.format("Compression level for Deflate must be greater than 0, not %d", level));
        }
        if (level > 9) {
            throw new RuntimeException(String.format("Compression level for Deflate must be less than 10, not %d", level));
        }
        this.deflater = new Deflater();
        this.deflater.setLevel(level);
        this.inflater = new Inflater();
    }

    @Override
    public byte[] compress(byte[] data) {
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int compressedSize = deflater.deflate(buffer);
            outputStream.write(buffer, 0, compressedSize);
        }
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] data) {
        try {
            inflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int decompressedSize = inflater.inflate(buffer);
                outputStream.write(buffer, 0, decompressedSize);
            }
            return outputStream.toByteArray();
        }
        catch (DataFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
