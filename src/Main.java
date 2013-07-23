import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Field field = new Field() ;
        Player [] players = new Player [2];
        int playersNumber; //число игроков
        int index = 0 ; // вспомогательная переменная
        Scanner in = new Scanner(System.in);
        int x = 0, y = 0 ; //координаты
        Random randomGenerator = new Random();

        System.out.println("Welcome to the Tic-Tac-Toe!");

        while (true)
        {
            System.out.print("Enter number of players (1 or 2): ");
            playersNumber = in.nextInt();
            if (playersNumber > 0 && playersNumber <= 2)
                break ;
            else
                System.out.println("Error! Incorrect number of players");
        }

        for (int i = 0; i < playersNumber; i++)
        {
            System.out.println("Enter player #" + (i + 1) + " name: ");
            String name = in.next();
            players [i] = new Player(name);
        }
        if (playersNumber == 1)
            players [1] = new Player() ;

        if (Math.random() > 0.5)
            index = 1 ;
        players [index].setSymbol('X');
        players [index^1].setSymbol('O');

        for (int i = 0; i < 2; i++)
            System.out.println(players [i].getName() + " plays with symbol '" + players[i].getPlayerSymbol() + "'");

        System.out.println("Field looks like this:");
        field.showField();

        for (int i = 1 ; ;i++)
        {
            System.out.println("Round #" + (i));

            System.out.println(players[index].getName()+ " turns");

            while (true)
            {
                if (players[index].getBot())
                {
                    x = randomGenerator.nextInt(3)+1;
                    y = randomGenerator.nextInt(3)+1;
                }
                else
                {
                    System.out.print("Enter coordinate 'x': ");
                    x = in.nextInt() ;
                    System.out.print("Enter coordinate 'y': ");
                    y = in.nextInt() ;
                }

                int result = field.setSymbol(x,y,players[index].getPlayerSymbol());
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
            int res = field.check(x-1,y-1) ;
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



            index = index^1;

        }

        System.out.println("Game over!");

    }
}
