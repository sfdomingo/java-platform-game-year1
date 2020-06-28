/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.*;
import game.objects.RockObject2;
import game.characters.SkeletonCharacter;

/**
 *
 * @author sf_domingo
 */
public class WallCollision2 implements CollisionListener {

    private SkeletonCharacter skeleton;

    public WallCollision2(SkeletonCharacter skeleton) {
        this.skeleton = skeleton;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof RockObject2) {
            skeleton.startWalking(-(skeleton.getWalkingSpeed()));
            skeleton.removeAllImages();
            skeleton.addImage(SkeletonCharacter.getWalkingLeft());
            skeleton.setIsFacingRight(false);
        }
    }
}
