import java.awt.Color;

/**
 * Tetris class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tetris implements ArrowListener 
{
    public static void main(String[] args)
    {
        Tetris tetris = new Tetris();
        tetris.play();
    }

    private BoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad activeTetrad;
    private int score=0;
    public Tetris()
    {
        grid = new BoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris Score: "+score);
        activeTetrad = new Tetrad(grid);
        display.setArrowListener(this);
    }

	@Override
	public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
		
	}

	@Override
	public void downPressed() {
		activeTetrad.translate(1,0);
		display.showBlocks();
	}

	@Override
	public void leftPressed() {
		activeTetrad.translate(0,-1);
		display.showBlocks();
	}

	@Override
	public void rightPressed() {
		activeTetrad.translate(0,1);
		display.showBlocks();
	}

	@Override
	public void spacePressed() {
		while(activeTetrad.translate(1,0)){
			display.showBlocks();
		}
		
	}

    public void play()
    {
        while (true)
        {
            try { Thread.sleep(1000); } catch(Exception e) {}

            //Insert Exercise 3.2 code here
            if(!activeTetrad.translate(1,0))
            {
            	activeTetrad= new Tetrad(grid); 
            	clearCompletedRows();
            	display.setTitle("Tetris Score: "+score);
            }
            if(!topRowsEmpty())
            {
            	display.setTitle("GAME OVER");
            	break;
            }
            //Insert Exercise 3.3 code here
            
            display.showBlocks();
        }
    }

    //precondition:  0 <= row < number of rows
    //postcondition: Returns true if every cell in the
    //               given row is occupied;
    //               returns false otherwise.
    private boolean isCompletedRow(int row)
    {
    	for(int i=1;i<grid.getNumCols();i++)	
    	{
    		if(grid.get(new Location(row,i-1))==null || grid.get(new Location(row,i))==null)
    			return false;
    		if(!grid.get(new Location(row,i-1)).getColor().equals(grid.get(new Location(row,i)).getColor()))
    			return false;
    	}
    	return true;
    	
    }

    //precondition:  0 <= row < number of rows;
    //               given row is full of blocks
    //postcondition: Every block in the given row has been
    //               removed, and every block above row
    //               has been moved down one row.
    private void clearRow(int row)
    {
    	for(int i=0;i<grid.getNumCols();i++)	
    	{
    		grid.get(new Location(row,i)).removeSelfFromGrid();
    	}
    	for(int i=grid.getNumRows()-2;i>=0;i--){
    		for(int col=grid.getNumCols()-1;col>=0;col--){
    			if(grid.get(new Location(i,col))!=null)
    			{    				
    				Block block=grid.get(new Location(i,col));
    				block.putSelfInGrid(grid, new Location(i+1,col));
    				grid.remove(new Location(i,col));
    			}
        	}
    	}
    }

    //postcondition: All completed rows have been cleared.
    private void clearCompletedRows()
    {
    	boolean cleared=false;
    	for(int row=grid.getNumRows()-1;row>=0;row--)
    	{
    		if(cleared)
    			row+=1;//clear the same row if shifted
    	 if(isCompletedRow(row)){
         	clearRow(row);
         	score+=1;
         	cleared=true;
         	continue;
         }
    	cleared=false;
    	}
    }

    //returns true if top two rows of the grid are empty (no blocks), false otherwise
    private boolean topRowsEmpty()
    {
    	for(int i=grid.getNumRows()-1;i>=0;i--){
    		int j=grid.getNumCols();
    		for( j=grid.getNumCols()-1;j>=0;j--){
        		if(grid.get(new Location(i,j))!= null)
        			break;
        	}
    		if(j==-1)
    			return true;

    	}
    	

        return false;
    }

}