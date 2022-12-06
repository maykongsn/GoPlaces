package com.goplaces.model;

public class Image {
    private String source;
    private int index;

    public Image(String source, int index) {
        this.source = source;
        this.index = index;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
