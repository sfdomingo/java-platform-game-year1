/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.collisions.BulletCollision;
import city.cs.engine.*;

/**
 *
 * @author Sherwin
 */
public class ShootTaco extends DynamicBody {

    private static final BodyImage tacoImage = new BodyImage("data/taco.png", 2.0f);
    private ShootTaco taco;

    public ShootTaco(World world) {
        super(world);

        Shape leftTaco = new PolygonShape(
                -0.46f, -0.267f, 0.122f, -0.155f, 0.12f, 0.328f, -0.212f, 0.273f, -0.442f, 0.078f);
        SolidFixture leftTacoFixture = new SolidFixture(this, leftTaco);
        
        Shape rightTaco = new PolygonShape(
                0.558f, -0.162f, 0.122f, -0.155f, 0.12f, 0.328f, 0.36f, 0.247f, 0.553f, 0.008f);
        SolidFixture rightTacoFixture = new SolidFixture(this, rightTaco);
        

        addImage(tacoImage);
        
        this.setGravityScale(0.0f);
        
        this.addCollisionListener(new BulletCollision(taco));
    }

}
