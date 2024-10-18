
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	Random rand;
	int  xVelocity;
	int yVelocity;
	 
	
	Ball(int x , int y , int width , int height){
	super(x,y,width,height);
	rand = new Random();
	int randomXDirection = 1;
	
		setXdirection(randomXDirection );
	int randomYDirection = -1;
		setYdirection(randomYDirection );
	}
	
	

	

	public void setXdirection(int randomXDirection) {
		xVelocity =randomXDirection;	
	}
	public void setYdirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	public void  draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x,y, width, height);
	}
	
}
