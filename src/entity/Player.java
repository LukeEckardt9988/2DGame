package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    KeyHandler keyH;
    // Position des Spielers auf dem Bildschirm
    public final int screenX;
    public final int screenY;

   public int hasKey = 0; // Anzahl der Schlüssel, die der Spieler hat
   public int hasCoin = 0; // Anzahl der Coins, die der Spieler hat

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);//Rufe den Constructor von der Entity auf. Superklasse!

        this.keyH = keyH;


        // Setzen der Bildschirmposition in der Mitte des Bildschirms
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);

        // Erstellen des Rechtecks für die Kollisionserkennung
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        // Setzen der Standardwerte des Spielers
        setDefaultValues();
        // Laden der Bilder des Spielers
        getPlayerImage();
    }

    public void setDefaultValues() {
        // Setzen der Startposition des Spielers in der Welt
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * 8;
        // Setzen der Geschwindigkeit des Spielers
        speed = 4;
        // Setzen der Startrichtung des Spielers
        direction = "down";
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

    public void update() {
        // Überprüfen, ob eine Taste gedrückt wurde
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Setzen der Bewegungsrichtung basierend auf der gedrückten Taste
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // Zurücksetzen des Kollisionsflags
            collisionOn = false;
            // Überprüfen der Kollision mit Tiles
            gp.cChecker.checktTile(this);
            // Überprüfen der Kollision mit Objekten
            int objIndex = gp.cChecker.checkObject(this, true);
            // Aufheben von Objekten
            pickUpObjects(objIndex);

            // NPC Collision Checken
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Bewegen des Spielers, wenn keine Kollision vorliegt
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            // Hochzählen des Sprite-Zählers
            this.spriteCounter++;
            // Wechseln des Sprites nach einer bestimmten Anzahl von Frames
            if (this.spriteCounter > 10) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        }
    }

    public void pickUpObjects(int i) {
        // Überprüfen, ob der Spieler mit einem Objekt kollidiert ist
        if (i != 999) {
            String objectName = gp.obj[i].name;  // Abrufen des Namens des Objekts
            switch (objectName) { // Ausführen der entsprechenden Aktion basierend auf dem Objektnamen
                case "Key":
                    gp.playSoundeffect(2); //Play den Soundeffekt für den Schlüssel
                    hasKey++; // Erhöhen der Anzahl der Schlüssel des Spielers
                    gp.obj[i] = null; // Entfernen des Schlüssels aus der Spielwelt
                    gp.ui.showMessage("Du hast einen Schlüssel");//Nachricht die auf dem Screen ausgegeben wird
                    break;
                case "Door":
                    gp.playSoundeffect(4); //Play den Soundeffekt für den Tür wenn verschlossen weil man keinen schlüssel hat.
                    gp.ui.showMessage("Die Tür ist verschlossen");
                    if (hasKey > 0) { // Überprüfen, ob der Spieler einen Schlüssel hat
                        gp.obj[i] = null; // Entfernen der Tür aus der Spielwelt
                        hasKey--; // Reduzieren der Anzahl der Schlüssel des Spielers
                        gp.playSoundeffect(3); //Play den Soundeffekt für den Tür
                        gp.ui.showMessage("Du hast die Tür Aufgeschlossen");//Nachricht die auf dem Screen ausgegeben wird
                    }
                    break;
                case "Coin":
                    gp.playSoundeffect(1); //Play den Soundeffekt für den Coin
                    hasCoin++;// Erhöhen der Anzahl der Schlüssel des Spielers
                    gp.obj[i] = null;  // Entfernen des Schlüssels aus der Spielwelt
                    System.out.println("Coin" + hasCoin); // Ausgabe der Anzahl der Schlüssel in der Konsole
                    gp.ui.showMessage("Du hast eine Münze");//Nachricht die auf dem Screen ausgegeben wird
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if(i != 999) {
            System.out.println("you are Hitten npc");
        }
    }

    public void draw(Graphics2D g2) {
        // Auswählen des Bildes basierend auf der aktuellen Richtung und dem Sprite-Zähler
        BufferedImage image = switch (direction) {
            case "up" -> (spriteNum == 1) ? up2 : up1;
            case "down" -> (spriteNum == 1) ? down2 : down1;
            case "right" -> (spriteNum == 1) ? right2 : right1;
            case "left" -> (spriteNum == 1) ? left2 : left1;
            default -> null;
        };

        // Zeichnen des Bildes an der aktuellen Position des Spielers
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}