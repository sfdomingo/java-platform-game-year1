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
public class RockObject extends StaticBody {
    private static final BodyImage rockImage = new BodyImage("data/rock.png", 3.0f);
    
    public RockObject(World world){
        super(world);
        
        Shape rockShape = new PolygonShape(
                -2.4f, -1.49f, -2.39f, 0.08f, -1.35f, 1.27f, -0.02f, 1.5f, 1.48f, 1.2f, 2.4f, 0.06f, 2.39f, -1.49f);
        Fixture rockFixture = new SolidFixture(this, rockShape);
        addImage(rockImage);
    }
}
