package me.homeplguin;

import me.homeplguin.Commands.HomeTest;
import me.homeplguin.Commands.SetHomeTest;
import me.homeplguin.Sql.Database;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;


public final class Main extends JavaPlugin {
    private static Main instance;
    public static HashMap<UUID,Boolean> players = new HashMap<UUID,Boolean>();
    private Database db = new Database();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        db.checkConnection();
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
