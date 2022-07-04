package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_HEART;
import object.OBJ_KEY;
import object.SuperObject;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40,arial_80B;
	//BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	public int titleScreenState = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN,40);
		arial_80B = new Font("Arial", Font.BOLD,80);
		//OBJ_KEY key = new OBJ_KEY(gp);
		//keyImage = key.image;
		
		SuperObject heart = new OBJ_HEART(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		else if(gp.gameState== gp.playState) {
			drawPlayerLife();
		}
		//Pause state
		else if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		/*if(gameFinished == true) {

			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x,y;
			
			text = "You found it";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2-textLength/2;
			y = gp.screenHeight/2-(gp.tileSize*3);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Congrat";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2-textLength/2;
			y = gp.screenHeight/2+(gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x "+ gp.player.hasKey, 74, 50);
			
			//Message
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
				messageCounter++;
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}*/
		
	}
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		
		int i = 0;
		while(i<gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+=gp.tileSize;
		}
		
		//Reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//draw current life
		while(i< gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i< gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x+=gp.tileSize;
		}
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(70,120,80));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			
			//title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
			String text = "Adventure Game";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			//shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			
			//main
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			//more image
			//blueboy
			x = gp.screenWidth/2-gp.tileSize;
			y+=gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);		
			
			//menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize*4;
			g2.drawString(text,x,y);
			if(commandNum==0) {
				g2.drawString(">" ,x-gp.tileSize,y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1) {
				g2.drawString(">" ,x-gp.tileSize,y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2) {
				g2.drawString(">" ,x-gp.tileSize,y);
			}
		}
		else if(titleScreenState==1) {
			//class selection screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select your class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Mage";
			x = getXforCenteredText(text);
			y+= gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Assasin";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Tanker";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y+= gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "Paused";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2-length/2;
		return x;
	}
}
