public class Player
{
    private final static String AUTO_NAME = "AutoPlayer";
    private String name ;
    private char playerSymbol ;
    private boolean bot ; //автоИгрок или нет

    Player ()
    {
        name = AUTO_NAME  ;
    }
    ///////////////////////////////
    void setName (String newName)
    {
        name = newName ;
    }

    void setBot (boolean flag)
    {
        bot = flag ;
    }
    //////////////////////////////
    void setSymbol (char newSymbol)
    {
        playerSymbol = newSymbol ;
    }
    /////////////////////////
    String getName ()
    {
        return name ;
    }
    ///////////////////////////////
    char getPlayerSymbol ()
    {
        return playerSymbol ;
    }

    boolean getBot()
    {
        return bot ;
    }


}
