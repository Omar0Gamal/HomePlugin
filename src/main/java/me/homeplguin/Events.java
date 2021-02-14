package me.homeplguin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(Main.getInstance().getDb().isPlayerInDb(e.getPlayer().getUniqueId())){
            return;
        }else{
            Main.getInstance().getDb().AddPlayer(e.getPlayer().getUniqueId());
        }

    }
}
