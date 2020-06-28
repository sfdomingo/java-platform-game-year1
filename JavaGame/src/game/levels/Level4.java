/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.cs.engine.*;
import game.Game;
import org.jbox2d.common.Vec2;

/**
 *
 * @author sf_domingo
 */
public class Level4 extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);

        Shape ground = new BoxShape(25, 1);
        Body ground1 = new StaticBody(this, ground);
        ground1.setPosition(new Vec2(0, -1));

        Shape wall = new BoxShape(0.5f, 10);
        Body wall1 = new StaticBody(this, wall);
        wall1.setPosition(new Vec2(-25.5f, 10));
        Body wall2 = new StaticBody(this, wall);
        wall2.setPosition(new Vec2(25.5f, 10));

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-24, 2);
    }

    @Override
    public Vec2 flagPosition() {
        return new Vec2(24, 2.0f);
    }

    @Override
    public boolean isCompleted() {
        return getBoss().getLifeCount() == 0;
    }
}
