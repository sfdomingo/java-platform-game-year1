/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import game.objects.Flag;
import city.cs.engine.*;
import game.collisions.EnemyCollision;
import game.collisions.FlagListener;
import game.Game;
import game.characters.GonzaloCharacter;
import game.characters.TrumpCharacter;
import org.jbox2d.common.Vec2;

/**
 *
 * @author sf_domingo
 */
public abstract class GameLevel extends World {

    private GonzaloCharacter gonzalo;
    private TrumpCharacter trump;

    public GonzaloCharacter getPlayer() {
        return gonzalo;
    }

    public TrumpCharacter getBoss() {
        return trump;
    }

    public void populate(Game game) {
        gonzalo = new GonzaloCharacter(this);
        gonzalo.setPosition(startPosition());

        Flag door = new Flag(this);
        door.setPosition(flagPosition());
        door.addCollisionListener(new FlagListener(game));

        if (game.level == 4) {
            trump = new TrumpCharacter(this);
            trump.setPosition(new Vec2(15, 2));
            trump.addCollisionListener(new EnemyCollision(getPlayer()));
        }

    }

    // Set Character's position
    public abstract Vec2 startPosition();

    // Set Flag's position
    public abstract Vec2 flagPosition();

    // Checks if the level has been successfully completed
    public abstract boolean isCompleted();

}
