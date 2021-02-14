package me.homeplguin;

import me.homeplguin.Sql.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{

    private Database db = new Database();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(db.isPlayerInDb(e.getPlayer().getUniqueId())){
            Main.players.put(e.getPlayer().getUniqueId(),true);
            return;
        }else{
            Main.players.put(e.getPlayer().getUniqueId(),false);
            db.addPlayer(e.getPlayer().getUniqueId());
            Main.players.put(e.getPlayer().getUniqueId(),true);
        }

    }
}
