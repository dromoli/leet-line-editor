package com.diegoromoli.leet;

import lombok.RequiredArgsConstructor;

import static com.diegoromoli.leet.TextEditor.EMPTY;

@RequiredArgsConstructor
public class TextUtils {

    private static final String NORMAL_TEXT = (char)27 + "[30;0m";
    private static final String INVERSE_TEXT = (char)27 + "[97;40m";

    private final TextEditor textEditor;

    public void printText() {
        printText(EMPTY);
    }

    public void printText(String action) {
        String text = textEditor.getText();
        int cursorPosition = textEditor.getCursorPosition();
        String pre = NORMAL_TEXT + text.substring(0, Math.max(0, cursorPosition));
        String cursor = INVERSE_TEXT + (cursorPosition == text.length() ? ' ' : text.charAt(cursorPosition));
        String post = NORMAL_TEXT + (cursorPosition == text.length() ? EMPTY : text.substring(cursorPosition + 1));
        String textToPrint = resolveTextToPrint(action, pre, cursor, post);
        System.out.println(textToPrint + " | textEditor.getCursorPosition(): " + cursorPosition);
    }

    private String resolveTextToPrint(String action, String pre, String cursor, String post) {
        String textToPrint;
        if (action.isBlank()) {
            textToPrint = pre + cursor + post;
        } else {
            textToPrint = action + ": " + pre + cursor + post;
        }
        return textToPrint;
    }

}
