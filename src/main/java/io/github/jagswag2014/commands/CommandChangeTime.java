package io.github.jagswag2014.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChangeTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player) commandSender;
        if(command.getName().equalsIgnoreCase("time")) {
            if(strings.length == 1) {
                World world = Bukkit.getWorld(strings[0]);
                if(world != null) {
                    long time = Long.parseLong(strings[1]);
                    world.setTime(time);
                    sender.sendMessage("§6§oTime is set to " + time + "in" + world.getName() + ".");
                } else {
                    sender.sendMessage("§cUsage: /time <world> <time>");
                }
            } else {
                sender.sendMessage("§cUsage: /time <world> <time>");
            }
        }
        return false;
    }
}
