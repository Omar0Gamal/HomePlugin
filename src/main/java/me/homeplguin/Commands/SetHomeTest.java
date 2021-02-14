package me.homeplguin.Commands;

import me.homeplguin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeTest  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length != 0){
                Main.getInstance().getDb().AddHome(p.getUniqueId(),p.getLocation(),args[0]);
                p.sendMessage("Your home: "+ args[0] +" has been added");
            }
        }
        return true;
    }
}
