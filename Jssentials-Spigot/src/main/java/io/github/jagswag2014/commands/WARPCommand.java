package io.github.jagswag2014.commands;

import io.github.jagswag2014.Jssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WARPCommand extends CommandParent {

    public WARPCommand(Jssentials plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
