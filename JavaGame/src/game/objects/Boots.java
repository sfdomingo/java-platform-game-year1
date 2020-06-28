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
public class Boots extends DynamicBody {
    private static final BodyImage bootsImage = new BodyImage ("data/bootsPower.png", 2.0f);
    
    public Boots (World world){
        super (world);
        
        Shape bow = new PolygonShape(
           -0.997f,-0.25f, -0.247f,0.99f, 0.995f,0.995f, 0.993f,-0.377f, 0.13f,-1.0f);
        Fixture bowFixture = new SolidFixture(this, bow);
        addImage(bootsImage);
    }
}
