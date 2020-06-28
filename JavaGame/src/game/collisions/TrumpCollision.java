/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.objects.ShootTaco;
import game.characters.TrumpCharacter;
import static game.collisions.BulletCollision.hitSound;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author sf_domingo
 */
public class TrumpCollision implements CollisionListener{
    
    private TrumpCharacter trump;
    
    public static SoundClip hitSound1;
    
    public TrumpCollision(TrumpCharacter trump){
        this.trump = trump;
        
        try {
            // Open an audio input stream
            hitSound1 = new SoundClip("data/Hit.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }
    }
    
    @Override
    public void collide(CollisionEvent e){
        if(e.getOtherBody() instanceof ShootTaco){
            if(e.getReportingBody() instanceof TrumpCharacter){
                hitSound1.play();
                trump.decreaseLifeCount();
                trump.zeroHealth();
                e.getOtherBody().destroy();
            }
            
        }
    }
}

