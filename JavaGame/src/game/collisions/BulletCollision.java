/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.*;
import game.characters.GonzaloCharacter;
import game.objects.ShootTaco;
import game.characters.SkeletonCharacter;
import static game.objects.Coin.coinSound;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author sf_domingo
 */
public class BulletCollision implements CollisionListener {

    private ShootTaco taco;
    public static SoundClip hitSound;
    

    public BulletCollision(ShootTaco taco) {
        this.taco = taco;
        
        try {
            // Open an audio input stream
            hitSound = new SoundClip("data/Hit.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof SkeletonCharacter) { // If the Taco(Bullet) touches the Enemy then both elements will be destroyed.
            hitSound.play();
            e.getReportingBody().destroy();
            e.getOtherBody().destroy();
            System.out.println("AN ENEMY HAS BEEN SLAIN!");
        } else if (!(e.getOtherBody() instanceof GonzaloCharacter)) { // If the Taco(Bullet) does not collide with Gonzalo it will disappear. 
            e.getReportingBody().destroy();                          // Therefore, since it's impossible to hit Gonzalo the Taco will disappear if something else is hit.
        }
    }
}
