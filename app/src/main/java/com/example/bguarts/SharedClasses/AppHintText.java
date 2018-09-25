package com.example.bguarts.SharedClasses;

public class AppHintText extends AppHint {

    private String text;

    public AppHintText(int hintNumber, String text) {
        super(hintNumber);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
