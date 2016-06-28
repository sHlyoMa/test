package com.enya.test.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class Zip {
    public static void main(String[] args) throws IOException {
        URI zipFile = URI.create("jar:file:/sample/test.zip");
        Path zip1 = Paths.get("/sample/sample.zip");
//        try(FileSystem zip = FileSystems.getFileSystem(zipFile)){
//            Path file = zip.getPath("test.txt");
//
//            System.out.println(Files.readAllBytes(file));
//        }

        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name : dirs) {
            System.err.println(name);
        }

        System.out.println("====Start====");
        Path source = Paths.get("D:/1.txt");
        Path source1 = Paths.get("D:/2.txt");
//        Files.createFile(source1);
        System.out.println(Files.readAttributes(source, BasicFileAttributes.class).lastModifiedTime());
        Files.write(source, "Test message".getBytes(), StandardOpenOption.WRITE);
        System.out.println(Files.readAttributes(Paths.get("D:/1.txt"), BasicFileAttributes.class).lastModifiedTime());
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        Files.createDirectories(zip1);
        try (FileSystem zip = FileSystems.newFileSystem(zip1.toUri(), env)) {
            Path file = zip.getPath("/test.txt");
            Path file1 = zip.getPath("/test1.txt");
            Files.copy(source, file, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(source1, file1, StandardCopyOption.REPLACE_EXISTING);
            Files.write(file, (System.lineSeparator() + " Add Line").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("exception");
        }
    }
}
