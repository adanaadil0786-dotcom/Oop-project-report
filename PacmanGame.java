import java.util.Scanner;

// -------------------- PacmanGame (Main Class) --------------------
public class PacmanGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        GameBoard board = new GameBoard(10,10);

        Pacman pacman = new Pacman(1,1);

        Ghost ghost = new Ghost(7,7);

        Food food = new Food(4,5);

        boolean gameRunning = true;

        while(gameRunning)
        {
            board.displayBoard(pacman, ghost, food);

            System.out.println("Move Pacman (W=Up, S=Down, A=Left, D=Right): ");
            char move = input.next().charAt(0);

            pacman.move(move, board);

            ghost.moveTowardPacman(pacman, board);

            // Check if food eaten
            if(pacman.getX()==food.getX() && pacman.getY()==food.getY())
            {
                System.out.println("Pacman ate the food!");
                gameRunning=false;
            }

            // Check if ghost catches pacman
            if(pacman.getX()==ghost.getX() && pacman.getY()==ghost.getY())
            {
                System.out.println("Ghost caught Pacman! Game Over.");
                gameRunning=false;
            }
        }

        input.close();
    }
}


// -------------------- GameBoard Class --------------------
class GameBoard
{
    private int rows;
    private int cols;

    public GameBoard(int rows,int cols)
    {
        this.rows=rows;
        this.cols=cols;
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }

    public void displayBoard(Pacman p, Ghost g, Food f)
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(i==p.getX() && j==p.getY())
                    System.out.print("P ");

                else if(i==g.getX() && j==g.getY())
                    System.out.print("G ");

                else if(i==f.getX() && j==f.getY())
                    System.out.print("F ");

                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}


// -------------------- Pacman Class --------------------
class Pacman
{
    private int x;
    private int y;

    public Pacman(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public void move(char direction, GameBoard board)
    {
        switch(direction)
        {
            case 'W':
            case 'w':
                if(x>0)
                    x--;
                break;

            case 'S':
            case 's':
                if(x<board.getRows()-1)
                    x++;
                break;

            case 'A':
            case 'a':
                if(y>0)
                    y--;
                break;

            case 'D':
            case 'd':
                if(y<board.getCols()-1)
                    y++;
                break;

            default:
                System.out.println("Invalid Move");
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}


// -------------------- Ghost Class --------------------
class Ghost
{
    private int x;
    private int y;

    public Ghost(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public void moveTowardPacman(Pacman p, GameBoard board)
    {
        if(x < p.getX())
            x++;

        else if(x > p.getX())
            x--;

        if(y < p.getY())
            y++;

        else if(y > p.getY())
            y--;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}


// -------------------- Food Class --------------------
class Food
{
    private int x;
    private int y;

    public Food(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
