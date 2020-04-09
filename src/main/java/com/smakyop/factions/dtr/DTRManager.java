package com.smakyop.factions.dtr;

import com.smakyop.factions.faction.Faction;

public class DTRManager {

    private Faction faction;
    private DTRStatus dtrStatus;

    public DTRManager(Faction faction){
        this.faction = faction;
        this.dtrStatus = DTRStatus.MAXIMUM;
    }

    public Faction getFaction() {
        return faction;
    }

    public DTRStatus getDtrStatus() {
        return dtrStatus;
    }
}
