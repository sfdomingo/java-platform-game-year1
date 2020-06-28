/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.characters;

import city.cs.engine.*;

/**
 * Class for Skeletons, enemy mob that will appear throughout the game.
 * @author sf_domingo
 */
public class SkeletonCharacter extends Walker {

    private static final BodyImage enemy = new BodyImage("data/enemy_idle.gif", 3.0f);
    private static final BodyImage walkingRight = new BodyImage("data/skeleton-right.gif", 3.0f);
    private static final BodyImage walkingLeft = new BodyImage("data/skeleton-left.gif", 3.0f);

    /**
     * Boolean that checks whether this body is facing right or not.
     */
    public boolean facingRight = true;

    /**
     * Declaring variable for the Speed in which this body is gonna walk for.      
     */
    private int walkingSpeed = 5;

    /**
     * Constructs a Skeleton.
     * @param world - it is initialised inside world.
     */
    public SkeletonCharacter(World world) {
        super(world);

        Shape lowerBodyShape = new PolygonShape(
                -0.94f, -1.5f, -0.94f, -1.32f, -0.65f, -0.75f, 0.0f, -0.75f, -0.09f, -1.5f);
        Fixture lowerBodyFixture = new SolidFixture(this, lowerBodyShape);
        Shape upperBodyShape = new PolygonShape(
                -1.03f, -0.75f, -1.03f, 0.0f, 0.43f, 0.0f, 0.43f, -0.75f);
        Fixture upperBodyFixture = new SolidFixture(this, upperBodyShape);
        Shape headShape = new PolygonShape(
                -0.66f, 0.0f, 0.09f, 0.0f, 0.09f, 0.75f, -0.66f, 0.75f);
        Fixture headFixture = new SolidFixture(this, headShape);
        addImage(enemy);

    }

    /**
     * Retrieves the image for when the enemy is idle.
     * @return idle image
     */
    public static BodyImage getIdle() {
        return enemy;
    }

    /**
     * Retrieves the image when the enemy is walking right.
     * @return walking right image.
     */
    public static BodyImage getWalkingRight() {
        return walkingRight;
    }

    /**
     * Retrieves the image when the enemy is walking left.
     * @return walking left image.
     */
    public static BodyImage getWalkingLeft() {
        return walkingLeft;
    }

    /**
     * It checks if the enemy is facing right.
     * @return boolean for facing right.
     */
    public boolean isFacingRight() {
        return facingRight;
    }

    /**
     * Sets the direction an enemy is facing.
     * @param b - true or false
     */
    public void setIsFacingRight(boolean b) {
        this.facingRight = b;
    }

    /**
     * Retrieves the speed the enemy is walking at.
     * @return value of speed.
     */
    public int getWalkingSpeed() {
        return walkingSpeed;
    }

    /**
     * It increases the walking speed by 5.
     */
    public void increaseWalkingSpeed() {
        walkingSpeed = walkingSpeed + 10;
    }
}
