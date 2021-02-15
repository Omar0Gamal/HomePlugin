package me.homeplguin.Events;

import me.homeplguin.Messages.MessageType;
import me.homeplguin.Utlis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if(Utlis.AwaitingTeleport.isEmpty()){
            return;
        }else {
            if (Utlis.AwaitingTeleport.contains(e.getPlayer())){
                Utlis.AwaitingTeleport.remove(e.getPlayer());
                Utlis.SendMessage(e.getPlayer(), MessageType.CancelTeleport);
                return;
            }
        }
    }
}
