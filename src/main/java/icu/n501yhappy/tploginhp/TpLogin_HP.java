package icu.n501yhappy.tploginhp;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TpLogin_HP extends JavaPlugin implements Listener {
    public static Configuration config;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info(ChatColor.GREEN+"PlaceholderAPI plugin was found");
        } else {
            getLogger().info(ChatColor.YELLOW+"PlaceholderAPI plugin was not found");
            //Bukkit.getPluginManager().disablePlugin(this);
        }
        saveDefaultConfig();
        config = getConfig();
        getServer().getPluginManager().registerEvents(this,this);
        getLogger().info("Plugin Loaded");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void OnJoin(PlayerJoinEvent event){
        if (!config.getString("messages.join").isEmpty()) event.setJoinMessage(PlaceholderAPI.setPlaceholders(event.getPlayer(), config.getString("messages.join")));
        event.getPlayer().teleport(new Location(Bukkit.getWorld(config.getString("world_name")),config.getDouble("x"),config.getDouble("y"),config.getDouble("z"), (float) config.getDouble("yaw"), (float) config.getDouble("pitch")));
    }
    @EventHandler
    public void OnLeft(PlayerQuitEvent event){
        if (!config.getString("messages.left").isEmpty()) event.setQuitMessage(PlaceholderAPI.setPlaceholders(event.getPlayer(), config.getString("messages.left")));
    }
}
