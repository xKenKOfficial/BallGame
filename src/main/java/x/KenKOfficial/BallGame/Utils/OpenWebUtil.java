package x.KenKOfficial.BallGame.Utils;

import java.awt.*;
import java.net.URI;

public class OpenWebUtil
{
    public static void openWeb(String www)
    {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        try {
            if(desktop != null) {
                if(desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI(www));
                }
            }
            throw new NullPointerException();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
