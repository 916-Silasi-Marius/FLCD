package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomScanner {
    private SymbolTable symbolTable;
    private List<String> tokens;
    Pattern identifierPattern = Pattern.compile("^[a-z][0-9]$");
    Pattern constantPattern = Pattern.compile("^[0-9]+");
    List<String> inOrderConstants;
    List<String> inOrderIdentifiers;

    public CustomScanner(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.tokens = new ArrayList<>();
        this.inOrderConstants = new ArrayList<>();
        this.inOrderIdentifiers = new ArrayList<>();
    }

    public void loadTokens() throws FileNotFoundException {
        File file = new File("data/token.in");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace("\n", "");
            tokens.add(line);
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

            for (String s : line.split(" ")) {
                boolean ok = false;
                if (tokens.contains(s)) {
                    ok = true;
                } else {
                    Matcher matcher = identifierPattern.matcher(s);
                    if (matcher.matches()) {
                        symbolTable.addIdentifier(s);
                        ok = true;
                    }
                    matcher = constantPattern.matcher(s);
                    if (matcher.matches()) {
                        symbolTable.addConstant(s);
                        ok = true;
                    }
                }
                if (!ok) {
                    return "PROBLEM AT: " + s;
                }
            }
        }
        return "CORRECT";
    }

    public void createPIF() throws IOException {
        inOrder(symbolTable.getConstants().root, "constants");
        inOrder(symbolTable.getIdentifiers().root, "identifiers");

        FileWriter myWriter = new FileWriter("data/PIF.txt");
        for (String constant : inOrderConstants) {
            myWriter.write("constant: " + constant + "; position: " + inOrderConstants.indexOf(constant) + '\n');
        }
        for (String identifier : inOrderIdentifiers) {
            myWriter.write("identifier: " + identifier + "; position: " + inOrderIdentifiers.indexOf(identifier) + '\n');
        }
        myWriter.close();
    }

    private void inOrder(Node node, String symTable) {
        if (node == null) {
            return;
        }

        inOrder(node.left, symTable);
        if (symTable.equals("constants")) {
            inOrderConstants.add(node.value);
        } else if (symTable.equals("identifiers")) {
            inOrderIdentifiers.add(node.value);
        }
        inOrder(node.right, symTable);
    }
}
