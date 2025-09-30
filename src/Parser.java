/*
 * @file: Parser.java
 * @description: This class processes the input.txt file
 * @author: Zell Godbold
 * @date: September 25, 2025
 */

import java.io.*;
import java.util.*;

public class Parser {

    // BST of Batter objects
    private BST<Batter> mybst = new BST<>();

    // Map of players from CSV for quick lookup by name
    private Map<String, Batter> playerMap = new HashMap<>();


    public Parser(String csvFile, String inputFile) throws FileNotFoundException {
        // runs code for the csv
        loadCSV(csvFile);
        // processes the commands in input.txt
        process(new File(inputFile));
    }

    public void loadCSV(String csvFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // moves past the header line
            }

            // runs through all the players in the csv
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",");

                // splices the entire csv
                int id = Integer.parseInt(fields[0].trim());
                int rank = Integer.parseInt(fields[1].trim());
                int year = Integer.parseInt(fields[2].trim());
                String player = fields[3].trim();
                int battedBallEvents = Integer.parseInt(fields[4].trim());
                double launchAngle = Double.parseDouble(fields[5].trim());
                double sweetSpotPercentage = Double.parseDouble(fields[6].trim());
                double maxEV = Double.parseDouble(fields[7].trim());
                double averageEV = Double.parseDouble(fields[8].trim());
                double flyBallLineDriveEV = Double.parseDouble(fields[9].trim());
                double groundBallEV = Double.parseDouble(fields[10].trim());
                int maxDistance = Integer.parseInt(fields[11].trim());
                double averageDistance = Double.parseDouble(fields[12].trim());
                double averageHomerun = Double.parseDouble(fields[13].trim());
                int hardHit95mph = Integer.parseInt(fields[14].trim());
                double hardHitPercentage = Double.parseDouble(fields[15].trim());
                double hardHitSwingPercentage = Double.parseDouble(fields[16].trim());
                int totalBarrels = Integer.parseInt(fields[17].trim());
                double barrelsBattedBallsPercentage = Double.parseDouble(fields[18].trim());
                double barrelsPlateAppearancePercentage = Double.parseDouble(fields[19].trim());

                // builds a new batter for every line of the csv
                Batter batter = new Batter(id, rank, year, player,
                battedBallEvents, launchAngle, sweetSpotPercentage,
                maxEV, averageEV, flyBallLineDriveEV,
                groundBallEV, maxDistance, averageDistance,
                averageHomerun, hardHit95mph, hardHitPercentage,
                hardHitSwingPercentage, totalBarrels,
                barrelsBattedBallsPercentage, barrelsPlateAppearancePercentage);
                // hashmap for easier searching
                playerMap.put(player, batter);
            }
        }
    }


    public void process(File input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            // gets all the commands in the input.txt file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) continue;

                // Split command into at most 2 parts (command + argument)
                String[] command = line.split(" ", 2);

                operate_BST(command);
            }
        }
    }


    public void operate_BST(String[] command) {
        switch (command[0].toLowerCase()) {
            // case for adding a player
            case "insert":
                if (command.length == 2) {
                    // use of hashmap
                    Batter batter = playerMap.get(command[1].trim());
                    if (batter != null) {
                        if (mybst.search(batter) == null) {
                            mybst.insert(batter);
                            writeToFile("insert " + batter.getPlayer(), "./result.txt");
                        } else {
                            writeToFile("insert " + batter.getPlayer() + " (duplicate)", "./result.txt");
                        }
                    } else {
                        writeToFile("Player not found in CSV: " + command[1], "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;

            // case for search
            case "search":
                if (command.length == 2) {
                    Batter batter = playerMap.get(command[1].trim());
                    if (batter != null && mybst.search(batter) != null) {
                        writeToFile("found " + batter, "./result.txt");
                    } else {
                        writeToFile("search failed: could not find \"" + batter.getPlayer() + "\"", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;

            // case of remove
            case "remove":
                if (command.length == 2) {
                    Batter batter = playerMap.get(command[1].trim());
                    if (batter != null && mybst.remove(batter)) {
                        writeToFile("removed " + batter.getPlayer(), "./result.txt");
                    } else {
                        writeToFile("remove failed: could not find \"" + batter.getPlayer() + "\"", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;


            // case for print
            case "print":
                if (command.length == 1) {
                    writeToFile("print: (In Ascending order by avgEV)", "./result.txt");
                    String result = mybst.printInOrder();
                    writeToFile(result, "./result.txt");
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;

            // case for isEmpty
            /*
            case "isempty":
                if (command.length == 1) {
                    boolean result = mybst.isEmpty();
                    if (result) {
                        writeToFile("is empty", "./result.txt");
                    } else {
                        writeToFile("is not empty", "./result.txt");
                    }
                }
            */

            // if it is not filtered by one of the cases, it is an invalid command
            default:
                writeToFile("Invalid Command", "./result.txt");
        }
    }

    // file writer for result.txt
    public void writeToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
