package com.smakyop.factions.dtr;

public enum  DTRStatus {

    FREEZED("Freezed"),
    RELOADING("Reloading"),
    MAXIMUM("Maximum");

    private String name;

    DTRStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
