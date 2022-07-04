
package entity;

import entity.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobPriority;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2-(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);
        
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
        
        //Player Status
        maxLife = 6;
        life = maxLife;
    }
    
    public void getPlayerImage(){
        up1 = setup("up1");
        up2 = setup("up2");
        down1 = setup("down1");
        down2 = setup("down2");
        left1 = setup("left1");
        left2 = setup("left2");
        right1 = setup("right1");
        right2 = setup("right2");
    }
    
    public BufferedImage setup(String imageName) {
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
    	return image;
    }
    
    public void update(){
    	
    	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
    		if(keyH.upPressed == true){
                direction = "up";
                
            }
            else if(keyH.downPressed == true){
                direction = "down";
                
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
    		
    		//Check tile collsion
    		collisionOn = false;
    		gp.cChecker.checkTile(this);
    		//Check obj collision
    		int objIndex = gp.cChecker.checkObject(this, true);
    		pickUpObject(objIndex);
    		//If collision = false, player can move
    		if(collisionOn== false) {
    			switch(direction) {
    			case "up": worldY -= speed; break;
    			case "down": worldY += speed; break;
    			case "right": worldX += speed; break;
    			case "left": worldX -= speed; break;
    			}
    		}
            
            spriteCounter++;
            if(spriteCounter > 10) {
            	if(spriteNum == 1) 
            		spriteNum = 2;
            	else if(spriteNum == 2)
            		spriteNum = 1;
            	spriteCounter = 0;
            }
    	} 
    }
    
    public void pickUpObject(int i) {
    	if(i!=999) {
    		//if index is not 999, we're touching an object
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "key":
				hasKey++;
				gp.playSE(1);
				gp.obj[i] = null;
				gp.ui.showMessage("You get a key");
				break;
				
			case "door":
				if(hasKey > 0) {
					gp.playSE(1);
					gp.obj[i]=null;
					hasKey--;
					
				}
				else {
					gp.ui.showMessage("You need a key");
				}
				break;
			
			case "boots":
				gp.obj[i]=null;
				gp.playSE(2);
				speed += 2;
				gp.ui.showMessage("Power up");
			break;
    	    }
        }
    }
    
    public void draw(Graphics g2){
        BufferedImage image = null;
        
        switch (direction) {
            case "up":
            	if(spriteNum == 1){
            		image = up1;
            	}
            	if(spriteNum == 2){
            		image = up2;
            	}
                break;
            case "down":
            	if(spriteNum == 1){
            		image = down1;
            	}
            	if(spriteNum == 2){
            		image = down2;
            	}
                break;
            case "left":
            	if(spriteNum == 1){
            		image = left1;
            	}
            	if(spriteNum == 2){
            		image = left2;
            	}
                break;
            case "right":
            	if(spriteNum == 1){
            		image = right1;
            	}
            	if(spriteNum == 2){
            		image = right2;
            	}
                break;
        }
        
        int x = screenX;
        int y = screenY;
        
        if(screenX>worldX) {
        	x = worldX;
        }
        if(screenY>worldY) {
        	y = worldY;
        }
        int rightOffset = gp.screenWidth-screenX;
		if(rightOffset > gp.worldWidth-worldX) {
			x = gp.screenWidth-(gp.worldWidth-worldX);
		}
		int bottomOffset = gp.screenHeight-screenY;
		if(bottomOffset>gp.worldHeight-worldY) {
			y = gp.screenHeight-(gp.worldHeight-worldY);
		}
        
        g2.drawImage(image, screenX, screenY, null);
    }
}
