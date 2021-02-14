package me.homeplguin.Commands;

import me.homeplguin.Sql.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeTest  implements CommandExecutor {

    private Database db = new Database();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length != 0){
                db.addHome(p.getUniqueId(),p.getLocation(),args[0]);
                p.sendMessage("Your home: "+ args[0] +" has been added");
            }
        }
        return true;
    }
}