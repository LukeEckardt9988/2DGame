package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_Paulus extends Entity{
    public NPC_Paulus(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            // Laden der Bilder für die verschiedenen Richtungen des Spielers
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusStepUp1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusStepUp2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusStep1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusStep2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusLeft1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusLeft2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusRight1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/palyer/timotheusRight2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {

        npcActionCounter ++;

        if (npcActionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1; //damit es keine 99 sind. 1 is 100

            if(i < 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "right";
            }
            if(i > 75) {
                direction = "left";
            }
            npcActionCounter = 0;
        }


    }

}
