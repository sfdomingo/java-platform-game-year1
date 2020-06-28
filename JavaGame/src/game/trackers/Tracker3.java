package game.trackers;

import city.cs.engine.*;
import game.Game;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow a particular body.
 */
public class Tracker3 implements StepListener {

    /**
     * The view
     */
    private WorldView view;

    /**
     * The body
     */
    private Body body;

    private Game game;

    public Tracker3(WorldView view, Body body) {
        this.view = view;
        this.body = body;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        
        float groundY = 11.5f; 
        
        view.setCentre(new Vec2(0, groundY));
        
    }
}