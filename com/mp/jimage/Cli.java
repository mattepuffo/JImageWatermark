package com.mp.jimage;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {

    private String[] args;
    private Options options;
    private String path;

    public Cli(String[] args) {
        this.args = args;
        options = new Options();
        options.addOption("h", "help", false, "Mostra l'help");
        options.addOption("p", "path", true, "Directory delle immagini");
    }

    public void parseCli() throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);
        if (cl.hasOption("h")) {
            help();
        }
        if (cl.hasOption("p")) {
            path = cl.getOptionValue("p");
        } else {
            help();
        }
    }

    private void help() {
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp("Help", options);
        System.exit(0);
    }

    public String getPath() {
        return path;
    }
}
