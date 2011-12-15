package me.sn0wmatt.plugins.lotunsaler;

import java.io.File;
import org.bukkit.Material;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class lotunsaler extends JavaPlugin {
    private final LUPlayerListener playerListener = new LUPlayerListener(this);
    public void onDisable() {
        // TODO: Place any custom disable code here.
        System.out.println(this + " is now disabled!");
        
    }

    public void onEnable() {
        // TODO: Place any custom enable code here, such as registering events      
        System.out.println(this + ", by sn0wmatt, is now enabled on server!");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.High, this);
        pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
	  setupConfig();
    }

    private void setupConfig() {
	if (!new File(getDataFolder(), "config.yml").exists()) {

		System.out.println(this + " Config not found! Generating a new one...");

		getConfig().options().header("#LotUnsaler config file, use this to config messages and other options");
		
                getConfig().addDefault("messages.lotunsaler.welcome", "Welcome, %player%, our server is using the lotunsaler plugin. To use this plugin, right click on a [lot] sign and if you have 50, 500, or 5000 gold nuggets, than you will buy the lot.");
                getConfig().addDefault("currency.lotunsaler", Material.GOLD_NUGGET);
	} else {
		System.out.println(this + " Config file found, loading...");
	}
}
}