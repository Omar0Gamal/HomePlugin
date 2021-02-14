package me.homeplguin;

public class Utlis {
    public static String GetHostName(){
        if(Main.getInstance().getConfig().getString("sql.hostname") == null){
            return "localhost";
        }
        return Main.getInstance().getConfig().getString("sql.hostname");
    }
    public static String GetUsername(){
        if(Main.getInstance().getConfig().getString("sql.username") == null){
            return "root";
        }
        return Main.getInstance().getConfig().getString("sql.username");
    }
    public static String GetPassword(){
        if(Main.getInstance().getConfig().getString("sql.password") == null){
            return null;
        }
        return Main.getInstance().getConfig().getString("sql.password");
    }
    public static int GetPort(){
        if(Main.getInstance().getConfig().getInt("sql.port") == 0){
            return 3306;
        }
        return Main.getInstance().getConfig().getInt("sql.port");
    }

    public static String GetDatabase(){
        if(Main.getInstance().getConfig().getString("sql.database") == null){
            return null;
        }
        return Main.getInstance().getConfig().getString("sql.database");
    }
}
