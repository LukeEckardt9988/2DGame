package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    // Position der Entität in der Welt
    public int worldX, worldY;
    // Geschwindigkeit der Entität
    public  int speed;

    // Bilder für die verschiedenen Richtungen der Entität
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // Aktuelle Richtung der Entität
    public String direction;

    // Zähler für den Spritewechsel
    public int spriteCounter = 0;
    // Nummer des aktuellen Sprites
    public int spriteNum = 1;
    // Rechteck für die Kollisionserkennung
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48 );
    // Standardposition der Solid Area
    public int solidAreaDefaultX, solidAreaDefaultY;
    // Flag, ob eine Kollision vorliegt
    public boolean collisionOn = false;
    // Geschwindigkeit des Richtungswechsel des NPCs
    public int npcActionCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    //Der NPC Charactor soll sich auch bewegen können
    public void setAction() {

    }
    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checktTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkPlayer(this);

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


    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX; // Berechnen der X-Position auf dem Bildschirm
        int screenY = worldY - gp.player.worldY + gp.player.screenY; // Berechnen der Y-Position auf dem Bildschirm

        // Überprüfen, ob das Objekt im sichtbaren Bereich ist
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY  + gp.player.screenY) {

            BufferedImage image = switch (direction) {
                case "up" -> (spriteNum == 1) ? up2 : up1;
                case "down" -> (spriteNum == 1) ? down2 : down1;
                case "right" -> (spriteNum == 1) ? right2 : right1;
                case "left" -> (spriteNum == 1) ? left2 : left1;
                default -> null;
            };

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Objekt zeichnen
        }
    }
}