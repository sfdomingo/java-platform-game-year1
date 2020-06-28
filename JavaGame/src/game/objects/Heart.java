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
public class Heart extends DynamicBody {
    private static final BodyImage heartImage = new BodyImage ("data/heart.png", 2.0f);
    
    public Heart (World world){
        super(world);
        
        Shape leftHeart = new PolygonShape(
            -1.174f,0.625f, -1.168f,0.107f, -0.001f,-0.997f, -0.004f,0.64f, -0.528f,0.982f);
        Fixture heartFixture = new SolidFixture(this, leftHeart);
        
        Shape rightHeart = new PolygonShape(
           1.165f,0.631f, 1.171f,0.095f, -0.001f,-0.997f, -0.004f,0.64f, 0.567f,0.985f);
        Fixture heartFixture2 = new SolidFixture(this, rightHeart);
        addImage(heartImage);
    }
    
}
