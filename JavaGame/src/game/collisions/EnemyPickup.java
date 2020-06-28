/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import game.objects.Boots;
import city.cs.engine.*;
import game.characters.SkeletonCharacter;

/**
 *
 * @author sf_domingo
 */
public class EnemyPickup implements CollisionListener {

    private SkeletonCharacter enemy;

    public EnemyPickup(SkeletonCharacter enemy) {
        this.enemy = enemy;
    }

    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() instanceof SkeletonCharacter && e.getReportingBody() instanceof Boots) {
            enemy.increaseWalkingSpeed();
            e.getReportingBody().destroy();
        }
    }
}
