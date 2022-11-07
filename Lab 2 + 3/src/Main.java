import model.BST;
import model.CustomScanner;
import model.SymbolTable;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BST constants = new BST();
        BST identifiers = new BST();
        SymbolTable symbolTable = new SymbolTable(constants, identifiers);
        CustomScanner customScanner = new CustomScanner(symbolTable);
        customScanner.loadTokens();
        System.out.println(customScanner.scan());
        customScanner.createPIF();
    }
}
