/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.characters;

import city.cs.engine.*;
import static game.objects.Coin.coinSound;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class for the main Character in the game.
 * @author Sherwin
 */
public class GonzaloCharacter extends Walker {

    private static final BodyImage idleRight = new BodyImage("data/idle-right.gif", 3.0f);
    private static final BodyImage runningRight = new BodyImage("data/run-right.gif", 3.0f);
    private static final BodyImage idleLeft = new BodyImage("data/idle-left.gif", 3.0f);
    private static final BodyImage runningLeft = new BodyImage("data/run-left.gif", 3.0f);
    private static final BodyImage jumping = new BodyImage("data/jump.png", 3.0f);
    private static final BodyImage jumping2 = new BodyImage("data/jump2.png", 3.0f);
    private static final BodyImage landingRight = new BodyImage("data/landing-right.png", 3.0f);
    private static final BodyImage landingLeft = new BodyImage("data/landing-left.png", 3.0f);
    
    /**
     * Declaring the sound when the player dies.
     */
    public static SoundClip deadSound;
    
    /**
     * Variables for coins that the player picks up.
     */
    public int coinCount;
    
    /**
     * Variables for lives that the player will start with.
     */
    private int lifeCount = 3;
    
    /**
     * Variables for enemies that the player will defeat.
     */
    private int shootCount = 0;

    /**
     * Variable for Stamina.
     */
    public int staminaCount = 50;

    // Creating character

    /**
     * Constructs a Character.
     * @param world - The character is then initialised inside world.
     */
    public GonzaloCharacter(World world) {
        super(world);

        Shape lowerBodyShape = new PolygonShape(
                -0.39f, -1.51f, -0.39f, -0.61f, 0.48f, -0.62f, 0.49f, -1.5f);
        SolidFixture lowerBodyFixture = new SolidFixture(this, lowerBodyShape);
        lowerBodyFixture.setFriction(15);

        Shape upperBodyShape = new PolygonShape(
                -0.77f, -0.62f, 0.68f, -0.62f, 0.68f, 0.26f, -0.78f, 0.26f);
        SolidFixture upperBodyFixture = new SolidFixture(this, upperBodyShape);
        Shape headShape = new PolygonShape(
                0.4f, 0.34f, 0.39f, 1.06f, -0.57f, 1.05f, -0.58f, 0.35f);
        upperBodyFixture.setFriction(0);

        SolidFixture headFixture = new SolidFixture(this, headShape);
        Shape hairShape = new PolygonShape(
                0.4f, 1.04f, 0.66f, 1.23f, -0.57f, 1.23f, -0.57f, 1.04f);
        headFixture.setFriction(0);

        SolidFixture hairFixture = new SolidFixture(this, hairShape);
        hairFixture.setFriction(0);

        this.setGravityScale(5.75f);
        
        try {
            // Open an audio input stream
            deadSound = new SoundClip("data/dead.mp3");
            // Adjusts volume
            deadSound.setVolume(0.5f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException a) {
            System.out.println(a);
        }

        addImage(idleRight);
    }


    /**
     * Retrieves the idle image when the character is facing right.
     * @return image of character facing right.
     */
    public static BodyImage getIdleRight() {
        return idleRight;
    }

    /**
     * Retrieves the idle image when the character is running right.
     * @return image of character running right.
     */
    public static BodyImage getRunningRight() {
        return runningRight;
    }

    /**
     * Retrieves the idle image when the character is facing left.
     * @return image of character facing left.
     */
    public static BodyImage getIdleLeft() {
        return idleLeft;
    }

    /**
     * Retrieves the idle image when the character is running left.
     * @return image of character running left.
     */
    public static BodyImage getRunningLeft() {
        return runningLeft;
    }

    /**
     * Retrieves the idle image when the character is facing right and jumping.
     * @return image of character jumping right.
     */
    public static BodyImage getJumping() {
        return jumping;
    }

    /**
     * Retrieves the idle image when the character is jumping and facing left.
     * @return image of character jumping left.
     */
    public static BodyImage getJumping2() {
        return jumping2;
    }

    /**
     * Retrieves the idle image when the character is landing right.
     * @return image of character landing right.
     */
    public static BodyImage getLandingRight() {
        return landingRight;
    }

    /**
     * Retrieves the idle image when the character is landing left.
     * @return image of character landing left.
     */
    public static BodyImage getLandingLeft() {
        return landingLeft;
    }

    /**
     * Retrieves the number of coins that the player has collected.
     * @return number of coins collected.
     */
    public int getCoinCount() {
        return coinCount;
    }


    /**
     * Sets the number of coins that the player has collected.
     * @param cnt - the number of coins.
     */
    public void setCoinCount(int cnt) {
        this.coinCount = cnt;
    }

    /**
     * It increases the amount of coins collected by 1.
     */
    public void incrementCoinCount() {
        coinCount++;
        System.out.println("OMG IT'S GOLD! Coin Count = " + coinCount);
    }

    // Life counter. Each time an enemy collides with Gonzalo his lifeCount will decrease.
    // When his life reaches 0 Gonzalo will die.

    /**
     * Retrieves the number of lives that the player has.
     * @return number of lives.
     */
    public int getLifeCount() {
        return lifeCount;
    }

    /**
     * It decreases the number of lives by 1.
     */
    public void decreaseLifeCount() {
        lifeCount--;
        System.out.println("AVOID THE ENEMIES! Lives left = " + lifeCount);

        if (lifeCount == 0) {
            this.zeroHealth();
        }
    }
    
    /**
     * Method that destroys the character and plays a sound effect when this dies.
     */
    public void zeroHealth(){
        GonzaloCharacter.this.destroy();
        deadSound.play();
        System.out.println("OH NO! Gonzalo Died!");
    }
    
    /**
     * Method that stops the sound effect.
     */
    public void stopDeadMusic(){
        deadSound.stop();
    }
    
    /**
     * It increases the number of lives by 1.
     */
    public void increaseLifeCount() {
        if (lifeCount >= 3) {
            System.out.println("YOU ALREADY HAVE MAX LIVES!");
        } else {
            lifeCount++;
            System.out.println("YOU HAVE GAINED A LIFE! Lives left = " + lifeCount);
        }
    }

    /**
     * Sets the number of lives that the player has.
     * @param lifeCnt - number of lives
     */
    public void setLifeCount(int lifeCnt) {
        this.lifeCount = lifeCnt;
    }


    /**
     * It retrieves the number of enemies that the player has killed.
     * @return value of enemies that the player has killed.
     */
    public int getShootCount() {
        return shootCount;
    }

    /**
     * It increases the number of enemies killed by 1.
     */
    public void increaseShootCount() {
        shootCount++; // Each time an enemy is defeated the shoot count will increase by 1.
    }

    /**
     * Sets the number of enemies that the player has killed.
     * @param shtCnt - number of enemies killed
     */
    public void setShootCount(int shtCnt) {
        this.shootCount = shtCnt;
    }

    /**
     * It retrieves the value of Stamina that the player has.
     * @return the stamina count.
     */
    public int getStaminaCount() {
        return staminaCount;
    }
    
    /**
     * It decreases the Stamina of the player by 5.
     */
    public void decreaseStaminaCount(){
        staminaCount -= 5;
        if (staminaCount <= 0){
            staminaCount = 0;
        }
    }

}
