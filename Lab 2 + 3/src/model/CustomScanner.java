package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomScanner {
    private SymbolTable symbolTable;

    public CustomScanner(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void loadTokens() throws FileNotFoundException {
        File file = new File("data/token.in");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace("\n", "");
            symbolTable.add(line);
        }
        scanner.close();
    }

    public String scan() throws FileNotFoundException {
        File file = new File("data/p1err.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace("\n", "");
            line = line.replace("\t", "");
//            for (char c : line.replaceAll("\\s", "").toCharArray()) {
//                if (!symbolTable.findToken(String.valueOf(c))) {
//                    return "PROBLEM AT VALUE: " + c;
//                }
//            }

            for (String s : line.split(" ")) {
                if (!symbolTable.findToken(s)) {
                    return "PROBLEM AT VALUE: " + s;
                }
            }
        }
        return "CORRECT";
    }
}
