package allThings;

public class MyThread extends Thread{
	
	private Board myBoard;
	private Snake mySnake;
	
	public MyThread(Board b, Snake s){
		myBoard = b;
		mySnake = s;
	}
	
	public void run(){
		while(true){
			mySnake.move();
			myBoard.repaint();
			try{
				sleep(300);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
	}

}