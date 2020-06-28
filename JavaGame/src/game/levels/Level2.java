/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.cs.engine.*;
import game.objects.Coin;
import game.Game;
import game.collisions.Pickup;
import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author sf_domingo
 */
public class Level2 extends GameLevel {

    private static final int NUM_COINS = 56;

    @Override
    public void populate(Game game) {
        super.populate(game);

        // Upper Grounds
        Shape tallGroundShape = new BoxShape(5, 10);
        Body tallGround = new StaticBody(this, tallGroundShape);
        tallGround.setPosition(new Vec2(-25, 9));
        BodyImage tallGround1image = new BodyImage("data/tallGround1.png", 21.0f);
        tallGround.addImage(tallGround1image);

        Shape tallGroundShape2 = new BoxShape(10, 7.5f);
        Body tallGround2 = new StaticBody(this, tallGroundShape2);
        tallGround2.setPosition(new Vec2(5, 6.5f));
        BodyImage tallGround2image = new BodyImage("data/tallGround2.png", 16.0f);
        tallGround2.addImage(tallGround2image);

        Shape tallGroundShape3 = new BoxShape(5, 10);
        Body tallGround3 = new StaticBody(this, tallGroundShape3);
        tallGround3.setPosition(new Vec2(35, 9));
        BodyImage tallGround3image = new BodyImage("data/tallGround3.png", 21.0f);
        tallGround3.addImage(tallGround3image);
//        tallGround3.setFillColor(new Color(17, 32, 36));

        // make level limits
        Shape limitShape = new BoxShape(0.5f, 20);
        Body limit = new StaticBody(this, limitShape);
        limit.setPosition(new Vec2(-30.5f, 15));

        Body limit2 = new StaticBody(this, limitShape);
        limit2.setPosition(new Vec2(40.5f, 15));

        // make the lower grounds
        Shape groundShape = new BoxShape(7.5f, 1);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(-12.5f, -1));
        BodyImage lowerGroundImage = new BodyImage("data/lowerGround.png", 3.0f);
        ground.addImage(lowerGroundImage);

        Shape groundShape2 = new BoxShape(7.5f, 1);
        Body ground2 = new StaticBody(this, groundShape2);
        ground2.setPosition(new Vec2(22.5f, -1));
        ground2.addImage(lowerGroundImage);

        // make some platforms
        Shape platformShape = new BoxShape(1.0f, 1.0f);

        for (int i = 0; i < 3; i++) {
            Body platform1 = new StaticBody(this, platformShape);
            platform1.setPosition(new Vec2(-10.0f + 2 * i, 7.5f));
            BodyImage platformImage = new BodyImage("data/tile.png", 2.0f);
            platform1.addImage(platformImage);
        }
        
        for (int i = 0; i < 3; i++) {
            Body platform1 = new StaticBody(this, platformShape);
            platform1.setPosition(new Vec2(25.0f + 2 * i, 8.5f));
            BodyImage platformImage = new BodyImage("data/tile.png", 2.0f);
            platform1.addImage(platformImage);
        }

        // Generate coins
        for (int i = 0; i < 8; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(-19, 1 + 2.5f * i));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 3; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(-6, 1 + 2.5f * i));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 5; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(-16.5f + 2 * i, 1));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 3; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(-6, 9 + 2.5f * i));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 6; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(16, 1 + 2.5f * i));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 10; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(-4 + 2 * i, 15));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 5; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(18 + 2 * i, 1));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 3; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(29, 1 + 2.5f * i));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(this, getPlayer());
            coin.setPosition(new Vec2(29, 10 + 2.5f * i));
            coin.addCollisionListener(new Pickup(game.getPlayer()));
        }

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-25, 21);
    }

    @Override
    public Vec2 flagPosition() {
        return new Vec2(37, 20.5f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() == NUM_COINS;
    }
}
