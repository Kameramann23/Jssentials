package io.github.jagswag2014.commands;

import io.github.jagswag2014.Jssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ZUtilityCommands extends ZCommandParent {

    public ZUtilityCommands(Jssentials plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}