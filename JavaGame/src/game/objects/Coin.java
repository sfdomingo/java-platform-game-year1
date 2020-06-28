/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import city.cs.engine.*;
import game.characters.GonzaloCharacter;
import static game.controls.Controller.shootSound;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class for the coins that the player will collect.
 * @author sf_domingo
 */
public class Coin extends StaticBody implements SensorListener {

    private static final BodyImage coinImage = new BodyImage("data/coin.gif", 2.0f);
    private GonzaloCharacter gonzalo;

    /**
     * Declaring a SoundClip variable.
     */
    public static SoundClip coinSound;

    /**
     * Constructs a coin object.
     * @param world - the world where this object is being initialised.
     * @param gonzalo - the body that will collect this object.
     */
    public Coin(World world, GonzaloCharacter gonzalo) {
        super(world);
        this.gonzalo = gonzalo;

        Shape coin = new CircleShape(0.70f);
        Sensor coinSensor = new Sensor(this, coin);

        coinSensor.addSensorListener(this);
        addImage(coinImage);

        try {
            // Open an audio input stream
            coinSound = new SoundClip("data/coinSound.wav");
            // Adjusts volume
            coinSound.setVolume(0.5f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }
    }

    /**
     * Method for when a body touches this sensor.
     * @param e - sensor event.
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof GonzaloCharacter) {
            gonzalo.incrementCoinCount();
            removeAllImages();
            e.getSensor().destroy();
            coinSound.play();
        }
    }

    /**
     * Method for when a body doesn't touch this sensor.
     * @param e - sensor event.
     */
    @Override
    public void endContact(SensorEvent e) {
    }
}
