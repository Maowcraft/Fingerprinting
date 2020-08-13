package maowcraft.fingerprinting;

import maowcraft.fingerprinting.command.CommandInspect;
import maowcraft.fingerprinting.event.EntityEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Fingerprinting extends JavaPlugin {
    private static Fingerprinting instance;

    @Override
    public void onEnable() {
        instance = this;

        registerEvents();

        Objects.requireNonNull(this.getCommand("inspect")).setExecutor(new CommandInspect());
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new EntityEvents(), this);
    }

    public static Fingerprinting getInstance() {
        return instance;
    }
}
