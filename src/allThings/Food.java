package allThings;
import java.util.*;

public class Food extends Body{
    
	public Food(){
		super(0,0);
		Random rand = new Random();
		this.row = rand.nextInt(Board.getRow());
		this.col = rand.nextInt(Board.getColumn());
	}
	
}
