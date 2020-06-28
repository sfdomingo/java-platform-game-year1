/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.*;
import game.Game;
import game.characters.GonzaloCharacter;

/**
 *
 * @author sf_domingo
 */
public class FlagListener implements CollisionListener {
    private Game game;
    
    public FlagListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        GonzaloCharacter gonzalo = game.getPlayer();
        if (e.getOtherBody() == gonzalo && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            game.goNextLevel();
        }
    }
}
