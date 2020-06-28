/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.cs.engine.*;
import game.objects.Boots;
import game.objects.Coin;
import game.collisions.EnemyCollision;
import game.collisions.EnemyPickup;
import game.Game;
import game.objects.GhostScroll;
import game.objects.GhostScroll2;
import game.objects.GhostScroll3;
import game.objects.Heart;
import game.collisions.KilledEnemiesCollision;
import game.objects.LimitPlatform;
import game.collisions.Pickup;
import game.objects.RockObject;
import game.objects.RockObject2;
import game.objects.Scroll1;
import game.objects.Scroll2;
import game.objects.Scroll3;
import game.characters.SkeletonCharacter;
import game.collisions.WallCollision;
import game.collisions.WallCollision2;
import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author sf_domingo
 */
public class Level1 extends GameLevel {

    private static final int NUM_COINS = 9;
    public int WALKING_SPEED = 5;

    @Override
    public void populate(Game game) {
        super.populate(game);

        // Make the ground
        Shape ground1Shape = new BoxShape(55.0f, 1);
        Body ground1 = new StaticBody(this, ground1Shape);
        ground1.setPosition(new Vec2(-105, 0.5f));
        BodyImage ground1image = new BodyImage("data/ground1.png", 3.25f);
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
        BodyImage ground2image = new BodyImage("data/ground2.png", 4.5f);
        ground2.addImage(ground2image);

        // Make ground3
        Shape ground3Shape = new BoxShape(20, 6);
        Body ground3 = new StaticBody(this, ground3Shape);
        ground3.setPosition(new Vec2(15, 4));
        BodyImage ground3image = new BodyImage("data/ground3.png", 14.0f);
        ground3.addImage(ground3image);

        // Make ground4
        Shape ground4Shape = new BoxShape(15, 3);
        Body ground4 = new StaticBody(this, ground4Shape);
        ground4.setPosition(new Vec2(110, 3.0f));
        BodyImage ground4image = new BodyImage("data/ground4.png", 12.0f);
        ground4.addImage(ground4image);

        Shape rightWallShape = new BoxShape(0.5f, 25, new Vec2(15, 25));
        Fixture rightWall = new SolidFixture(ground4, rightWallShape);

        // Make floating platforms
        BodyImage platformImage = new BodyImage("data/floating-platform.png", 8.0f);
        Shape floatingPlatform = new BoxShape(5, 3);
        Body platform = new StaticBody(this, floatingPlatform);
        platform.setPosition(new Vec2(-15f, 13.5f));
        platform.addImage(platformImage);

        for (int i = 0; i < 3; i++) {
            Body platform2 = new StaticBody(this, floatingPlatform);
            platform2.setPosition(new Vec2(45 + 20 * i, 12.5f));
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
        Body tile4 = new StaticBody(this, tileShape);
        tile4.setPosition(new Vec2(95, 12.5f));
        tile4.addImage(tile);

        Shape floatingPlatform2 = new BoxShape(2, 1);
        BodyImage platformImage2 = new BodyImage("data/floating-platform2.png", 4.0f);
        for (int i = 0; i < 2; i++) {
            Body platform3 = new StaticBody(this, floatingPlatform2);
            platform3.setPosition(new Vec2(55 + 20 * i, 7.0f));
            platform3.addImage(platformImage2);
        }

        // Spawn Enemies
        SkeletonCharacter enemy = new SkeletonCharacter(this);
        for (int i = 0; i < 1; i++) {
            enemy.setPosition(new Vec2(-100 + (4 * i), 3.0f));
            enemy.addCollisionListener(new EnemyCollision(getPlayer()));
            enemy.addCollisionListener(new WallCollision(enemy));
            enemy.addCollisionListener(new WallCollision2(enemy));
            enemy.addCollisionListener(new EnemyPickup(enemy));
            enemy.addCollisionListener(new KilledEnemiesCollision(getPlayer(), enemy));
            enemy.removeAllImages();
            enemy.addImage(SkeletonCharacter.getWalkingRight());
            enemy.startWalking(enemy.getWalkingSpeed());
        }
        
        SkeletonCharacter enemy3 = new SkeletonCharacter(this);
        enemy3.addCollisionListener(new KilledEnemiesCollision(getPlayer(), enemy));
        enemy3.setPosition(new Vec2(20, 11.5f));
        enemy3.addCollisionListener(new EnemyCollision(getPlayer()));

        SkeletonCharacter enemy2 = new SkeletonCharacter(this);
        enemy2.addCollisionListener(new KilledEnemiesCollision(getPlayer(), enemy));
        enemy2.setPosition(new Vec2(10, 11.5f));
        enemy2.addCollisionListener(new EnemyCollision(getPlayer()));

        /**
         * Spawn Scrolls with Tips for the player && Spawn PowerUp Items.
         */
        Scroll1 scroll1 = new Scroll1(this, game.getPlayer());
        scroll1.setPosition(new Vec2(-115, 5.0f));
        GhostScroll scroll1Ghost = new GhostScroll(this);
        scroll1Ghost.setPosition(new Vec2(-115, 5));

        Scroll2 scroll2 = new Scroll2(this, game.getPlayer());
        scroll2.setPosition(new Vec2(-105, 5.0f));
        GhostScroll2 scroll2Ghost = new GhostScroll2(this);
        scroll2Ghost.setPosition(new Vec2(-105, 5.0f));
        
        Boots boots = new Boots(this);
        boots.setPosition(new Vec2(-105, 3));
        boots.addCollisionListener(new EnemyPickup(enemy));

        Scroll3 scroll3 = new Scroll3(this, game.getPlayer());
        scroll3.setPosition(new Vec2(-70, 5));
        GhostScroll3 scroll3Ghost = new GhostScroll3(this);
        scroll3Ghost.setPosition(new Vec2(-70, 5));
        Heart heart = new Heart(this);
        heart.setPosition(new Vec2(-70, 3));
        heart.addCollisionListener(new Pickup(getPlayer()));

        // Spawn Coins
        for (int i = 0; i < 2; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(55 + 20 * i, 10));
            //coin.addCollisionListener(new Pickup(getPlayer()));
        }

        for (int i = 0; i < 3; i++) {
            Coin coin1 = new Coin(this, getPlayer());
            coin1.setPosition(new Vec2(-129 + (3 * i), 10.5f));
            coin1.addCollisionListener(new Pickup(getPlayer()));

            Coin coin2 = new Coin(this, getPlayer());
            coin2.setPosition(new Vec2(-32.0f + 3 * i, 10.5f));
            coin2.addCollisionListener(new Pickup(getPlayer()));
        }

        Coin coin3 = new Coin(this, getPlayer());
        coin3.setPosition(new Vec2(15, 11.5f));
        coin3.addCollisionListener(new Pickup(getPlayer()));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-140, 2); // original x is -140. 2
    }

    @Override
    public Vec2 flagPosition() {
        return new Vec2(120, 8);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() == NUM_COINS;
    }
}
