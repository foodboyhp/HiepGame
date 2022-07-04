package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_DOOR extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_DOOR(GamePanel gp) {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}