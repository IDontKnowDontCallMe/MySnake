package allThings;
import java.util.*;

enum DIRECTION {
	UP , DOWN , LEFT ,RIGHT;
}

public class Snake {
	
	private ArrayList<Body> myBody ;
	private DIRECTION myDirection;
	
	public Snake(){
		myBody = new ArrayList<Body>();
		reset();
	}
	
	public Body getBody(int index){
		return myBody.get(index);
	}
	
	public int getLength(){
		return myBody.size();
	}
	
	public void reset(){
		myBody.clear();
		myBody.add(new Body(7,4));
		myBody.add(new Body(7,3));
		myBody.add(new Body(7,2));
		myBody.add(new Body(7,1));
		Board.nextDirection = DIRECTION.RIGHT;
		myDirection = DIRECTION.RIGHT ;
	}
	
	public void move(){
		
	     Body head;
		 Body headOld = myBody.get(0);
		 
		 myDirection = Board.nextDirection;
		
		switch(myDirection){
		case UP:
			head = new Body(headOld.row-1, headOld.col);
			break;
		case DOWN:
			head = new Body(headOld.row+1, headOld.col);
			break;
		case LEFT:
			head = new Body(headOld.row, headOld.col-1);
			break;
		case RIGHT:
			head = new Body(headOld.row, headOld.col+1);
			break;
		default:
			head = new Body(0,0);
		}
		
		Food f = Board.getFood();
		
		if(head.row == f.row && head.col == f.col){
			myBody.add(0,head);
			Board.foodProduce();
		}
		else{
		    myBody.add(0,head);
		    myBody.remove(myBody.size()-1);
		}
		
		if(this.isAttackBody() || this.isAttackBound()){
			Board.setSTATE(STATE.DEAD);
		}
		
		
				
	}
	
	public boolean isAttackBody(){
		boolean isattackbody = false;
		Body head = myBody.get(0);
		for(int i=1; i < myBody.size(); i++){
			if(head.row == myBody.get(i).row && head.col == myBody.get(i).col){
				isattackbody = true;
				break;
			}
		}
		return isattackbody;
	}
	
	public boolean isAttackBound(){
		boolean isattackbound = false;
		Body head = myBody.get(0);
		if(head.col >= Board.getColumn() || head.col < 0 || head.row >= Board.getRow() || head.row < 0){
			isattackbound = true;
		}
		return isattackbound;
	}
	
	public void setDirection(DIRECTION d){
		
		switch(d){
		case UP:
			if(myDirection == DIRECTION.DOWN){
				return;
			}
			Board.nextDirection = d;
			break;
		case DOWN:
			if(myDirection == DIRECTION.UP){
				return;
			}
			Board.nextDirection = d;
		case LEFT:
			if(myDirection == DIRECTION.RIGHT){
				return;
			}
			Board.nextDirection = d;
			break;
		case RIGHT:
			if(myDirection == DIRECTION.LEFT){
				return;
			}
			Board.nextDirection = d;
			break;
			
		}
		
	}

}
