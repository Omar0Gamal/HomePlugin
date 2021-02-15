package me.homeplguin;

import me.homeplguin.Messages.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class Utlis {

    public static List<Player> AwaitingTeleport = new ArrayList<Player>();


    public static String getConfigString(String path){
        return ChatColor.translateAlternateColorCodes('&',Main.getInstance().getConfig().getString(path));
    }

    public static String GetHostName(){
        if(getConfigString("sql.hostname") == null){
            return "localhost";
        }
        return getConfigString("sql.hostname");
    }
    public static String GetUsername(){
        if(getConfigString("sql.username") == null){
            return "root";
        }
        return getConfigString("sql.username");
    }
    public static String GetPassword(){
        if(getConfigString("sql.password") == null){
            return null;
        }
        return getConfigString("sql.password");
    }
    public static String GetDatabase(){
        if(getConfigString("sql.database") == null){
            return null;
        }
        return getConfigString("sql.database");
    }

    public static void SendMessage(Player p, MessageType type){
        switch (type){
            case InitTeleport:
                String message = getConfigString("Message.InitTeleport");
                p.sendMessage(message);
                break;
            case PostTeleport:
                String msage = getConfigString("Message.PostTeleport");
                p.sendMessage(msage);
                break;
            case NoHomeWithName:
                String m = getConfigString("Message.NoHomeWithName");
                p.sendMessage(m);
                break;
            case AirSetHome:
                String me = getConfigString("Message.AirSetHome");
                p.sendMessage(me);
                break;
            case SetHome:
                String ms = getConfigString("Message.SetHome");
                p.sendMessage(ms);
                break;
            case CancelTeleport:
                String mse = getConfigString("Message.CancelTeleport");
                p.sendMessage(mse);
                break;
            case SettingHomeWithoutAName:
                String mseg = getConfigString("Message.SettingHomeWithoutAName");
                p.sendMessage(mseg);
                break;
            case StartTeleport:
                String msege = getConfigString("Message.StartTeleport");
                p.sendMessage(msege);
                break;
        }
    }
}
