package com.company.controller.utils;

import com.company.entity.ResultEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileUtils {

    private FileUtils() {
    }

    public static List<String> readTextFile(String source) {
        List<String> items = new ArrayList<>();
        try {
            Files.lines(Paths.get(source)).forEach(items::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void saveCsvFile(String destination, List<ResultEntity> resultList) {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(destination)))) {
            resultList.forEach(resultEntity ->
                writer.println(resultEntity.getSourceFolderPath() + ";" + resultEntity.getSubitemsCount()));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
