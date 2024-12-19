package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); // Erstellen des Spielfensters
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließen des Fensters beendet die Anwendung
        window.setResizable(false); // Größe des Fensters kann nicht geändert werden
        window.setTitle("2d Adventure"); // Titel des Fensters

        GamePanel gamePanel = new GamePanel(); // Erstellen des Spielpanels
        window.add(gamePanel); // Hinzufügen des Spielpanels zum Fenster

        window.pack(); // Anpassen der Fenstergröße an die bevorzugte Größe des Spielpanels

        window.setLocationRelativeTo(null); // Zentrieren des Fensters auf dem Bildschirm
        window.setVisible(true); // Anzeigen des Fensters

        gamePanel.setupGame(); // Initialisieren des Spiels
        gamePanel.startGameThread(); // Starten des Spiel-Threads
    }
}