/*
 * @file: Proj1.java
 * @description: This class checks for valid args length and passes it into the Parser.java class
 * @author: Zell Godbold
 * @date: September 25, 2025
 */

import java.io.FileNotFoundException;

// this class just processes the args and passes them into the parser.
// This version has to handle two different args statements: csv and input.txt
public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.println("Usage: java Proj1 <csv-file> <input-file>");
            System.exit(0);
        }
        new Parser(args[0], args[1]);
    }
}

