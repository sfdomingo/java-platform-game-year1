/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import city.cs.engine.*;

/**
 *
 * @author sf_domingo
 */
public class GhostScroll extends StaticBody {
    
    private static final BodyImage scrollImage = new BodyImage ("data/Enemy-scroll.png", 2.0f);
    
    public GhostScroll (World world){
        super(world);
        
        Shape boots = new BoxShape(0.5f, 0.5f);
        Fixture scrollFixture = new GhostlyFixture(this, boots);
        addImage(scrollImage);
    }
}
