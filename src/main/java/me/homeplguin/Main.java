package me.homeplguin;

import me.homeplguin.Commands.HomeTest;
import me.homeplguin.Commands.SetHomeTest;
import me.homeplguin.Sql.DataBaseHikrioCp;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {
    private static Main instance;

    public DataBaseHikrioCp getDb() {
        return db;
    }

    private DataBaseHikrioCp db = new DataBaseHikrioCp();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        db.connectToDatabase();
        this.getCommand("SetHomeTest").setExecutor(new SetHomeTest());
        this.getCommand("HomeTest").setExecutor(new HomeTest());
        getServer().getPluginManager().registerEvents(new Events() ,this);
    }

    @Override
    public void onDisable() {
    }

    public static Main getInstance(){
        return instance;
    }

}
