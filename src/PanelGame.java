
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class PanelGame extends JPanel implements Runnable {
 public static final int GAME_WIdth=1000;
 public static final int GAME_HEIGHT= (int)(GAME_WIdth * (0.5555));
 public static final Dimension SCREEN_SIZE = new Dimension(GAME_WIdth,GAME_HEIGHT);
 public static final int BALL_DIAMETER = 20;
 public static final int PADDLE_WIDTH = 25;
 public static final int PADDLE_HEIGHT = 100;
 Thread gameThreade;
 Image image;
 Graphics graphics;
 Paddle paddle1;
 Paddle paddle2;
 Ball ball;
 Scoore scoore;
 

	PanelGame(){
		newPaddles();
		newBall();
		scoore = new Scoore(GAME_WIdth,GAME_HEIGHT);
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		gameThreade = new Thread(this);
		gameThreade.start();

		
		
	}
	public void newBall() {
		ball = new Ball((GAME_WIdth/2)-(BALL_DIAMETER/2),((GAME_HEIGHT/2)-(BALL_DIAMETER/2)),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles(){
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIdth-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		scoore.draw(g);
	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void checkCollision() {
		if(paddle1.y<=0) {
			paddle1.y=0;
		}
		if(paddle1.y>= GAME_HEIGHT-PADDLE_HEIGHT) {
			paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
		}
		if(paddle2.y<=0) {
			paddle2.y=0;
		}
		if(paddle2.y>= GAME_HEIGHT-PADDLE_HEIGHT) {
			//paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
		}
		if(ball.y<=0) {
			
			ball.setYdirection(-ball.yVelocity);
		}
		if(ball.y>=GAME_HEIGHT-BALL_DIAMETER) {
			
			ball.setYdirection(-ball.yVelocity);
		}
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			
			if((int)ball.xVelocity < 4)
				ball.xVelocity = (int) (ball.xVelocity + 0.8);
			
			if((int)ball.yVelocity>-3 && (int)ball.yVelocity<3) {
				if(ball.yVelocity>0) {
					ball.yVelocity=(int)(ball.yVelocity + 0.8);
				}else {
					ball.yVelocity =(int) (ball.yVelocity - 0.8);
				}
			}
		
			ball.setXdirection(ball.xVelocity);
			ball.setYdirection(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity=Math.abs(-ball.xVelocity);
			
			if((int)ball.xVelocity < 4)
				ball.xVelocity = (int) (ball.xVelocity + 0.8);
			
			if((int)ball.yVelocity>-3 && (int)ball.yVelocity<3) {
				if(ball.yVelocity>0) {
					ball.yVelocity=(int) (ball.yVelocity + 0.8);
				}else {
					ball.yVelocity =(int) (ball.yVelocity - 0.8);
				}
			}
		
			ball.setXdirection(-ball.xVelocity);
			ball.setYdirection(-ball.yVelocity);
		}
		if(ball.x<=0) {
			scoore.player2++;
			newPaddles();
			newBall();
		}
		if(ball.x >= GAME_WIdth-BALL_DIAMETER) {
			scoore.player1++;
			newPaddles();
			newBall();
		}
		
	}
	public void run() {
		long lastTime = System.nanoTime();
		double amountOdTicks = 60.0;
		double ns = 1000000000 / amountOdTicks;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
				

			}
			
		}
	}
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
