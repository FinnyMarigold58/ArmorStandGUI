package dev.finny.armorstandgui;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import dev.finny.armorstandgui.commands.ArmorStandCommand;
import dev.finny.armorstandgui.events.MenuHandler;

public class ArmorStandGui extends JavaPlugin {
    public final Logger logger = this.getLogger();

    @Override
    public void onEnable() {
        logger.info("Plugin has started");

        getCommand("armorstand").setExecutor(new ArmorStandCommand());

        getServer().getPluginManager().registerEvents(new MenuHandler(), this);
    }
}
