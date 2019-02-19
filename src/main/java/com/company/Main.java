package com.company;

import com.company.cli.CmdParser;
import com.company.controller.Controller;
import com.company.controller.implementation.ApplicationController;
import com.company.controller.utils.FileUtils;
import org.apache.commons.cli.ParseException;

import java.util.AbstractMap;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        try {
            AbstractMap.SimpleEntry<String, String> sourceAndDestination = CmdParser
                .getInstance().parseSourceAndDestination(args);
            Controller controller = new ApplicationController();
            FileUtils.saveCsvFile(sourceAndDestination.getValue(),
                controller.scanFoldersList(FileUtils.readTextFile(sourceAndDestination.getKey())));
        } catch (ParseException | InterruptedException | ExecutionException e) {
            CmdParser.getInstance().printHelp();
            System.exit(0);
        }
    }
}
