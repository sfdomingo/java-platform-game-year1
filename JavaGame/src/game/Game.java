package game;

import game.controls.Controller;
import game.controls.GiveFocus;
import game.panelsAndFrames.ControlPanel;
import game.trackers.Tracker2;
import game.trackers.Tracker3;
import game.trackers.Tracker;
import game.characters.GonzaloCharacter;
import game.characters.TrumpCharacter;
import game.levels.GameLevel;
import game.levels.Level2;
import game.levels.Level4;
import game.levels.Level3;
import game.levels.Level1;
import city.cs.engine.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The computer game.
 */
public class Game implements ActionListener {

    /**
     * The World in which the bodies move and interact.
     */
    private GameLevel world;

    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private UserView view;

    /**
     * Declaring an integer level field.
     */
    public int level;
    private Controller controller;
    final JFrame frame = new JFrame("Gonzalo's Trip: Murica Edition");

    /**
     * Declaring a timer.
     */
    public Timer timer;

    /**
     * Declaring a variable minutes.
     */
    public int minutes = 0;

    /**
     * Declaring a variable seconds.
     */
    public int seconds1 = 0;

    /**
     * Declaring a variable seconds.
     */
    public int seconds2 = 0;

    /**
     * Declaring a variable seconds which will be used only in Level 2.
     */
    public int level2seconds = 20;

    /**
     * Declaring a SoundClip field which will be played from Level 1 to 2.
     */
    public static SoundClip music1;

    /**
     * Declaring a SoundClip field which will be played in Level 4.
     */
    public static SoundClip america;

    /**
     * Declaring a SoundClip field which will be played in Level 3.
     */
    public static SoundClip music2;

    /**
     * Initialise a new Game.
     */
    public Game() {

        // Make the world
        level = 1;
        world = new Level1();
        world.populate(this);

        // Make a view
        view = new MyView(world, 990, 490, this);

        // uncomment this to draw a 1-metre grid over the view
        //view.setGridResolution(1);
        
        // display the view in a frame
        Container buttons = new ControlPanel(this);
        frame.add(buttons, BorderLayout.SOUTH);

        timer = new Timer(1000, this);
        timer.start();

        // Add Wide angle view of the world
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        controller = new Controller(world.getPlayer(), view);
        frame.addKeyListener(controller);
        // Elements inside the brackets is shown to GameLevel
        // Since Tacos are initialised in Controller.class it needs a way to be called into GameLevel.

        // uncomment to make the view track Gonzalo
        world.addStepListener(new Tracker(view, world.getPlayer()));

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 1000, 500);
        
        try {
            // Open an audio
            music1 = new SoundClip("data/Ranchera.mp3");
            music1.loop();
            music1.setVolume(0.2f);
            music2 = new SoundClip("data/Snow.mp3");
            music2.setVolume(0.5f);
            america = new SoundClip("data/America.mp3");
            america.setVolume(0.5f);
            System.out.println("Loading music...");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // start!
        world.start();
    }

    /**
     * It retrieves the World of the game where the elements are.
     *
     * @return the world.
     */
    public GameLevel getWorld() {
        return world;
    }

    /**
     * It retrieves the Character that the player will use.
     *
     * @return the character.
     */
    public GonzaloCharacter getPlayer() {
        return world.getPlayer();
    }

    /**
     * It retrieves the Final Boss that the player will fight.
     *
     * @return the Boss.
     */
    public TrumpCharacter getBoss() {
        return world.getBoss();
    }

    /**
     * It checks if the player has met the requirements to complete the level.
     *
     * @return a true boolean that the level is completed.
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /**
     * It resets variables which are used within the Timer.
     */
    public void resetTime() {
        minutes = 0;
        seconds1 = 0;
        seconds2 = 0;
        level2seconds = 20;
        timer.start();
    }

