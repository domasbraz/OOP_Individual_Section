package recyclingservice.data.leaderboard;

import java.io.Serializable;

public class LeaderBoardData implements Serializable
{

    private String[][] leaderboadValues = {

        {"850","DB2023","Domas","Brazdeikis"},
        {"840","AS2023","Aaron","Smyth"},
        {"830","AP2023","Adam","Plesca"},
        {"820","DG2023","David","Gevorkyan"},
        {"800", "TS2023", "Tom", "Smith"},
        {"750", "JP2023", "John", "Parker"},
        {"700", "MD2023", "Mark", "Davis"},
        {"650", "AW2023", "Alice", "Wonder"},
        {"400", "BW2023", "Bob", "Williams"},
        {"300", "JH2023", "Jane", "Harrison"},
        {"0", "n/a", "n/a", "n/a"} //user score

    };

    public void setLeaderboadValues(String[][] leaderboadValues) 
    {
        this.leaderboadValues = leaderboadValues;
    }

    public String[][] getLeaderboadValues() 
    {
        return leaderboadValues;
    }


}
