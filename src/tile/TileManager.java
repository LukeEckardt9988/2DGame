package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    // Array zum Speichern der Kacheln
    public Tile[] tile;
    // 2D-Array zum Speichern der Kachel-IDs der Karte
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        // Laden der Bilder der Kacheln
        getTileImage();
        // Laden der Karte aus der Textdatei
        loadMap();
    }

    public void getTileImage() {
        try {
            // Erstellen der Kacheln und Zuweisen der Bilder
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandRock.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandRock2.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterClean.png")));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterCrysl.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterDownGrasUp.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterUpGrasDown.png")));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterLeftGrasRight.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterRightGrasLeft.png")));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grasLeftDown.png")));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grasLeftUp.png")));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grasRightDown.png")));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grasRightUp.png")));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandWeg.png")));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandwegDown.png")));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandwegDownLeft.png")));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandWegDownRight.png")));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandWegUpLeft.png")));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandWegUpRight.png")));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house.png")));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house2.png")));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house3.png")));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterUpRight.png")));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterUpLeft.png")));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterDownRight.png")));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterDownLeft.png")));
            tile[27].collision = true;

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wallUpLeft.png")));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wallUpRight.png")));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wallDownLeft.png")));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wallDownRight.png")));
            tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wallUp.png")));
            tile[33].collision = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bigHouseUpLeft.png")));
            tile[34].collision = true;

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bigHouseLeft.png")));
            tile[35].collision = true;

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bigHouseRight.png")));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bigHouseUpRight.png")));
            tile[37].collision = true;

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bigHouseMidUp.png")));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandWegUpNull.png")));

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sandWegDownNull.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap() {
        try {
            // Öffnen der Textdatei mit der Karte
            InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // Einlesen der Karte Zeile für Zeile
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                // Aufteilen der Zeile in einzelne Kachel-IDs
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    // Speichern der Kachel-ID im 2D-Array
                    mapTileNum[col][row] = num;
                    col++;
                }
                // Nächste Zeile einlesen
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        // Durchlaufen der Karte und Zeichnen der Kacheln
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            // Abrufen der Kachel-ID aus dem 2D-Array
            int tileNum = mapTileNum[worldCol][worldRow];

            // Berechnen der Weltkoordinaten der Kachel
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            // Berechnen der Bildschirmkoordinaten der Kachel
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Zeichnen der Kachel, wenn sie sich im sichtbaren Bereich befindet
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;
            // Nächste Zeile zeichnen
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}