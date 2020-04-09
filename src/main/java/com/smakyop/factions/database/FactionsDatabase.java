package com.smakyop.factions.database;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.faction.Faction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FactionsDatabase {

    public boolean nameExists(String factionName){
        try {
            PreparedStatement statement = HardcoreFactions.getInstance().getDatabaseManager().createStatement("SELECT id FROM factions WHERE name = ?");
            statement.setString(1, factionName);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            boolean exists = resultSet.next();
            statement.close();
            return exists;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void createFaction(String name){
        Faction faction = Faction.getFaction(name);
        try {
            PreparedStatement statement = HardcoreFactions.getInstance().getDatabaseManager().createStatement("INSERT INTO factions " +
                    "(name, owner_uuid, creation_date, dtr, dtr_max, dtr_freezed, kills, points, money, description) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
            statement.setString(1, name);
            statement.setString(2, faction.getOwnerUUID().toString());
            statement.setLong(3, faction.getCreationDate());
            statement.setDouble(4, faction.getDtr());
            statement.setDouble(5, faction.getMaxDtr());
            statement.setBoolean(6, faction.isDtrFreezed());
            statement.setInt(7, faction.getKills());
            statement.setInt(8, faction.getPoints());
            statement.setDouble(9, faction.getMoney());
            statement.setString(10, faction.getDescription());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @param element need to be a correct value
     * @param factionName represents faction you want to get
     */
    public Object get(String element, String factionName){
        Faction faction = Faction.getFaction(factionName);
        if(faction == null){
            HardcoreFactions.getInstance().log("ERROR: faction is null. (FactionsDatabase:56)");
            return null;
        }

        try{
            PreparedStatement statement = HardcoreFactions.getInstance().getDatabaseManager().createStatement("SELECT ? FROM factions WHERE name = ?");
            statement.setString(1, element);
            statement.setString(2, factionName);

            ResultSet result = statement.executeQuery();
            if(!result.next()){
                HardcoreFactions.getInstance().log("ERROR: result is null. (FactionsDatabase:67)");
                return null;
            }

            Object o = result.getObject(element);
            statement.close();
            return o;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<String> getRegisteredFactions(){
        ArrayList<String> factions = new ArrayList<>();
        int id = 0;
        try {
            PreparedStatement statement = HardcoreFactions.getInstance().getDatabaseManager().createStatement("SELECT name FROM factions");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                id++;
                PreparedStatement statementId = HardcoreFactions.getInstance().getDatabaseManager().createStatement("SELECT name FROM factions WHERE id = ?");
                statementId.setInt(1,id);
                ResultSet resultId = statementId.executeQuery();
                statementId.close();
                factions.add(resultId.getString("name"));
            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return factions;
    }
}
