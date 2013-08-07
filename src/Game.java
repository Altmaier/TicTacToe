import java.util.Scanner;

public class Game
{
    private static final int PLAYER_COUNT = 2 ;
    Scanner in ;
    private Field field ;
    private Player [] players ;
    int index; //индекс текущего игрока
    Coord [] history = new Coord [(int)Math.pow(Field.FIELD_SIZE, 2)] ;

    Game ()
    {
        System.out.println("Welcome to the Tic-tac-toe!");
        field = new Field() ;
        players = new Player [PLAYER_COUNT] ;
        for (int i = 0; i < PLAYER_COUNT; i++)
            players [i] = new Player() ;
        in = new Scanner(System.in) ;
        setPlayers () ;
        setPlayerSymbols();
        System.out.println("Field looks like this:");
        field.showField();
    }

    private void setPlayers ()
    {
        int playersNumber ;

        while (true)
        {
            System.out.print("Enter number of players (1 or 2): ");
            playersNumber = in.nextInt();
            if (playersNumber > 0 && playersNumber <= PLAYER_COUNT)
                break ;
            else
                System.out.println("Error! Incorrect number of players");
        }

        if (playersNumber == 1)
            players [PLAYER_COUNT-1].setBot(true);

        for (int i = 0; i < PLAYER_COUNT; i++)
        {
             if (!players[i].getBot())
             {
                 System.out.println("Enter player #" + (i + 1) + " name: ");
                 players [i].setName(in.next());
             }
        }

    }

    private void setPlayerSymbols ()
    {
        index = 0 ;
        if (Math.random() > 0.5)
            index = 1 ;
        players[index].setSymbol('X');
        players[index^1].setSymbol('O');

        for (int i = 0; i < PLAYER_COUNT; i++)
            System.out.println(players [i].getName() + " plays with symbol '" + players[i].getPlayerSymbol() + "'");
    }

    private void save (Coord coord, int i)
    {
        history [i] = coord ;

    }

    private void returnTo (int i)
    {
        field.empty();
        for (int j = 0; j < PLAYER_COUNT; j++)
            if (players[j].getPlayerSymbol() == 'X')
            {
                index = j ;
                break ;
            }
        for (int j = 0 ; j < i ; j++)
        {
            field.setSymbol(history[j],players[index].getPlayerSymbol());
            index=index^1 ;
        }

        System.out.println("Now field looks like this:");
        field.showField();
    }

    public void game ()
    {
        for (int i = 1 ; ;i++)
        {

            int res = round (i) ;

            System.out.println("Do you want to return to previous step? (Yes/No: ");
            String answer = in.next() ;
            if (answer.equals("Yes"))
            {
                System.out.println("Enter round number (or 0): ");
                int number = in.nextInt();
                if (number >= 0 && number < i)
                {
                    returnTo(number);
                    i = number ;
                }
                else
                    System.out.println("Wrong input!");
            }

            if(res == 1)
            {
                System.out.println("The winner is " + players [index].getName() + "!");
                break ;
            }
            if (res == 2)
            {
                System.out.println("Won the friendship!");
                break ;
            }

        }
        System.out.println("Game over!");

    }

    private int round (int i)
    {
        Coord temp = new Coord() ; //координаты
        System.out.println("Round #" + (i));

        System.out.println(players[index].getName()+ " turns");

        while (true)
        {
            if (players[index].getBot())
            {
                temp = field.autoPlay(players[index].getPlayerSymbol()) ;
            }
            else
            {
                System.out.print("Enter coordinate 'x': ");
                temp.x = in.nextInt() ;
                System.out.print("Enter coordinate 'y': ");
                temp.y = in.nextInt() ;
            }

            int result = field.setSymbol(temp,players[index].getPlayerSymbol());
            if (result == 0)
                break ;
            switch (result)
            {
                case 1: System.out.println("This cell is already occupied"); break;
                case 2: System.out.println("This coordinate 'y' is incorrect"); break;
                case 3: System.out.println("This coordinate 'x' is incorrect"); break ;
            }
        }

        System.out.println("Now field looks like this:");
        field.showField();

        save(temp, i-1) ;

        index = index^1;

        int res = field.check(temp, players[index].getPlayerSymbol()) ;
        return res ;

    }
}
