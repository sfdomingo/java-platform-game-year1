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
public class GhostScroll2 extends StaticBody {
        
    private static final BodyImage scrollImage = new BodyImage ("data/Boots-scroll.png", 2.0f);
    
    public GhostScroll2 (World world){
        super(world);
        
        Shape boots = new BoxShape(0.5f, 0.5f);
        Fixture scrollFixture = new GhostlyFixture(this, boots);
        addImage(scrollImage);
    }
}
