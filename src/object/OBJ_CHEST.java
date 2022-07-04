package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_CHEST extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_CHEST(GamePanel gp) {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}