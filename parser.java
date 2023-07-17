
// Debug the code below to implemment the desired functionalities?

// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

// This class provides the skeleton parser for project 1

class Parser {
    private JFrame window;
    private Token token;
    private Lexer lexer;

    // Constructor to create new lexical analyzer from input file

    public Parser(File file) throws IOException {
        lexer = new Lexer(file);
    }

    // Parses the production
    // scene -> SCENE IDENTIFIER number_list images END '.'

    public Scene parseScene() throws LexicalError, SyntaxError, IOException {
        verifyNextToken(Token.SCENE);
        verifyNextToken(Token.IDENTIFIER);
        String windowName = lexer.getLexeme();
        int[] dimensions = getNumberList(2);
        Scene scene = new Scene(windowName, dimensions[0], dimensions[1]);
        parseImages(scene);
        verifyNextToken(Token.END);
        verifyNextToken(Token.PERIOD);
        return scene;
    }

    // Parses the following productions

    // images -> image images | image
    // image -> right_triangle | rectangle | parallelogram | regular_polygon | isosceles | text
    // right_triangle -> RIGHT_TRIANGLE COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
    // rectangle -> RECTANGLE_ COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
    // parallelogram -> PARALLELOGRAM COLOR number_list AT number_list number_list OFFSET NUMBER ';'
    // regular_polygon -> REGULAR_POLYGON COLOR number_list AT number_list SIDES NUMBER RADIUS NUMBER ';'
    // isosceles -> ISOSCELES COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
    // text -> TEXT COLOR number_list AT number_list STRING ';'

    private void parseImages(Scene scene) throws LexicalError, SyntaxError, IOException {
        Token imageToken = lexer.getNextToken();
        if (imageToken == Token.RIGHT_TRIANGLE || imageToken == Token.RECTANGLE || imageToken == Token.PARALLELOGRAM
                || imageToken == Token.REGULAR_POLYGON || imageToken == Token.ISOSCELES || imageToken == Token.TEXT) {
            parseImage(scene, imageToken);
            parseImages(scene);
        }
    }

    private void parseImage(Scene scene, Token imageToken) throws LexicalError, SyntaxError, IOException {
        int height, width, offset, radius;
        verifyNextToken(Token.COLOR);
        int[] colors = getNumberList(3);
        Color color = new Color(colors[0], colors[1], colors[2]);
        verifyNextToken(Token.AT);
        int[] location = getNumberList(2);
        Point point = new Point(location[0], location[1]);
        if (imageToken == Token.RIGHT_TRIANGLE) {
            verifyNextToken(Token.HEIGHT);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            width = lexer.getNumber();
            verifyNextToken(Token.SEMICOLON);
            RightTriangle rightTriangle = new RightTriangle(color, point, height, width);
            scene.addImage(rightTriangle);
        } else if (imageToken == Token.RECTANGLE) {
            verifyNextToken(Token.HEIGHT);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            width = lexer.getNumber();
            verifyNextToken(Token.SEMICOLON);
            Rectangle rectangle = new Rectangle(color, point, height, width);
            scene.addImage(rectangle);
        } else if (imageToken == Token.PARALLELOGRAM) {
            int[] secondLocation = getNumberList(2);
            Point secondPoint = new Point(secondLocation[0], secondLocation[1]);
            verifyNextToken(Token.OFFSET);
            offset = lexer.getNumber();
            verifyNextToken(Token.SEMICOLON);
            //Parallelogram parallelogram = new Parallelogram( color, point, secondPoint, offset);
            scene.addImage(parallelogram);
        } else if (imageToken == Token.REGULAR_POLYGON) {
            int sides = lexer.getNumber();
            verifyNextToken(Token.RADIUS);
            radius = lexer.getNumber();
            verifyNextToken(Token.SEMICOLON);
            RegularPolygon regularPolygon = new RegularPolygon(color, point, sides, radius);
            scene.addImage(regularPolygon);
        } else if (imageToken == Token.ISOSCELES) {
            verifyNextToken(Token.HEIGHT);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            width = lexer.getNumber();
            verifyNextToken(Token.SEMICOLON);
            Isosceles isosceles = new Isosceles(color, point, height, width);
            scene.addImage(isosceles);
        } else if (imageToken == Token.TEXT) {
            verifyNextToken(Token.STRING);
            String text = lexer.getLexeme();
            verifyNextToken(Token.SEMICOLON);
            Text textObj = new Text(color, point, text);
            scene.addImage(textObj);
        } else {
            throw new SyntaxError(lexer.getLineNo(), "Unexpected image name " + imageToken);
        }
    }

    // Parses the following production
    // number_list -> '(' numbers ')'
    // numbers -> NUMBER | NUMBER ',' numbers

    // Returns an array of the numbers in the number list

    private int[] getNumberList(int count) throws LexicalError, SyntaxError, IOException {
        int[] list = new int[count];
        verifyNextToken(Token.LEFT_PAREN);
        for (int i = 0; i < count; i++) {
            verifyNextToken(Token.NUMBER);
            list[i] = lexer.getNumber();
            if (i < count - 1) {
                verifyNextToken(Token.COMMA);
            }
        }
        verifyNextToken(Token.RIGHT_PAREN);
        return list;
    }

    // Verifies that the next token is the expected token

    private void verifyNextToken(Token expectedToken) throws LexicalError, SyntaxError, IOException {
        token = lexer.getNextToken();
        verifyCurrentToken(expectedToken);
    }

    // Verifies that the current token is the expected token

    private void verifyCurrentToken(Token expectedToken) throws SyntaxError {
        if (token != expectedToken) {
            throw new SyntaxError(lexer.getLineNo(),
                    "Expecting token " + expectedToken + " not " + token);
        }
    }
}
