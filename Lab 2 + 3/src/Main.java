import model.BST;
import model.CustomScanner;
import model.SymbolTable;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BST constants = new BST();
        BST identifiers = new BST();
        SymbolTable symbolTable = new SymbolTable(constants, identifiers);
        CustomScanner customScanner = new CustomScanner(symbolTable);
        customScanner.loadTokens();
        System.out.println(customScanner.scan());
    }
}
