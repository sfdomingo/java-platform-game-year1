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
public class Scroll3 extends StaticBody implements SensorListener {
    private static final BodyImage scrollOpen = new BodyImage("data/scrolltext3.png", 12.0f);
    private static final BodyImage scrollClose = new BodyImage("data/Heart-scroll.png", 2.0f);

    public Scroll3(World world, GonzaloCharacter gonzalo) {
        super(world);

        Shape chestShape = new BoxShape(1, 1);
        Fixture chestGhost = new GhostlyFixture(this, chestShape);

        Shape SensorShape = new BoxShape(3, 3);
        Sensor chest = new Sensor(this, SensorShape);

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
            addImage(Scroll3.scrollOpen);
        }
    }

    @Override
    public void endContact(SensorEvent e) {
        removeAllImages();
        addImage(Scroll3.scrollClose);
    }
}

