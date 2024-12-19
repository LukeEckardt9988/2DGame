package main;

import entity.NPC_Paulus;
import objects.OBJ_Coin;
import objects.OBJ_Door;
import objects.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Platzieren von Schlüsseln
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 15 * gp.tileSize;
        gp.obj[0].worldY = 25 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 25 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;

        // Platzieren einer Münze
        gp.obj[2] = new OBJ_Coin();
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 39 * gp.tileSize;

        gp.obj[4] = new OBJ_Coin();
        gp.obj[4].worldX = 30 * gp.tileSize;
        gp.obj[4].worldY = 30 * gp.tileSize;

        gp.obj[5] = new OBJ_Coin();
        gp.obj[5].worldX = 30 * gp.tileSize;
        gp.obj[5].worldY = 31 * gp.tileSize;

        gp.obj[6] = new OBJ_Coin();
        gp.obj[6].worldX = 34 * gp.tileSize;
        gp.obj[6].worldY = 32 * gp.tileSize;

        gp.obj[7] = new OBJ_Coin();
        gp.obj[7].worldX = 19 * gp.tileSize;
        gp.obj[7].worldY = 13 * gp.tileSize;

        gp.obj[8] = new OBJ_Coin();
        gp.obj[8].worldX = 20 * gp.tileSize;
        gp.obj[8].worldY = 13 * gp.tileSize;

        gp.obj[9] = new OBJ_Coin();
        gp.obj[9].worldX = 18 * gp.tileSize;
        gp.obj[9].worldY = 13 * gp.tileSize;

        gp.obj[10] = new OBJ_Coin();
        gp.obj[10].worldX = 17 * gp.tileSize;
        gp.obj[10].worldY = 16 * gp.tileSize;

        gp.obj[11] = new OBJ_Coin();
        gp.obj[11].worldX = 18 * gp.tileSize;
        gp.obj[11].worldY = 16 * gp.tileSize;

        gp.obj[12] = new OBJ_Coin();
        gp.obj[12].worldX = 19 * gp.tileSize;
        gp.obj[12].worldY = 16 * gp.tileSize;

        // Platzieren einer Tür
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 20 * gp.tileSize;
        gp.obj[3].worldY = 17 * gp.tileSize;

        gp.obj[13] = new OBJ_Door();
        gp.obj[13].worldX = 19 * gp.tileSize;
        gp.obj[13].worldY = 15 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Paulus(gp);
        gp.npc[0].worldX = gp.tileSize*16;
        gp.npc[0].worldY = gp.tileSize*20;
    }
}