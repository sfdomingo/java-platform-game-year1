package game.collisions;

import game.objects.LimitPlatform;
import game.characters.GonzaloCharacter;
import game.objects.Heart;
import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Collision listener that allows GonzaloCharacter to collect coins.
 */
public class Pickup implements CollisionListener {

    private GonzaloCharacter gonzalo;
    public static SoundClip lifeSound;
    
    public Pickup(GonzaloCharacter gonzalo) {
        this.gonzalo = gonzalo;
        
        try {
            // Open an audio input stream
            lifeSound = new SoundClip("data/1up.wav");
            // Adjusts volume
            lifeSound.setVolume(0.5f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof GonzaloCharacter && e.getReportingBody() instanceof Heart) {
            gonzalo.increaseLifeCount();
            lifeSound.play();
            e.getReportingBody().destroy();
        }

        if (e.getOtherBody() instanceof GonzaloCharacter && e.getReportingBody() instanceof LimitPlatform) {
            gonzalo.destroy();
            gonzalo.zeroHealth();
            gonzalo.setLifeCount(0);
        }
    }

}
