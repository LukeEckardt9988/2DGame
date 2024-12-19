package main;

import entity.Entity;
import entity.Player;
import objects.SuperObjects;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Bildschirm- und Welt-Einstellungen
    final int originalTileSize = 62; // Original Tile-Größe
    final int scale = 3; // Skalierungsfaktor für die Tiles

    public final int tileSize = originalTileSize + scale; // Skalierte Tile-Größe
    public final int maxScreenCol = 16; // Anzahl der Spalten auf dem Bildschirm
    public final int maxScreenRow = 12; // Anzahl der Zeilen auf dem Bildschirm
    public final int screenWidth = tileSize * maxScreenCol; // Breite des Bildschirms
    public final int screenHeight = tileSize * maxScreenRow; // Höhe des Bildschirms

    //Worlds Eigenschaften
    public final int maxWorldCol = 50; // Anzahl der Spalten in der Welt
    public final int maxWorldRow = 50; // Anzahl der Zeilen in der Welt

    //Bildrate
    int FPS = 60; // Frames pro Sekunde

    // Instanzen der Spielklassen Aka System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound soundeffect = new Sound();
    public CollitionChecker cChecker = new CollitionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entity und Objects
    public Player player = new Player(this, keyH);
    public SuperObjects obj[] = new SuperObjects[30]; // Array für Spielobjekte
    public Entity npc[] = new Entity[10];

    public GamePanel() {
        // Panel-Einstellungen
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject(); // Spielobjekte platzieren
        aSetter.setNPC();// NPC aufrufen
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // Spiel-Thread starten
    }

    @Override
    public void run() {
        // Spiel-Loop mit Delta-Timing für gleichmäßige Bildraten
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update(); // Spielzustand aktualisieren
                repaint(); // Spielszene neu zeichnen
                delta--;
                drawCount++;
            }

            // Ausgabe der FPS in der Konsole
            if (timer >= 1000000000) {
                System.out.println("FPS;" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update(); // Spieler aktualisieren
        for (int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); // Tiles zeichnen

        // Objekte zeichnen
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        for (int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        player.draw(g2); // Spieler zeichnen
        ui.draw(g2); // Spieler Infos (UI) zeichnen
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSoundeffect(int i) {
        soundeffect.setFile(i);
        soundeffect.play();
    }
}