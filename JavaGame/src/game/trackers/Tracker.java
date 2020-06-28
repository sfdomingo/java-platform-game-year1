package game.trackers;

import city.cs.engine.*;
import game.Game;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow a particular body.
 */
public class Tracker implements StepListener {

    /**
     * The view
     */
    private WorldView view;

    /**
     * The body
     */
    private Body body;

    private Game game;

    public Tracker(WorldView view, Body body) {
        this.view = view;
        this.body = body;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        //view.setCentre(new Vec2(body.getPosition()));
        float x = body.getPosition().x;
        float y = body.getPosition().y;

        float groundY = 11.5f;

        if (x < -124) {
            view.setCentre(new Vec2(-124, groundY));
        } else if (x > 100) {
            view.setCentre(new Vec2(100, groundY));
        } else {
            view.setCentre(new Vec2(x, groundY));
        }

    }
}
