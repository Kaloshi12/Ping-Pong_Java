
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Scoore  extends Rectangle{
static  int GAME_HEIGHT ;
static int GAME_WIDTH;
int player1=0;
int player2=0;
	Scoore(int GAME_WIDTH,int GAME_HEIGHT){
		this.GAME_WIDTH = GAME_HEIGHT;
		this.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	public void  draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		g.drawLine(500, 0 , 500 , (GAME_HEIGHT));
		g.drawString(String.valueOf(player1),500-85,50);
		g.drawString(String.valueOf(player2),500+20,50);
	}
}

