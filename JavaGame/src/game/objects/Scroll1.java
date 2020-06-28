/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import city.cs.engine.*;
import game.characters.GonzaloCharacter;

/**
 *
 * @author sf_domingo
 */
public class Scroll1 extends StaticBody implements SensorListener {

    private static final BodyImage scrollOpen = new BodyImage("data/scrolltext1.png", 12.0f);
    private static final BodyImage scrollClose = new BodyImage("data/Enemy-scroll.png", 2.0f);

    public Scroll1(World world, GonzaloCharacter gonzalo) {
        super(world);

        Shape chestShape = new BoxShape(1, 1);
        Fixture chestGhost = new GhostlyFixture(this, chestShape);

        Shape sensorShape = new BoxShape(3, 3);
        Sensor chest = new Sensor(this, sensorShape);

        chest.addSensorListener(this);
        addImage(scrollClose);
    }

    public static BodyImage getScrollText() {
        return scrollClose;
    }

    public static BodyImage getScrollOpen() {
        return scrollOpen;
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof GonzaloCharacter) {
            removeAllImages();
            addImage(Scroll1.scrollOpen);
        }
    }

    @Override
    public void endContact(SensorEvent e) {
        removeAllImages();
        addImage(Scroll1.scrollClose);
    }
}
