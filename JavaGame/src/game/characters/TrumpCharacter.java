/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.characters;

import game.collisions.TrumpCollision;
import city.cs.engine.*;
import static game.objects.Coin.coinSound;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * Class for the Final Boss that the player will have to defeat.
 *
 * @author sf_domingo
 */
public class TrumpCharacter extends Walker implements StepListener {

    private static final BodyImage idleRight = new BodyImage("data/trump-idleRight.gif", 6.0f);
    private static final BodyImage idleLeft = new BodyImage("data/trump-idleLeft.gif", 6.0f);
    private static final BodyImage defenseUp = new BodyImage("data/defenseUp.gif", 11.0f);

    /**
     * Declaring a variable for its health points.
     */
    private int lifeCount = 20;

    /**
     * Declaring a SoundClip variable.
     */
    public static SoundClip winSound;

    /**
     * Constructs a boss character.
     * @param world - then it is initialised in world.
     */
    public TrumpCharacter(World world) {
        super(world);

        Shape trumpShape = new PolygonShape(
                -1.92f, -2.93f, -1.82f, 0.26f, 0.48f, 2.88f, 1.8f, 1.43f, 1.78f, -2.98f);
        Fixture trumpFixture = new SolidFixture(this, trumpShape);
        this.setGravityScale(6.0f);

        addImage(idleLeft);
        this.startWalking(-5);

        getWorld().addStepListener(this);
        this.addCollisionListener(new TrumpCollision(this));

        try {
            // Open an audio input stream
            winSound = new SoundClip("data/Win.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }
    }

    /**
     * Retrieves the number of health points.
     * @return the number of health points.
     */
    public int getLifeCount() {
        return lifeCount;
    }

    /**
     * It decreases the number of health points by 1.
     */
    public void decreaseLifeCount() {
        lifeCount--;
        System.out.println("Trump has been hit! HP: " + lifeCount);
    }

    /**
     * Method for when the Boss is defeated.
     */
    public void zeroHealth() {
        if (lifeCount <= 0) {
            this.destroy();
            winSound.play();
            System.out.println("YOU HAVE DEFEATED TRUMP!");
        }
    }

    /**
     * Method for when the Boss will regenerate health points.
     */
    public void restoreHP() {
        if (lifeCount < 10) {
            lifeCount = lifeCount + 3;
            removeAllImages();
            addImage(defenseUp);
        }
    }
    
    /**
     * Method to change the character's image into the idle image.
     */
    public void getIdleRight(){
        removeAllImages();
        addImage(idleRight);
    }

    /**
     * preStep method.
     * @param se - stepEvent.
     */
    @Override
    public void preStep(StepEvent se) {

    }

    /**
     * postStep method.
     * @param se - stepEvent.
     */
    @Override
    public void postStep(StepEvent se) {
        Vec2 v = this.getLinearVelocity();
        // only jump if body is not already jumping
        if (Math.abs(v.y) < 0.01f) {
            this.jump(30);
        }
        if (this.getPosition().x <= -23) {
            removeAllImages();
            addImage(idleRight);
            this.startWalking(10);
        }
        if (this.getPosition().x >= 23) {
            removeAllImages();
            addImage(idleLeft);
            this.startWalking(-10);
        }

    }
}
