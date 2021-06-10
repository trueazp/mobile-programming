package com.miaowmere.finalproject_h071191035.utilities;

public enum ImageSize {
    W154("w154"),
    W200("w200"),
    W342("w342");

    private String value;

    ImageSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