    /**
     * It stops the current game and creates a new one starting from Level 1.
     */
    public void resetGame() {
        if (level > 2) {
            music2.stop();
            america.stop();
            music1.play();
            music1.loop();
        } else {
            music1.play();
        }
        // Stops the entire Game.
        world.stop();

        // Sets the game to the starting level.
        level = 1;

        // Get a new world.
        world = new Level1();

        // Fill it with bodies.
        world.populate(this);

        // Switch the keyboard control to the new player.
        controller.setBody(world.getPlayer());

        world.addStepListener(new Tracker(view, world.getPlayer()));

        // Show the new world in the view.
        view.setWorld(world);

        // Starts the world.
        world.start();

    }

    /**
     * It sends the player to the next level.
     */
    public void goNextLevel() {
        world.stop();
        GonzaloCharacter oldGonzalo = world.getPlayer();
        if (level == 4) {
            System.exit(0);
        } else if (level == 3) {
            music2.stop();
            america.play();
            america.loop();
            level++;
            // Get a new wolrd
            world = new Level4();
            // Populate world with bodies
            world.populate(this);
            // Add controls for the new character in the new world
            controller.setBody(world.getPlayer());
            // Add tracker to new World to follow the character
            world.addStepListener(new Tracker3(view, world.getPlayer()));
            // Add old character's values to the new Level
            world.getPlayer().setCoinCount(oldGonzalo.getCoinCount());
            world.getPlayer().setLifeCount(oldGonzalo.getLifeCount());
            world.getPlayer().setShootCount(oldGonzalo.getShootCount());
            // Show the new world in view
            view.setWorld(world);
            world.start();
        } else if (level == 2) {
            music1.stop();
            music2.play();
            music2.loop();
            level++;
            // Get a new wolrd
            world = new Level3();
            // Populate world with bodies
            world.populate(this);
            // Add controls for the new character in the new world
            controller.setBody(world.getPlayer());
            // Add tracker to new World to follow the character
            world.addStepListener(new Tracker(view, world.getPlayer()));
            // Add old character's values to the new Level
            world.getPlayer().setCoinCount(oldGonzalo.getCoinCount());
            world.getPlayer().setLifeCount(oldGonzalo.getLifeCount());
            world.getPlayer().setShootCount(oldGonzalo.getShootCount());
            // Show the new world in view
            view.setWorld(world);
            world.start();
        } else if (level == 1) {
            level++;
            // Get a new wolrd
            world = new Level2();
            // Populate world with bodies
            world.populate(this);
            // Add controls for the new character in the new world
            controller.setBody(world.getPlayer());
            // Add tracker to new World to follow the character
            world.addStepListener(new Tracker2(view, world.getPlayer()));
            // Add old character's values to the new Level
            world.getPlayer().setCoinCount(oldGonzalo.getCoinCount());
            world.getPlayer().setLifeCount(oldGonzalo.getLifeCount());
            world.getPlayer().setShootCount(oldGonzalo.getShootCount());
            // Show the new world in view
            view.setWorld(world);
            world.start();

        }
    }

    /**
     * It stops any background music that is being played.
     */
    public void stopMusic() {
        music1.stop();
        music2.stop();
        america.stop();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        seconds1++;
        if (world.getPlayer().getStaminaCount() < 50) {
            world.getPlayer().staminaCount += 3;
            if (world.getPlayer().getStaminaCount() > 50) {
                world.getPlayer().staminaCount = 50;
            }
        }

        if (seconds1 > 9) {
            seconds1 = 0;
            seconds2++;
        }
        if (seconds2 > 5) {
            seconds2 = 0;
            minutes++;
        }

        if (level == 2) {
            level2seconds--;
        }

        if (level == 4) {
            if (seconds1 == 5) {
                world.getBoss().restoreHP();
            }
            if (seconds1 == 7) {
                world.getBoss().getIdleRight();
            }
        }
    }

    /**
     * Run the game.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Game();

    }

    /**
     * It stops the game and disposes the current window of the game but does
     * not end the program at all.
     */
    public void back() {
        world.stop();
        controller.setBody(null);
        music1.stop();
        music2.stop();
        america.stop();
        frame.dispose();

    }
}
