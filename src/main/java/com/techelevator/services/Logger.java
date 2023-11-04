package com.techelevator.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Logger {

    // ctor
    private String filePath;
    private File logFile;
    // needs a string that is the path of the file
    // instantiate a file
    public Logger(String path) throws FileNotFoundException {
        filePath = path;
        logFile = new File("Log.txt");
    }


    // writeToLog
    // method that takes a string and logs it to the file
    public void writeToLog(String message) {
        try(FileWriter fileWriter = new FileWriter(filePath, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter))

                //add totals from array[3]
        {
            printWriter.println(message);

        } catch (IOException e) {
            // how do we want to handle this exception?
        }
    }
    // readFromLog
    // method that returns an array of strings that are the lines of the file
    // reset the file scanner after the read so it can be used again
    public Collection<String> readFromLog() {
        try {
            Scanner logReader = new Scanner(logFile);

            // instantiate return list
            List<String> returnList = new ArrayList<>();

            // while the file has lines left
            while (logReader.hasNextLine()) {
                // read the line from the file
                String logLine = logReader.nextLine();

                //add totals from array[3]


                // add the line to the return list
                returnList.add(logLine);
            }
            // loop ends
            logReader.close();
            // return list
            return returnList;

        } catch (FileNotFoundException e) {
            // how do we handle this exception?
        }
        return null;
    }

    public void clearLog()
    {
        try {
            // apparently this deletes the whole file?
            // probably because PrintWriter's underlying FileWriter has append set to false
            // this means that whatever content the print writer has (none) gets written over the top of the file
            // this happens as soon as close is called i think?
            PrintWriter logWriter = new PrintWriter(logFile);
            logWriter.close();

        } catch (FileNotFoundException e) {
            // how do we handle this exception?
        }
    }
}
