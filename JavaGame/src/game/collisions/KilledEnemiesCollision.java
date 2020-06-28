/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collisions;

import city.cs.engine.*;
import game.characters.GonzaloCharacter;
import game.objects.ShootTaco;
import game.characters.SkeletonCharacter;

/**
 *
 * @author sf_domingo
 */
public class KilledEnemiesCollision implements CollisionListener{
    
    private GonzaloCharacter gonzalo;
    private SkeletonCharacter enemy;
    
    public KilledEnemiesCollision(GonzaloCharacter gonzalo, SkeletonCharacter enemy){
        this.gonzalo = gonzalo;
        this.enemy = enemy;
    }
    
    @Override
    public void collide(CollisionEvent e){
        if(e.getOtherBody() instanceof ShootTaco){
            if(e.getReportingBody() instanceof SkeletonCharacter){
                gonzalo.increaseShootCount();
            }
        }
    }
}
