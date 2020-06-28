/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import game.collisions.EnemyCollision;
import game.collisions.EnemyPickup;
import game.Game;
import game.characters.GonzaloCharacter;
import game.objects.Heart;
import game.collisions.KilledEnemiesCollision;
import game.objects.LimitPlatform;
import game.collisions.Pickup;
import game.objects.RockObject;
import game.objects.RockObject2;
import game.characters.SkeletonCharacter;
import game.collisions.WallCollision;
import game.collisions.WallCollision2;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Sherwin
 */
public class Level3 extends GameLevel {

    private static final int NUM_ENEMIES = 8;
    //private SkeletonCharacter enemy;
    private GonzaloCharacter gonzalo;

    @Override
    public void populate(Game game) {
        super.populate(game);

        // Make the ground
        Shape ground1Shape = new BoxShape(55.0f, 1);
        Body ground1 = new StaticBody(this, ground1Shape);
        ground1.setPosition(new Vec2(-105, 0.5f));
        BodyImage ground1image = new BodyImage("data/ground1-snow.png", 3.25f);
        ground1.addImage(ground1image);

        Shape leftWallShape = new BoxShape(0.5f, 25, new Vec2(-44.5f, 25));
        Fixture leftWall = new SolidFixture(ground1, leftWallShape);
        
        LimitPlatform limit = new LimitPlatform(this);
        limit.setPosition(new Vec2(0, -8));
        limit.addCollisionListener(new Pickup(game.getPlayer()));

        // Make Obstacles
        RockObject rock = new RockObject(this);
        rock.setPosition(new Vec2(-110, 2.8f));
        RockObject2 rock2 = new RockObject2(this);
        rock2.setPosition(new Vec2(-80, 2.8f));

        // Make ground2
        Shape ground2Shape = new BoxShape(10, 1);
        Body ground2 = new StaticBody(this, ground2Shape);
        ground2.setPosition(new Vec2(-30, 0.5f));
        BodyImage ground2image = new BodyImage("data/ground2-snow.png", 4.5f);
        ground2.addImage(ground2image);

        // Make ground3
        Shape ground3Shape = new BoxShape(20, 6);
        Body ground3 = new StaticBody(this, ground3Shape);
        ground3.setPosition(new Vec2(15, 4));
        BodyImage ground3image = new BodyImage("data/ground3-snow.png", 14.0f);
        ground3.addImage(ground3image);

        // Make ground4
        Shape ground4Shape = new BoxShape(15, 3);
        Body ground4 = new StaticBody(this, ground4Shape);
        ground4.setPosition(new Vec2(110, 3.0f));
        BodyImage ground4image = new BodyImage("data/ground4-snow.png", 12.0f);
        ground4.addImage(ground4image);

        Shape rightWallShape = new BoxShape(0.5f, 25, new Vec2(15, 25));
        Fixture rightWall = new SolidFixture(ground4, rightWallShape);

        // Make floating platforms
        BodyImage platformImage = new BodyImage("data/floating-platform-snow.png", 8.0f);
        Shape floatingPlatform = new BoxShape(5, 3);
        Body platform = new StaticBody(this, floatingPlatform);
        platform.setPosition(new Vec2(-15f, 13.5f));
        platform.addImage(platformImage);

        for (int i = 0; i < 3; i++) {
            Body platform2 = new StaticBody(this, floatingPlatform);
            platform2.setPosition(new Vec2(45 + 20 * i, 7.0f));
            platform2.addImage(platformImage);
        }

        Shape tileShape = new BoxShape(1, 1);
        BodyImage tile = new BodyImage("data/tile.png", 2.0f);
        for (int i = 0; i < 5; i++) {
            Body tile1 = new StaticBody(this, tileShape);
            tile1.setPosition(new Vec2(-33f + 2 * i, 8.5f));
            tile1.addImage(tile);

            Body tile2 = new StaticBody(this, tileShape);
            tile2.setPosition(new Vec2(-130 + 2 * i, 8.5f));
            tile2.addImage(tile);

            Body tile3 = new StaticBody(this, tileShape);
            tile3.setPosition(new Vec2(11.0f + 2 * i, 16.0f));
            tile3.addImage(tile);
        }

        // Spawn Enemies
        for (int i = 0; i < 1; i++) {
            SkeletonCharacter enemy = new SkeletonCharacter(this);
            enemy.addCollisionListener(new KilledEnemiesCollision(getPlayer(), enemy));
            enemy.setPosition(new Vec2(-100 + (4 * i), 3.0f));
            enemy.addCollisionListener(new EnemyCollision(getPlayer()));
            enemy.addCollisionListener(new WallCollision(enemy));
            enemy.addCollisionListener(new WallCollision2(enemy));
            enemy.addCollisionListener(new EnemyPickup(enemy));
            enemy.removeAllImages();
            enemy.addImage(SkeletonCharacter.getWalkingRight());
            enemy.startWalking(enemy.getWalkingSpeed());

        }

        RockObject rock3 = new RockObject(this);
        rock3.setPosition(new Vec2(-3.5f, 11.5f));
        RockObject rock4 = new RockObject(this);
        rock4.setPosition(new Vec2(33, 11.5f));

        for (int i = 0; i < 6; i++) {
            SkeletonCharacter enemy2 = new SkeletonCharacter(this);
            enemy2.addCollisionListener(new KilledEnemiesCollision(getPlayer(), enemy2));
            enemy2.setPosition(new Vec2(0 + 5 * i, 11.5f));
            enemy2.addCollisionListener(new EnemyCollision(getPlayer()));
        }

        for (int i = 0; i < 3; i++) {
            SkeletonCharacter enemy2 = new SkeletonCharacter(this);
            enemy2.addCollisionListener(new KilledEnemiesCollision(getPlayer(), enemy2));
            enemy2.setPosition(new Vec2(-38 + 8 * i, 2.5f));
            enemy2.addCollisionListener(new EnemyCollision(getPlayer()));
        }

        //Spawn Hearts
        Heart heart = new Heart(this);
        heart.setPosition(new Vec2(15, 20));
        heart.addCollisionListener(new Pickup(getPlayer()));

        Heart heart2 = new Heart(this);
        heart2.setPosition(new Vec2(-130, 10));
        heart2.addCollisionListener(new Pickup(getPlayer()));

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(120, 8); // original x is120. 8
    }

    @Override
    public Vec2 flagPosition() {
        return new Vec2(-140, 3);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getShootCount() >= NUM_ENEMIES;
    }
}
