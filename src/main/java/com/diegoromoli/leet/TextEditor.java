package com.diegoromoli.leet;

class TextEditor {

    public static final String EMPTY = "";

    private String text;
    private int cursorPosition;

    public TextEditor() {
        this.text = "";
        this.cursorPosition = 0;
    }

    public void addText(String textToAdd) {
        this.text = allTextToLeftOfCursor() + textToAdd + allTextToRightOfCursor();
        this.cursorPosition = this.cursorPosition + textToAdd.length();
    }

    public int deleteText(int k) {
        int initialCursorPosition = cursorPosition;
        if (k > initialCursorPosition) {
            cursorPosition = 0;
        } else {
            cursorPosition = cursorPosition - k;
        }
        this.text = this.text.substring(0, cursorPosition) + this.text.substring(initialCursorPosition );
        return Math.min(k, initialCursorPosition);
    }

    public String cursorLeft(int k) {
        if (k > cursorPosition) {
            cursorPosition = 0;
        } else {
            cursorPosition = cursorPosition - k;
        }
        int min = Math.min(10, cursorPosition);
        return this.text.substring(cursorPosition - min, cursorPosition);
    }

    public String cursorRight(int k) {
        cursorPosition = Math.min(k + cursorPosition, text.length());
        int min = Math.min(10, cursorPosition);
        return this.text.substring(cursorPosition - min, cursorPosition);
    }

    private String allTextToLeftOfCursor() {
        if (text.isEmpty() || cursorPosition == 0) {
            return EMPTY;
        }
        return text.substring(0, cursorPosition);
    }

    private String allTextToRightOfCursor() {
        if (text.isEmpty() || cursorPosition == text.length()) {
            return EMPTY;
        }
        return text.substring(cursorPosition);
    }

    public String getText() {
        return text;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }
}
