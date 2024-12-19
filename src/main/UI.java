package main;

import objects.OBJ_Coin;
import objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font impact_30; // Schriftart für die Anzeige
    BufferedImage keyImage; // Bild des Schlüssels
    BufferedImage CoinImage; // Bild der Münze
    public boolean messageOn = false;
    public String message = ""; //Leeres Feld für Screen Nachrichten
    int messageCounter = 0; //Zeit setzten um das Anzeigen der Nachricht auszuschalten.

    public UI(GamePanel gp) {
        this.gp = gp;

        impact_30 = new Font("Impact", Font.PLAIN, 30); // Initialisierung der Schriftart

        // Laden der Bilder für Schlüssel und Münze
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
        OBJ_Coin coin = new OBJ_Coin();
        CoinImage = coin.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2) {

        g2.setFont(impact_30); // Setzen der Schriftart
        g2.setColor(Color.white); // Setzen der Farbe auf Weiß

        // Zeichnen des Schlüsselbildes und der Anzahl der Schlüssel
        g2.drawImage(keyImage, gp.tileSize*3, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 220, 60);

        // Zeichnen des Münzbildes und der Anzahl der Münzen
        g2.drawImage(CoinImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasCoin, 85, 60);

        //Nachrichten auf dem Screen Anzeigen lassen
        if(messageOn == true) {
            g2.drawString(message, gp.tileSize*2, gp.tileSize*10);
            messageCounter++;
            if (messageCounter > 60) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}