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
public class Flag extends StaticBody {
    
    public Flag(World world){
        super(world, new BoxShape(0.55f, 1.4f));
        addImage(new BodyImage("data/flag.gif", 2.8f));
    }
}
