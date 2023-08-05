package dev.finny.armorstandgui.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.finny.armorstandgui.utils.MenuUtils;

public class ArmorStandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player p = (Player) sender;

        MenuUtils.openMainMenu(p);

        return true;
    }

}
