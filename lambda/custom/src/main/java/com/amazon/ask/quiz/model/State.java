package com.amazon.ask.quiz.model;

public class State {

    private String name;
    private String abbreviation;
    private String capital;
    private String statehoodYear;
    private String statehoodOrder;

    public State() {}

    public State(String name, String abbreviation, String capital, String statehoodYear, String statehoodOrder) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.capital = capital;
        this.statehoodYear = statehoodYear;
        this.statehoodOrder = statehoodOrder;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getCapital() {
        return capital;
    }

    public String getStatehoodYear() { return statehoodYear; }

    public String getStatehoodOrder() {
        return statehoodOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setStatehoodYear(String statehoodYear) {
        this.statehoodYear = statehoodYear;
    }

    public void setStatehoodOrder(String statehoodOrder) {
        this.statehoodOrder = statehoodOrder;
    }

}
