public class Field {
    public final static int FIELD_SIZE = 3 ;
    private final static int POINTS = 3 ;
    private final static int MIN = 0 ;
    private final static char EMPTY_CELL = ' ' ;

    private char [][] field ;

    Field()
    {
        field = new char [FIELD_SIZE] [FIELD_SIZE] ;
        for(int i = 0; i < FIELD_SIZE; i++)
            for(int j = 0; j < FIELD_SIZE; j++)
                field [i] [j] = EMPTY_CELL ;
    }
    int setSymbol (int [] coordinate, char playerSymbol)
    {
        int x = coordinate [0] - 1;
        int y = coordinate [1] - 1;
        if (x >= MIN && x < FIELD_SIZE)
        {
            if (y >= MIN && y < FIELD_SIZE)
            {
                if (field [y] [x] == EMPTY_CELL)
                {
                    field [y] [x] = playerSymbol ;
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
        for (int i = 1 ; i <= FIELD_SIZE ; i++)
            System.out.print("   " + i);
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

    int check (int [] coordinate, char symbol)
    {
        int x = coordinate [0] - 1;
        int y = coordinate [1] - 1;
        if (x==y)//главная диагональ
        {
           if (check_diag1(symbol)==POINTS)
               return 1 ;
        }

        if ((x+y)==(FIELD_SIZE-1))
        {
            if (check_diag2(symbol)==POINTS)
                return 1 ;
        }

        if (check_line(y, symbol)==POINTS)
            return 1 ;

        if (check_column(x, symbol)==POINTS)
            return 1 ;

        if(check_draw())
           return 2 ;

        return 0 ;
    }

    int check_diag1 (char symbol)
    {
        int count = 0 ;
        for (int i = 0; i < FIELD_SIZE ; i++)
        {
            if(symbol == field [i] [i])
            {
                count++ ;
            }
            else
            {
                if (field [i] [i]!=EMPTY_CELL)
                    count-- ;
            }
        }
        return count ;
    }

    int check_diag2 (char symbol)
    {
        int count = 0 ;
        for (int i = 0; i < FIELD_SIZE ; i++)
        {
            if(symbol == field [i] [FIELD_SIZE-1-i])
            {
                count++ ;
            }
            else
            {
                if (field [i] [FIELD_SIZE-1-i]!=EMPTY_CELL)
                    count-- ;
            }
        }
        return count ;
    }

    int check_line (int y, char symbol)
    {
        int count = 0 ;
        for (int i = 0; i < FIELD_SIZE ; i++)
        {
            if(symbol == field [y] [i])
            {
                count++ ;
            }
            else
            {
                if (field [y] [i]!=EMPTY_CELL)
                    count-- ;
            }
        }
        return count ;
    }

    int check_column (int x, char symbol)
    {
        int count = 0 ;
        for (int i = 0; i < FIELD_SIZE ; i++)
        {
            if(symbol == field [i] [x])
            {
               count++;
            }
            else
            {
                if (field [i] [x]!=EMPTY_CELL)
                    count-- ;
            }

        }
        return count ;
    }
    boolean check_draw ()
    {
        for(int i = 0; i < FIELD_SIZE; i++)
        {
            for(int j = 0; j < FIELD_SIZE; j++)
            {
                if(field [i] [j] == EMPTY_CELL)
                {
                    return false ;
                }
            }
        }
        return true ;

    }

    int [] autoPlay (char symbol)
    {
        int [] coordinate = new int [2] ;
        int [] [] matrix_weight = new int [FIELD_SIZE][FIELD_SIZE] ;
        for(int i = 0; i < FIELD_SIZE; i++)
            for(int j = 0; j < FIELD_SIZE; j++)
            {
                if (field [i] [j] != EMPTY_CELL)
                {
                    matrix_weight [i] [j] = FIELD_SIZE ;
                    continue;
                }
                int size = 4 ;
                int [] count = new int [size] ;

                count [0] = check_line(i,symbol) ;
                count [1] = check_column(j,symbol);
                if (i==j)
                    count [2] = check_diag1(symbol);
                else
                    count [2] = FIELD_SIZE ;
                if ((i+j)==(FIELD_SIZE-1))
                    count [3] = check_diag2(symbol);
                else
                    count [3] = FIELD_SIZE ;
                int index = 0 ;
                for (int k = 0; k < size; k++)
                {
                    if (count [k]==(POINTS-1))
                    {
                        coordinate [0] = j + 1 ;
                        coordinate [1] = i + 1 ;
                        return coordinate ;
                    }

                    if(count [index] >  count [k])
                        index = k ;
                }

                matrix_weight [i] [j] = count [index] ;
            }
        int minX = 0, minY = 0 ;
        for(int i = 0; i < FIELD_SIZE; i++)
            for(int j = 0; j < FIELD_SIZE; j++)
            {
                if(matrix_weight [minY] [minX] > matrix_weight[i][j])
                {
                    minX = j ;
                    minY = i ;
                }
            }
        coordinate [0] = minX + 1 ;
        coordinate [1] = minY + 1 ;

        return coordinate ;

    }


}
