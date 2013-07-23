public class Player
{
    final static String AUTO_NAME = "AutoPlayer";
    private String name ;
    private char playerSymbol ;
    private boolean bot = false ; //автоИгрок или нет

    Player ()
    {
        name = AUTO_NAME  ;
        bot = true ;
    }
    ///////////////////////////////
    Player (String newName)
    {
        name = newName ;
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
