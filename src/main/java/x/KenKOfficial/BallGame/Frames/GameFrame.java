package x.KenKOfficial.BallGame.Frames;

import x.KenKOfficial.BallGame.Panels.GamePanel;
import x.KenKOfficial.BallGame.Utils.GameUtil;
import x.KenKOfficial.BallGame.Utils.Loggers.GLogger;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    public GameFrame()
    {
        GLogger.getLogger().info("Loading game frame...");
        this.setTitle(GameUtil.getName() + "-" + GameUtil.getVersion());
        this.setIconImage(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/textures/icon/ballgame.png"))).getImage());
        //this.setBounds(10, 15, 1000, 700);
        this.setLocation(4, 50);
        this.initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        pack();
    }

    private void initComponents()
    {
        GLogger.getLogger().info("Loading components in game: " + GameUtil.getName() + "-" + GameUtil.getVersion());
        this.getContentPane().add(new GamePanel());
    }
}
