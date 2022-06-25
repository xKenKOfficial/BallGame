package x.KenKOfficial.BallGame.Utils.Loggers;

import x.KenKOfficial.BallGame.Utils.GameUtil;

import java.util.logging.Logger;

public class GLogger
{
    private static final Logger logger = Logger.getLogger(GameUtil.getName());

    public static Logger getLogger() {
        return logger;
    }
}
