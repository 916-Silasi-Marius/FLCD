package model;

public class SymbolTable {
    private final BST constants;
    private final BST identifiers;

    public SymbolTable(BST constants, BST identifiers) {
        this.constants = constants;
        this.identifiers = identifiers;
    }

    public void add(String value) {
        if (value.length() > 1) {
            char[] chars = value.toCharArray();
            boolean onlyLetters = true;
            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    onlyLetters = false;
                    break;
                }
            }

            if (onlyLetters) {
                constants.add(value);
            } else {
                identifiers.add(value);
            }
        } else {
            identifiers.add(value);
        }
    }

    public boolean findToken(String value) {
        return constants.containsNode(value) || identifiers.containsNode(value);
    }
}
