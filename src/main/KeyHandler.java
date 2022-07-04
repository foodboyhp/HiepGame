package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;
    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        
        //title state
        if(gp.gameState==gp.titleState) {
        	if(gp.ui.titleScreenState==0) {
        		if(code == KeyEvent.VK_W){
            		gp.ui.commandNum--;
            		if(gp.ui.commandNum<0) {
            			gp.ui.commandNum = 2;
            		}
            	}
            	if(code == KeyEvent.VK_S){
            		gp.ui.commandNum++;
            		if(gp.ui.commandNum>2) {
            			gp.ui.commandNum = 0;
            		}
            	}
            	if(code==KeyEvent.VK_ENTER) {
            		if(gp.ui.commandNum==0) {
            			gp.ui.titleScreenState = 1;
            			gp.playMusic(0);
            		}
            		if(gp.ui.commandNum==1) {
            			//TODO: add later
            		}
            		if(gp.ui.commandNum==2) {
            			System.exit(0);
            		}
            	}
        	}
        	
        	else if(gp.ui.titleScreenState==1) {
        		if(code == KeyEvent.VK_W){
            		gp.ui.commandNum--;
            		if(gp.ui.commandNum<0) {
            			gp.ui.commandNum = 3;
            		}
            	}
            	if(code == KeyEvent.VK_S){
            		gp.ui.commandNum++;
            		if(gp.ui.commandNum>3) {
            			gp.ui.commandNum = 0;
            		}
            	}
            	if(code==KeyEvent.VK_ENTER) {
            		if(gp.ui.commandNum==0) {
            			gp.gameState = gp.playState;
            			gp.playMusic(0);
            		}
            		if(gp.ui.commandNum==1) {
            			gp.gameState = gp.playState;
            			gp.playMusic(0);
            		}
            		if(gp.ui.commandNum==2) {
            			gp.gameState = gp.playState;
            			gp.playMusic(0);
            		}
            		if(gp.ui.commandNum==3) {
            			gp.ui.titleScreenState = 0;
            		}
            	}
        	}
        	
        }
        //play state
        if(gp.gameState == gp.playState) {
        	
        	if(code == KeyEvent.VK_W){
        		upPressed = true;
        	}
        	else if(code == KeyEvent.VK_S){
        		downPressed = true;
        	}
        	else if(code == KeyEvent.VK_A){
        		leftPressed = true;
        	}
        	else if(code == KeyEvent.VK_D){
        		rightPressed = true;
        	}
        	else if(code == KeyEvent.VK_E){
        		if(gp.gameState==gp.playState) {
        			gp.gameState = gp.pauseState;
        		}
        	}
        	else if(code == KeyEvent.VK_ESCAPE) {
        		gp.gameState = gp.titleState;
        		gp.ui.titleScreenState = 0;
        	}
        }
        if(gp.gameState == gp.pauseState) {
        	if(code== KeyEvent.VK_P) {
        		gp.gameState = gp.playState;
        	}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}