/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.*;
import game.characters.GonzaloCharacter;

/**
 *
 * @author sf_domingo
 */
public class EnemyCollision implements CollisionListener {
    private GonzaloCharacter gonzalo;
    
    public EnemyCollision(GonzaloCharacter gonzalo) {
        this.gonzalo = gonzalo;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == gonzalo) { 
            gonzalo.decreaseLifeCount(); // Every time Gonzalo touches an enemy he will lose one life.
        }
    }
}
