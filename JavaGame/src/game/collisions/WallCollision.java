/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.*;
import game.objects.RockObject;
import game.characters.SkeletonCharacter;

/**
 *
 * @author sf_domingo
 */
public class WallCollision implements CollisionListener {

    private SkeletonCharacter skeleton;

    public WallCollision(SkeletonCharacter skeleton) {
        this.skeleton = skeleton;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof RockObject) {
            skeleton.startWalking(skeleton.getWalkingSpeed());
            skeleton.removeAllImages();
            skeleton.addImage(SkeletonCharacter.getWalkingRight());
            skeleton.setIsFacingRight(false);
        }
    }
}
