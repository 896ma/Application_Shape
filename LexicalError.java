//code has been modiied

// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

// Class that defines a lexical error

class LexicalError extends Exception {
    private int line;
    private String description;

    // Constructor that creates a lexical error object given the line number and error
    public LexicalError(int line, String description) {
        this.line = line;
        this.description = description;
    }

    // Override toString() method to provide custom error message format
    @Override
    public String toString() {
        return "Lexical Error on Line: " + line + " " + description;
    }
}