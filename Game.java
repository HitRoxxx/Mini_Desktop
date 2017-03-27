import java.util.List;
import java.util.ArrayList;
class Game
{
	int[][] board = new int[3][3];
	List<Point> availablePoints = getAvailableStates();
    

	
	public boolean isMovePossible() 
	{
        for (int x = 0; x < 3; x++) 
		{
            for (int y = 0; y < (3 - 1); y++) 
			{
                int yy = y + 1;
                if (board[x][y] == board[x][yy]) 
				{
                    return false;
                }
            }
        }
         
        for (int y = 0; y < 3; y++) 
		{
            for (int x = 0; x < (3 - 1); x++) 
			{
                int xx = x + 1;
                if (board[x][y] == board[xx][y]) 
				{
                    return false;
                }
            }
		}
		return true ;
	}
    public boolean hasWon() 
	{
        for (int i = 0; i < 3; i++) 
		{
			 for (int j = 0; j < 3; j++) 
			 {
				 if(board[i][j]==2048)
				 {
					 return true;
				 }
			 }
		}
        return false;
    }

    public List<Point> getAvailableStates() 
	{
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; i++) 
		{
            for (int j = 0; j < 3; j++) 
			{
                if (board[i][j] == 0) 
				{
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(Point point, int value) 
	{
        board[point.x][point.y] = value;  
    } 
    
}