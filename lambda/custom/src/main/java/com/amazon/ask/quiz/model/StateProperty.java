package com.amazon.ask.quiz.model;

public enum StateProperty {

    NAME("name"),
    ABBREVIATION("abbreviation"),
    CAPITAL("capital"),
    STATEHOOD_YEAR("statehood year"),
    STATEHOOD_ORDER("statehood order");

    private String value;

    public String getValue() {
        return value;
    }

    StateProperty(String value) {
        this.value = value;
    }

}