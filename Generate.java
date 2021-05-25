import java.io.FileWriter;
import java.io.IOException;

public class Generate {

    int nCols;
    int nRows;

    boolean[][] board;

    // Function of calculating next state of every cell
    public void step()
    {
        boolean[][] newBoard = new boolean[nRows][nCols];
        for(int x = 0; x < nRows; x++)
        {
            for(int y = 0; y < nCols; y++)
            {
                int aliveNeighbours = numberOfNeighbours(x,y);

                if(board[x][y])
                {
                    if(aliveNeighbours == 2 || aliveNeighbours == 3)
                        newBoard[x][y] = true;
                }else
                {
                    if(aliveNeighbours == 3)
                        newBoard[x][y] = true;
                }
            }
        }

        board = newBoard;

    }

    // Counts number of neighbours of cell with x,y co-ordinates
    public int numberOfNeighbours(int x, int y)
    {
        int number = 0;

        if(x-1 >= 0 && board[x-1][y])
            number++;
        if(x-1 >= 0 && y-1 >= 0 && board[x-1][y-1])
            number++;
        if(x-1 >= 0 && y+1 < nCols && board[x-1][y+1])
            number++;

        if(y-1 >= 0 && board[x][y-1])
            number++;
        if(y+1 < nCols && board[x][y+1])
            number++;

        if(x+1 < nRows && board[x+1][y])
            number++;
        if(x+1 < nRows && y-1 >= 0 && board[x+1][y-1])
            number++;
        if(x+1 < nRows && y+1 < nCols && board[x+1][y+1])
            number++;

        return number;

    }

    // Saves current state of the board to a file
    public void saveToFile(String fileName)
    {

        try{
            FileWriter myWriter = new FileWriter(fileName);

            for(int i = 0; i < nRows; i++)
            {
                for(int j = 0; j < nCols; j++)
                {
                    myWriter.write(board[i][j] ? " 1\n" : " 0\n");
                }
            }

            myWriter.close();
        }catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }


    }

    // When user clicks on a cell, function change its state to opposite
    public void setCell(int x, int y)
    {
        if(x >= 0 && x < nRows && y >= 0 && y < nCols)
        {
            board[x][y] = !board[x][y];
        }
    }

    // Turns off every cell
    void clearBoard()
    {
        board = new boolean[nRows][nCols];
    }

    public Generate(int r, int c)
    {
        nRows = r+6;
        nCols = c+6;
        board = new boolean[nRows][nCols];
    }

    public void setBoard(boolean[][] b)
    {
        this.board = b;
    }

    public boolean[][] getBoard() {
        return board;
    }
}
