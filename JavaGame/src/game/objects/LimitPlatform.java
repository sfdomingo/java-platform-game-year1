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
public class LimitPlatform extends StaticBody {
    
    public LimitPlatform(World world){
        super(world);
        
        Shape platform = new BoxShape(130, 1);
        Fixture platformFixture = new SolidFixture(this, platform);
    }
}
