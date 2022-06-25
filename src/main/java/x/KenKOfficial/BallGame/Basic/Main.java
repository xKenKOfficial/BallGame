package x.KenKOfficial.BallGame.Basic;

import x.KenKOfficial.BallGame.Discord.DiscordRP;
import x.KenKOfficial.BallGame.Frames.GameFrame;
import x.KenKOfficial.BallGame.Utils.GameUtil;
import x.KenKOfficial.BallGame.Utils.Loggers.GLogger;

public class Main
{
    private static final DiscordRP discordRP = new DiscordRP();

    public static void main(String[] args)
    {
        GLogger.getLogger().info("Loading: " + GameUtil.getName() + "-" + GameUtil.getVersion());
        new GameFrame().setVisible(true);
        discordRP.start();
    }

    public static DiscordRP getDiscordRP()
    {
        return discordRP;
    }
}
