package com.company.cli;

import org.apache.commons.cli.*;

import java.util.AbstractMap;
import java.util.Optional;

public class CmdParser {
    private Options options;
    private HelpFormatter helpFormatter;
    private CommandLineParser parser;
    private static CmdParser INSTANCE;

    public static CmdParser getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CmdParser();
        return INSTANCE;
    }

    private CmdParser() {
        options = new Options();
        options.addOption("s", true, "source file")
            .addOption("d", true, "destination file");
        helpFormatter = new HelpFormatter();
        parser = new DefaultParser();
    }

    public AbstractMap.SimpleEntry<String, String> parseSourceAndDestination(String[] args) throws ParseException {
        return Optional.of(parser.parse(options, args))
            .filter(commandLine -> commandLine.hasOption("s") && commandLine.hasOption("d"))
            .map(commandLine -> new AbstractMap.SimpleEntry<>(commandLine.getOptionValue("s"), commandLine.getOptionValue("d")))
            .orElseThrow(() -> new ParseException("wrong arguments!"));
    }

    public void printHelp() {
        helpFormatter.printHelp("Usage:", options);
    }
}
