package com.diegoromoli.leet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextEditorTest {

    public static final String ORIGINAL_TEXT = "At its core is an interpreter for Emacs Lisp";

    private TextEditor stonedit;
    private TextUtils textUtils;

    @BeforeEach
    public void setup() {
        stonedit = new TextEditor();
        textUtils = new TextUtils(stonedit);
    }

    @Test
    public void testCursorRightSimple() {
        stonedit.addText(ORIGINAL_TEXT);
        stonedit.cursorLeft(1000);
        stonedit.cursorRight(5);
        assertEquals('s', stonedit.getText().charAt(stonedit.getCursorPosition()));
    }

    @Test
    public void testCursorRightThenLeft() {
        stonedit.addText(ORIGINAL_TEXT);
        stonedit.cursorLeft(1000);
        stonedit.cursorRight(15);
        assertEquals('a', stonedit.getText().charAt(stonedit.getCursorPosition()));
        stonedit.cursorLeft(2);
        assertEquals('s', stonedit.getText().charAt(stonedit.getCursorPosition()));
    }

    @Test
    public void testDeleteText() {
        int charsToDelete = 12;
        stonedit.addText(ORIGINAL_TEXT);
        stonedit.cursorLeft(1000);
        int originalTextLength = stonedit.getText().length();
        stonedit.cursorRight(29);
        assertEquals(' ', stonedit.getText().charAt(stonedit.getCursorPosition()));
        int deletedChars = stonedit.deleteText(charsToDelete);
        assertEquals(originalTextLength - charsToDelete, stonedit.getText().length());
        assertEquals("At its core is an for Emacs Lisp", stonedit.getText());
        assertEquals(charsToDelete, deletedChars);
    }

    @Test
    public void testAddText() {
        String textToAdd = " interpreter";
        stonedit.addText("At its core is an for Emacs Lisp");
        int originalTextLength = stonedit.getText().length();
        stonedit.cursorLeft(15);
        stonedit.addText(textToAdd);
        assertEquals(originalTextLength + textToAdd.length(), stonedit.getText().length());
        assertEquals(ORIGINAL_TEXT, stonedit.getText());
    }

    @Test
    public void testLeetCode() {
        stonedit.addText("leetcode");
        textUtils.printText("addText('leetcode')");
        int deletedChars = stonedit.deleteText(4);
        textUtils.printText("deleteText(4)");
        assertEquals("leet", stonedit.getText());
        assertEquals(4, deletedChars);
        stonedit.addText("practice");
        textUtils.printText("addText('practice')");
        String cursorRight = stonedit.cursorRight(3);
        textUtils.printText("cursorRight(3)");
        assertEquals("etpractice", cursorRight);
        String cursorLeft = stonedit.cursorLeft(8);
        textUtils.printText("cursorLeft(8)");
        assertEquals("leet", cursorLeft);
        deletedChars = stonedit.deleteText(10);
        textUtils.printText("deleteText(10)");
        assertEquals("practice", stonedit.getText());
        assertEquals(4, deletedChars);
        cursorLeft = stonedit.cursorLeft(2);
        textUtils.printText("cursorLeft(2)");
        assertEquals("", cursorLeft);
    }
}
