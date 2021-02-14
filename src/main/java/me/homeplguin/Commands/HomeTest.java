package me.homeplguin.Commands;

import me.homeplguin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeTest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 0) {
                Location loc = Main.getInstance().getDb().getHome(p.getUniqueId(),args[0]);
                p.teleport(loc);
                return true;
            }
        }
        return true;
    }
}
