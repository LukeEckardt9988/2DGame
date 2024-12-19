package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip; // Objekt zum Abspielen von Audiodaten
    URL soundURL[] = new URL[30]; // Array zum Speichern der URLs der Audiodateien (maximal 30)

    public Sound() {
        // Laden der Audiodateien im Konstruktor
        soundURL[0] = getClass().getResource("/sounds/Background16.wav"); // Hintergrundmusik
        soundURL[1] = getClass().getResource("/sounds/Coin16.wav"); // Soundeffekt für Münzen
        soundURL[2] = getClass().getResource("/sounds/Key16.wav"); // Soundeffekt für Schlüssel
        soundURL[3] = getClass().getResource("/sounds/OpenDoor16.wav"); // Soundeffekt für Schlüssel
        soundURL[4] = getClass().getResource("/sounds/Knock16.wav"); // Soundeffekt für Schlüssel


    }

    public void setFile(int i) {
        try {
            // Laden der Audiodatei aus dem Array anhand des Index i
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            // Erstellen eines Clips zum Abspielen der Audiodaten
            clip = AudioSystem.getClip();
            // Öffnen des Clips mit den Audiodaten
            clip.open(ais);

        } catch (Exception e) {
            // Fehlerbehandlung: Wenn ein Fehler auftritt, wird eine RuntimeException geworfen
            throw new RuntimeException(e);
        }
    }

    public void play() {
        // Abspielen des Clips
        clip.start();
    }

    public void loop() {
        // Endlosschleife des Clips
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        // Stoppen des Clips
        clip.stop();
    }
}