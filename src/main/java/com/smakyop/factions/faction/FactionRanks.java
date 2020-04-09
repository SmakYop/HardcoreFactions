package com.smakyop.factions.faction;

public enum FactionRanks {

    NO_FACTION(0, ""),
    MEMBRE(1, ""),
    OFFICIER(2, "*"),
    COLEADER(3, "*"),
    LEADER(4, "**");

    private int level;
    private String name;

    FactionRanks(int level, String name){
        this.level = level;
        this.name = name;
    }

    public int getLevel(){
        return level;
    }

    public String getName(){
        return name;
    }

    public static FactionRanks getRankByLevel(int level) {
        for (FactionRanks rank : values())
            if (rank.getLevel() == level)
                return rank;
        return null;
    }
}
