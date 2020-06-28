/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.*;
import static java.awt.Color.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * This class refers to the graphic elements that the game would contain.
 *
 * @author Sherwin
 */
public class MyView extends UserView {

    private Image background;
    private Image life;
    private Image dead = new ImageIcon("data/dead-message.png").getImage();
    private Image dead2 = new ImageIcon("data/dead-message2.png").getImage();
    private Image finish = new ImageIcon("data/finish-message.png").getImage();
    private Image coin = new ImageIcon("data/staticCoin.png").getImage();
    private Image enemy = new ImageIcon("data/enemy_idle.gif").getImage();
    
    private Game game;

    /**
     * MyView constructor.
     *
     * @param world - initialised inside world.
     * @param width - width size of the view.
     * @param height - height size of the view.
     * @param game - MyView is then initialised inside Game.
     */
    public MyView(World world, int width, int height, Game game) {
        super(world, width, height);
        this.game = game;

    }

    /**
     * It mainly shows the different image backgrounds of the game.
     * @param g - Graphics2D
     */
    @Override
    protected void paintBackground(Graphics2D g) {

        /**
         * It draws a different Background per level.
         */
        if (game.level == 1) {
            background = new ImageIcon("data/Level 1-bg.jpg").getImage();
        } else if (game.level == 2) {
            background = new ImageIcon("data/Level 2-bg.gif").getImage();
        } else if (game.level == 3) {
            background = new ImageIcon("data/Level 3-bg.gif").getImage();
        } else if (game.level == 4) {
            background = new ImageIcon("data/finalboss-bg.gif").getImage();
        }

        g.drawImage(background, 0, 0, 1050, 550, this);

    }

    /**
     * * It shows images/text into the Foreground of the game such as a coin
     * counter, life counter, enemy counter, stamina counter, etc.
     * @param g - Graphics2D
     */
    @Override
    protected void paintForeground(Graphics2D g) {

        /**
         * It sets the Font for the GUI.
         */
        g.setFont(new Font("Arial", Font.BOLD, 22));

        /**
         * It sets the Color Font for the GUI.
         */
        if (game.level == 4) {
            g.setColor(WHITE);
        } else {
            g.setColor(BLACK);
        }
        /**
         * It shows how the Player's stamina which determines if they can shoot
         * or not.
         */
        g.drawString("Shooting Stamina: " + game.getPlayer().getStaminaCount(), 20, 170);

        /**
         * It shows how many coins has the player collected so far.
         */
        g.drawString(": " + game.getPlayer().getCoinCount(), 50, 83);
        g.drawImage(coin, 15, 60, 30, 30, this);

        /**
         * It shows how many enemies has the player defeated so far.
         */
        g.drawString(": " + game.getPlayer().getShootCount(), 50, 133);
        g.drawImage(enemy, 15, 90, 40, 50, this);

        /**
         * It shows how much time has the player been playing for.
         */
        g.drawString("Time: " + game.minutes + ":" + game.seconds2 + "" + game.seconds1, 870, 30);

        /**
         * It shows how much Life Hearts does the player have.
         */
        if (game.getPlayer().getLifeCount() == 3) {
            life = new ImageIcon("data/life3.png").getImage();
        } else if (game.getPlayer().getLifeCount() == 2) {
            life = new ImageIcon("data/life2.png").getImage();
        } else if (game.getPlayer().getLifeCount() == 1) {
            life = new ImageIcon("data/life1.png").getImage();
        } else if (game.getPlayer().getLifeCount() == 0) {
            life = new ImageIcon("data/life0.png").getImage();
        }
        g.drawImage(life, 10, 10, 135, 35, this);

        /**
         * This is shown if the player has been defeated.
         */
        if (game.getPlayer().getLifeCount() == 0 && game.level < 4) {
            g.drawImage(dead, 275, 170, this);
            game.timer.stop();
            game.stopMusic();
        }
        if (game.getPlayer().getLifeCount() == 0 && game.level >= 4) {
            g.drawImage(dead2, 275, 170, this);
            game.timer.stop();
            game.stopMusic();
        }

        /**
         * This is shown if the player has defeated the Boss.
         */
        if (game.level == 4) {
            g.setFont(new Font("Calibri", Font.BOLD, 40));
            g.drawString("Boss Health: " + game.getBoss().getLifeCount(), 700, 200);
            if (game.getBoss().getLifeCount() == 0) {
                g.drawImage(finish, 275, 170, this);
                game.timer.stop();
            }
        }

        /**
         * * A second timer is shown to tell the player that Level 2 has to be
         * completed before the time runs out.
         */
        if (game.level == 2 && game.level2seconds > 0) {
            g.setFont(new Font("Calibri", Font.BOLD, 35));
            g.drawString("COLLECT ALL COINS BEFORE " + game.level2seconds + " SECONDS", 200, 100);
        } else if (game.level2seconds == 0) {
            g.setFont(new Font("Calibri", Font.BOLD, 40));
            g.drawString("YOU RAN OUT OF TIME! BE FASTER NEXT TIME!", 110, 150);
            game.timer.stop();
            game.getPlayer().destroy();
            game.getPlayer().setLifeCount(0);
        } else {

        }

    }

}
