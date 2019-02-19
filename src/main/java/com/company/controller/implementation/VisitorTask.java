package com.company.controller.implementation;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.AbstractMap;
import java.util.concurrent.Callable;

public class VisitorTask implements Callable<AbstractMap.SimpleEntry<String, Integer>> {

    private String sourceFolderPath;
    private FileCountVisitor visitor;

    VisitorTask(String sourceFolderPath) {
        this.sourceFolderPath = sourceFolderPath;
        visitor = new FileCountVisitor();
    }

    @Override
    public AbstractMap.SimpleEntry<String, Integer> call() throws Exception {
        Path path = Paths.get(sourceFolderPath);
        Files.walkFileTree(path, visitor);
        System.out.println(sourceFolderPath + "\t" + visitor.getCounter());
        return new AbstractMap.SimpleEntry<>(sourceFolderPath, visitor.getCounter());
    }

    private class FileCountVisitor extends SimpleFileVisitor<Path> {

        private int counter;

        int getCounter() {
            return counter;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            counter++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
    }

}
