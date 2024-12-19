package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coin extends SuperObjects{
    public OBJ_Coin() {
        name = "Coin";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
