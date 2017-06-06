/**
 * Tetrad class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.Random;

public class Tetrad 
{
    private Block[] blocks;
    private BlockDisplay display; 
    public Tetrad(BoundedGrid<Block> grid)
    {
    	
    	
        //Exercise 1.2  Insert code here to
        //                  instantiate blocks Block array (length 4)
    	blocks=new Block[4];
        //                  initialize blocks array with new Block objects
    	for(int i=0;i<=3;i++){
    		blocks[i]=new Block();
    	}
        //                  declare color variable
    	Color color=Color.BLACK;
        //                  declare and instantiate locs Location array (length 4)
    	Location[] locs=new Location[4];
        //                  declare shape variable and set equal to zero
    
    	
        //Exercise 2.0  Insert code here to
        //                  choose a random integer from 0 to 6
    	//Change next int to 1 to test with 1 kind of tetrad
    	Random rand = new Random(); 
    	int shape = rand.nextInt(7); 
        //Exercise 1.2  Insert code here to
        //                  branch (if statements) based on each shape number, to then
        //                      set the color variable for that shape
    	switch(shape){
    	case 0:
    		color=Color.RED; //I
    		locs[0]=new Location(0,3);
    		locs[1]=new Location(0,4);
    		locs[2]=new Location(0,5);
    		locs[3]=new Location(0,6);
    		break;
    	case 1:
    		color=Color.GRAY; //T
    		locs[0]=new Location(0,5);
    		locs[1]=new Location(0,6);
    		locs[2]=new Location(0,7);
    		locs[3]=new Location(1,6);
    		break;
    	case 2:
    		color=Color.CYAN; //O
    		locs[0]=new Location(1,5);
    		locs[1]=new Location(0,6);
    		locs[2]=new Location(1,6);
    		locs[3]=new Location(0,5);
    		break;
    	case 3:
    		color=Color.YELLOW; //L
    		locs[0]=new Location(0,5);
    		locs[1]=new Location(1,5);
    		locs[2]=new Location(2,5);
    		locs[3]=new Location(2,6);
    		break;
    	case 4:
    		color=Color.PINK; //J
    		locs[0]=new Location(0,6);
    		locs[1]=new Location(1,6);
    		locs[2]=new Location(2,6);
    		locs[3]=new Location(2,5);
    		break;
    	case 5:
    		color=Color.BLUE; //S
    		locs[0]=new Location(0,6);
    		locs[1]=new Location(0,7);
    		locs[2]=new Location(1,5);
    		locs[3]=new Location(1,6);
    		break;
    	case 6:
    		color=Color.GREEN;//Z
    		locs[0]=new Location(0,5);
    		locs[1]=new Location(1,5);
    		locs[2]=new Location(1,6);
    		locs[3]=new Location(2,6);
    		break;
    	}
        //                      set the block locations for that shape
        
        //Exercise 1.2  Insert code here (after the above if statements) to
        //                  loop through the blocks array to
        //                      set the color of each block
        //                  call addToLocations
    	for(int i=0;i<=3;i++){
    		blocks[i].setColor(color);
    	}
    	addToLocations(grid,locs);
    	//display=new BlockDisplay(grid);
    	
    	//translate(1,0);
    }

    //precondition:  blocks are not in any grid;
    //               blocks.length = locs.length = 4.
    //postcondition: The locations of blocks match locs,
    //               and blocks have been put in the grid.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
    	
    	//Block firstElement=grid.get(locs[0]);
    	blocks[0].putSelfInGrid(grid, locs[0]);
    	
    	//Block secondElement=grid.get(locs[1]);
    	blocks[1].putSelfInGrid(grid, locs[1]);
    	
    	//Block thirdElement=grid.get(locs[2]);
    	blocks[2].putSelfInGrid(grid, locs[2]);
    	
    	//Block forthElement=grid.get(locs[3]);
    	blocks[3].putSelfInGrid(grid, locs[3]);
     
    }

    //precondition:  Blocks are in the grid.
    //postcondition: Returns old locations of blocks;
    //               blocks have been removed from grid.
    private Location[] removeBlocks()
    {
    	Location[] locs=new Location[4];
    	for(int i=0;i<=3;i++){
    		locs[i]=blocks[i].getLocation();
    		blocks[i]=null;
    	}
    	
    	return locs;
    }

    //postcondition: Returns true if each of locs is
    //               valid (on the board) AND empty in
    //               grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid,
                             Location[] locs)
    {
    	for(int i=0;i<=3;i++){
    		if(!grid.isValid(locs[i]))
    			return false;
    		Block block=grid.get(locs[i]);
    		if(block!=null)
    			return false;
    	}
    	
    	return true;
    }

    //postcondition: Attempts to move this tetrad deltaRow
    //               rows down and deltaCol columns to the
    //               right, if those positions are valid
    //               and empty; returns true if successful
    //               and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        //Exercise 2.2    Insert code here to
        //              ask any block for its grid and store value
    	//BoundedGrid<Block>[] grids=new BoundedGrid<Block>[4];

    	BoundedGrid<Block> grid=blocks[0].getGrid();
    	Color color=blocks[0].getColor();

        //              remove the blocks (but save the locations)
    	Location[] locs=removeBlocks();
    	for(int i=0;i<=3;i++){
    		grid.remove(locs[i]);
    	}
    	blocks=new Block[4];
    	Location[] newLocs=new Location[4];
        //              create an array of the new (possible) locations
    	for(int i=0;i<=3;i++){
    		newLocs[i]=new Location(locs[i].getRow()+deltaRow,locs[i].getCol()+deltaCol);
    	}
        //              check if the new locations are empty
    	if(!areEmpty(grid,newLocs))
    	{
    		for(int i=0;i<=3;i++){
        		blocks[i]=new Block();
        		blocks[i].setColor(color);
        	}
        	addToLocations(grid,locs);
    		return false;
    	}
        //              replace the tetrad in the proper place (translated)
    	for(int i=0;i<=3;i++){
    		blocks[i]=new Block();
    		blocks[i].setColor(color);
    	}
    	addToLocations(grid,newLocs);
    	
    	return true;
        //              return true if moved, false if not moved

    }

    //postcondition: Attempts to rotate this tetrad
    //               clockwise by 90 degrees about its
    //               center, if the necessary positions
    //               are empty; returns true if successful
    //               and false otherwise.
    public boolean rotate()
    {
        //Exercise 3.0  Insert code here to
        //              ask any block for its grid and store value
    	BoundedGrid<Block> grid=blocks[0].getGrid();
    	Color color=blocks[0].getColor();
        //              remove the blocks (but save the locations)
    	Location[] locs=removeBlocks();
    	for(int i=0;i<=3;i++){
    		grid.remove(locs[i]);
    	}
    	blocks=new Block[4];
    	Location[] newLocs=new Location[4];
        //              create an array of the new (possible) locations
    	for(int i=0;i<=3;i++){
    		newLocs[i]=new Location(locs[0].getRow()-locs[0].getCol()+locs[i].getCol()
    				,locs[0].getRow()+locs[0].getCol()-locs[i].getRow());
    	}
        //              check if the new locations are empty
    	if(!areEmpty(grid,newLocs))
    	{
    		for(int i=0;i<=3;i++){
        		blocks[i]=new Block();
        		blocks[i].setColor(color);
        	}
        	addToLocations(grid,locs);
    		return false;
    	}
        //              replace the tetrad in the proper place (rotated)
    	for(int i=0;i<=3;i++){
    		blocks[i]=new Block();
    		blocks[i].setColor(color);
    	}
    	addToLocations(grid,newLocs);
    	return true;

    }

}