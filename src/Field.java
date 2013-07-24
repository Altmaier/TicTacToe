public class Field {
    private final static char FIELD_SIZE = 3 ;
    private final static char MIN = 0 ;
    private char [][] field ;

    Field()
    {
        field = new char [FIELD_SIZE] [FIELD_SIZE] ;
        for(int i = 0; i < FIELD_SIZE; i++)
            for(int j = 0; j < FIELD_SIZE; j++)
                field [i] [j] = ' ' ;
    }
    int setSymbol (int x, int y, char playerSymbol)
    {
        if (x > MIN && x <= FIELD_SIZE)
        {
            if (y > MIN && y <= FIELD_SIZE)
            {
                if (field [y-1] [x-1] == ' ')
                {
                    field [y-1] [x-1] = playerSymbol ;
                    return 0 ;
                }
                else
                    return 1;
            }
            else
                return 2;
        }
        else
            return 3 ;
    }

    void showField (){
        System.out.print("   " + 1 + "   " + 2 + "   " + 3);
        System.out.println();
        for(int i = 0; i < FIELD_SIZE; i++)
        {
            System.out.print((i+1)+" ");
            for(int j = 0; j < FIELD_SIZE; j++)
            {
                System.out.print("[" + field [i] [j] + "]" + " ");
            }

            System.out.println();
        }
    }

    int check (int x, int y)
    {
       if (x==y || Math.abs(x-y)==2)
       {
            if(field [0] [0]== field [1] [1] && field [2][2]==field[1][1])
                return 1 ;
            if(field [0] [2]== field [1] [1] && field [2][0]==field[1][1])
                return 1 ;
       }

       if(field [y] [0] == field [y] [1] && field [y] [0] == field [y] [2])
           return 1 ;

       if(field [0] [x] == field [1] [x] && field [0] [x] == field [2] [x])
           return 1 ;


       boolean flag = true ;
       for(int i = 0; i < FIELD_SIZE; i++)
           for(int j = 0; j < FIELD_SIZE; j++)
               if(field [i] [j] == ' ')
                  flag = false ;
       if(flag)
           return 2 ;


       return 0 ;
    }
}
