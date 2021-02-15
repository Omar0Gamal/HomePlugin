package me.homeplguin.Commands;

import me.homeplguin.Main;
import me.homeplguin.Utlis;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.homeplguin.Messages.MessageType.*;

public class SetHomeTest  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length != 0){
                Location blockunder = p.getLocation();
                blockunder.setY(blockunder.getY() - 1.0);
                if(blockunder.getBlock().getType() != Material.AIR  && blockunder.getBlock().getType() != Material.CAVE_AIR && blockunder.getBlock().getType() != Material.VOID_AIR) {
                    Main.getInstance().getDb().AddHome(p.getUniqueId(), p.getLocation(), args[0]);
                    Utlis.SendMessage(p,SetHome);
                }else {
                    Utlis.SendMessage(p,AirSetHome);
                }
            }else {
                Utlis.SendMessage(p,SettingHomeWithoutAName);
            }
        }
        return true;
    }
}
