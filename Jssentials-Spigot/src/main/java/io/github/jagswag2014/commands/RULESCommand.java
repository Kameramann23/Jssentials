package io.github.jagswag2014.commands;

import io.github.jagswag2014.Jssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RULESCommand extends CommandParent {

    public RULESCommand(Jssentials plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}