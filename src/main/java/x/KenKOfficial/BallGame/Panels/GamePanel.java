package x.KenKOfficial.BallGame.Panels;

import x.KenKOfficial.BallGame.Utils.Loggers.GLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener
{
    private final int FRAME_HEIGHT = 600;
    private final int FRAME_WIDTH = 1000;

    private int PALETTE_WIDTH = 100;
    private int PALETTE_HEIGHT = 20;

    private int xp = (FRAME_WIDTH - PALETTE_WIDTH) / 2;
    private int yp = FRAME_HEIGHT - PALETTE_HEIGHT;

    private int diameterBall = 30;
    private int xBall = (FRAME_WIDTH - diameterBall) / 2;
    private int yBall = (FRAME_HEIGHT - diameterBall) / 2;

    private Random generator = new Random();
    private int poems = generator.nextInt(5) + 4;
    private int columns = generator.nextInt(5) + 4;
    private int brickWidth = 50;
    private int brickHeight = 10;
    private int brickInterval= 5;
    private int brickIntervalLeft = (FRAME_WIDTH - (brickWidth*columns + brickInterval*(columns-1))) / 2;
    private int brickIntervalUp = 10;
    private int brickX[][] = new int[columns][poems];
    private int brickY[][] = new int[columns][poems];

    private boolean hitBricks[][] = new boolean[columns][poems];

    private int dx = 2;
    private int dy = -2;

    private Timer timer;

    private String gameStatus = "trwa";

    public GamePanel()
    {
        GLogger.getLogger().info("Loadning game panel...");
        initComponents();
        this.setBackground(Color.black);

        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < poems; j++) {
                brickX[i][j] = i * (brickWidth + brickInterval) + brickIntervalLeft;
                brickY[i][j] = j * (brickHeight + brickInterval) + brickIntervalUp;
                hitBricks[i][j] = false;
            }
        }

        timer = new Timer(10, this);
        timer.start();
        this.addKeyListener(new ControlKey());
        this.setFocusable(true);
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.painted(g);
    }

    private void painted(Graphics g)
    {
        if(gameStatus == "trwa") {
            this.paintPalette(g);
            this.paintBall(g);
            this.paintBricks(g);
        } else {
            this.endGame(g);
        }
    }

    private void paintPalette(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(xp, yp, PALETTE_WIDTH, PALETTE_HEIGHT);
        g.setColor(Color.white);
        g.drawRect(xp, yp, PALETTE_WIDTH, PALETTE_HEIGHT);
    }

    private void paintBall(Graphics g)
    {
        g.setColor(Color.red);
        g.fillOval(xBall, yBall, diameterBall, diameterBall);
        g.setColor(Color.white);
        g.drawOval(xBall, yBall, diameterBall, diameterBall);
    }

    private void paintBricks(Graphics g)
    {
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < poems; j++) {
                if(!hitBricks[i][j]) {
                    g.setColor(Color.red);
                    g.fillRect(brickX[i][j], brickY[i][j], brickWidth, brickHeight);
                    g.setColor(Color.white);
                    g.drawRect(brickX[i][j], brickY[i][j], brickWidth, brickHeight);
                }
            }
        }
    }

    private void endGame(Graphics g) {
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(gameStatus, (FRAME_WIDTH - fontMetrics.stringWidth(gameStatus)) / 2, FRAME_HEIGHT / 2);
    }

    private void rushBall()
    {
        if(xBall <= 0 || xBall > FRAME_WIDTH - diameterBall) {
            dx = dx * -1;
        }
        if(yBall <= 0 || yBall + diameterBall >= yp && xBall < xp + PALETTE_WIDTH) {
            dy = dy * -1;
        }
        xBall = xBall + dx;
        yBall = yBall + dy;
    }

    private void hitBallOnTheBrick()
    {
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < poems; j++) {
                if(hitBricks[i][j] == false) {
                    if(xBall > brickX[i][j] && xBall < brickX[i][j] + brickWidth && yBall + diameterBall > brickY[i][j] && yBall < brickY[i][j] + brickHeight) {
                        dy = -dy;
                        hitBricks[i][j] = true;
                    } else if(yBall > brickY[i][j] && yBall < brickY[i][j] + brickHeight && xBall + diameterBall > brickX[i][j] && xBall < brickX[i][j] + brickWidth) {
                        dx = -dx;
                        hitBricks[i][j] = true;
                    }
                }
            }
        }
    }

    private void statusGame()
    {
        if(yBall > FRAME_HEIGHT) {
            gameStatus = "PRZEGRYWASZ";
        }
        int dontHitBrick = 0;
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < poems; j++) {
                if(hitBricks[i][j] == false) {
                    dontHitBrick += 1;
                }
            }
        }
        if(dontHitBrick == 0) {
            gameStatus = "WYGRYWASZ";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.statusGame();
        this.hitBallOnTheBrick();
        this.rushBall();
        this.repaint();
    }

    private class ControlKey extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && xp > 0) {
                xp = xp - 10;
            }
            if(key == KeyEvent.VK_RIGHT && xp < FRAME_WIDTH - PALETTE_WIDTH) {
                xp = xp + 10;
            }
        }

    }
}
