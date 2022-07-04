package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_KEY extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_KEY(GamePanel gp) {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
