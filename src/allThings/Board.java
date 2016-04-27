package allThings;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum STATE{
	RUN, DEAD;
}

public class Board extends Frame{
	
	private static final int Width = 400;
	private static final int Height = 300;		
    private static final int Column = 20;
	private static final int Row = 15;
	private static final int TileWidth = 16;
	private static final int TileHeight = 16;
	private static final int XOffset = (Width-Column*TileWidth)/2;
	private static final int YOffset = (Height-Row*TileHeight)/2;
	private static Snake mySnake;
	private static Food myFood;
	public static STATE myState = STATE.RUN;
	public static DIRECTION nextDirection;
	
	
	
	public static void main(String args[]){
		Board board = new Board("SNAKE");
	}
	
	public Board(String title){
		super(title);
		setSize( Width , Height );
		setVisible(true);
		setResizable(false);
		setSTATE(STATE.RUN);
		mySnake = new Snake();
		foodProduce();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		addKeyListener(new KeyHandler(mySnake));
		MyThread t = new MyThread(this,mySnake);
		t.start();
	}
	
	public static void setSTATE(STATE s){
		myState = s;
	}
	
	public void paint(Graphics g){
		switch(myState){
		case RUN:
		  drawDecoration(g);
		  drawSnake(g);
		  drawFood(g);
		  break;
		case DEAD:
			drawMessage(g);
			break;
		}
	}
	
	public void drawMessage(Graphics g){
		g.drawString("Game over, press F2 to try again!", XOffset, Height/2);
	}
	
	public void drawDecoration(Graphics g){
		
		g.setColor(Color.black);
		g.drawRect(XOffset, YOffset, Column*TileWidth, Row*TileHeight);
		
	}
	
	public void drawSnake(Graphics g){
		
		g.setColor(Color.green);
		for(int i = 0; i < mySnake.getLength() ; i++){
			Body body = mySnake.getBody(i);
			g.fillRect(body.col * TileWidth + XOffset, body.row * TileHeight + YOffset , TileWidth, TileHeight);
		}
		
	}
	
	public void drawFood(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(XOffset+myFood.col*TileWidth, YOffset+myFood.row*TileHeight, TileWidth, TileHeight);
		
	}
	
	public static void foodProduce(){
		boolean isSuit = false;
		Food food = new Food();
		while(!isSuit){
			isSuit = true;
			food = new Food();
			for(int i=0; i < mySnake.getLength(); i++){
				if(food.row == mySnake.getBody(i).row && food.col == mySnake.getBody(i).col){
					isSuit = false;
					break;
				}
			}
		}
		myFood = food;
		
	}
	
	public static Food getFood(){
		return myFood;
	}
	
	class KeyHandler extends KeyAdapter{
		private Snake snake;
		
		public KeyHandler(Snake s){
			snake = s;
		} 
		
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				snake.setDirection(DIRECTION.UP);
				break;
			case KeyEvent.VK_DOWN:
				snake.setDirection(DIRECTION.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				snake.setDirection(DIRECTION.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				snake.setDirection(DIRECTION.RIGHT);
				break;
			case KeyEvent.VK_F2:
				mySnake.reset();
				Board.setSTATE(STATE.RUN);
				break;
			}
		}
	}
	
	public static int getRow(){
		return Row;
	}
	
	public static int getColumn(){
		return Column;
	}
	
}
