package game.controls;

import game.objects.ShootTaco;
import game.characters.GonzaloCharacter;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static game.Game.music1;

/**
 * Key handler to control an Walker.
 */
public class Controller extends KeyAdapter {

    private static final float JUMPING_SPEED = 35;
    private static final float WALKING_SPEED = 9.5f;

    private GonzaloCharacter body;
    private WorldView view;
    private ShootTaco taco;
    boolean faceRight = true;

    public static SoundClip shootSound;

    public Controller(GonzaloCharacter body, WorldView view) {
        this.body = body;
        this.view = view;

        try {
            // Open an audio input stream
            shootSound = new SoundClip("data/shoot.wav");
            // Adjusts volume
            shootSound.setVolume(1.0f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }
    }

    /**
     * Handle key press events for walking and jumping.
     *
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) { // W = jump
            Vec2 v = body.getLinearVelocity();
//            only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
                body.removeAllImages();
                body.addImage(GonzaloCharacter.getJumping()); // If Gonzalo is facing Right then the Jump Image facing right should be added.
                if (faceRight == false) {
                    body.removeAllImages();
                    body.addImage(GonzaloCharacter.getJumping2()); // Otherwise the Jump Image facing left should be added instead.
                }
            }
        } else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED); // A = walk left
            body.removeAllImages();
            body.addImage(GonzaloCharacter.getRunningLeft());
            faceRight = false;
        } else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED); // D = walk right
            body.removeAllImages();
            body.addImage(GonzaloCharacter.getRunningRight());
            faceRight = true;
        } else if (code == KeyEvent.VK_SPACE) { // NEED TO FIX FIRE RATE (BY HOLDING SPACE THE PLAYER IS ABLE TO SHOOT AT A HIGH RATE)
            if (body.getStaminaCount() > 5) {
                if (faceRight == true) {
                    taco = new ShootTaco(view.getWorld());
                    taco.setGravityScale(0);
                    taco.setLinearVelocity(new Vec2(60, 0)); // Direction of bullet
                    taco.setPosition(new Vec2(body.getPosition()));
                    body.applyImpulse(new Vec2(-7, 0)); // Direction of recoil

                } else if (faceRight == false) {
                    taco = new ShootTaco(view.getWorld());
                    taco.setGravityScale(0);
                    taco.applyImpulse(new Vec2(-25, 0)); // Direction of bullet
                    taco.setPosition(new Vec2(body.getPosition()));
                    body.applyImpulse(new Vec2(7, 0)); // Direction of recoil
                }
                body.decreaseStaminaCount();
                shootSound.play();
            }

        }
    }

    /**
     * Handle key release events (stop walking).
     *
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e
    ) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(GonzaloCharacter.getIdleLeft());
        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(GonzaloCharacter.getIdleRight());
        } else if (code == KeyEvent.VK_W) {
            body.removeAllImages();
            if (faceRight == true) {
                body.addImage(GonzaloCharacter.getLandingRight());
            } else if (faceRight == false) {
                body.addImage(GonzaloCharacter.getLandingLeft());
            }
        } else if (code == KeyEvent.VK_SPACE) {
            //shootSound.stop();
        }
    }

    public void setBody(GonzaloCharacter body) {
        this.body = body;
    }
}
