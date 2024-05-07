package arod.prisonpickaxe;

import arod.prisonpickaxe.events.JoinEvent;
import arod.prisonpickaxe.events.aBlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PrisonPickaxePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new aBlockBreakEvent(this), this);
    }



}
