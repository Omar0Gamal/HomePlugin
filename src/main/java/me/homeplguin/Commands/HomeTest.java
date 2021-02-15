package me.homeplguin.Commands;

import me.homeplguin.Main;
import me.homeplguin.Messages.MessageType;
import me.homeplguin.Utlis;
import org.bukkit.Bukkit;
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
                Location loc = Main.getInstance().getDb().getHome(p.getUniqueId(), args[0]);
                if(loc != null) {
                    Utlis.SendMessage(p,MessageType.InitTeleport);
                    Utlis.AwaitingTeleport.add(p);
                    Utlis.SendMessage(p,MessageType.StartTeleport);

                    Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable()
                    {
                        int time = Integer.getInteger(Utlis.getConfigString("Settings.TeleportTimer"));
                        @Override
                        public void run()
                        {
                            if (this.time == 0)
                            {
                                if(Utlis.AwaitingTeleport.contains(p)){
                                    p.teleport(loc);
                                    Utlis.SendMessage(p,MessageType.PostTeleport);
                                    return;
                                }else {
                                    return;
                                }
                            }
                            p.sendMessage(time+ "");
                            this.time--;
                        }
                    });
                }else {
                    Utlis.SendMessage(p,MessageType.NoHomeWithName);
                }
            }else {
                Utlis.SendMessage(p, MessageType.SettingHomeWithoutAName);
            }

        }
        return true;
    }
}
