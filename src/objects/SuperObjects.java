package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjects {

    public BufferedImage image;  // Bild des Objekts
    public String name;  // Name des Objekts
    public boolean collision = false;  // Flag, ob das Objekt eine Kollision verursacht
    public int worldX, worldY;  // Position des Objekts in der Welt
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // Rechteck für die Kollisionserkennung
    public int solidAreaDefaultX = 0;  // Standard X-Position der solidArea
    public int solidAreaDefaultY = 0;  // Standard Y-Position der solidArea

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX; // Berechnen der X-Position auf dem Bildschirm
        int screenY = worldY - gp.player.worldY + gp.player.screenY; // Berechnen der Y-Position auf dem Bildschirm

        // Überprüfen, ob das Objekt im sichtbaren Bereich ist
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY  + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Objekt zeichnen
        }
    }
}