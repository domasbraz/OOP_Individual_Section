package recyclingservice.data.player;

import java.io.Serializable;

public class PlayerHighScore implements Serializable
{
    private String[] playerValues = {"0","n/a","n/a","n/a"};

    public void setPlayerValues(String[] playerValues) 
    {
        this.playerValues = playerValues;
    }

    public String[] getPlayerValues() 
    {
        return playerValues;
    }
}