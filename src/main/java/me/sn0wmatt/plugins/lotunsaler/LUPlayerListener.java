/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.sn0wmatt.plugins.lotunsaler;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 * @author sn0wmatt
 */
//Credits to Wizzledonker for Config.yml code.

public class LUPlayerListener extends PlayerListener{
public static Permission permission = null;
public static Economy economy = null;

private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private Boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

public lotunsaler plugin;
public LUPlayerListener(lotunsaler instance){
    plugin = instance;
}
  
    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
                
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();
        
        
        if (action == Action.RIGHT_CLICK_BLOCK){
            if (!block.getType().equals(Material.SIGN_POST)){
            return;
            }            
            if (((Sign)block.getState()).getLine(0).toLowerCase().contains("[lot]")){
                ((Sign)block.getState()).setLine(0, ChatColor.GREEN + "[lot]");
            } else {
                return;
            }
            player.get
            Inventory inventory = player.getInventory();
            Sign signState = ((Sign)block.getState());
            String lotsize = signState.getLine(1);
	    String lotprice = signState.getLine(2);
            
            
            //Small lot
            if ((lotsize.contains("small")) && (lotprice.contains("50"))){
			   ItemStack it = new ItemStack(Material.GOLD_INGOT, 50);
                    if (inventory.contains(it)){
                    signState.setLine(1, player.getName() + "'s");
                    signState.setLine(2, lotsize + " lot");
                    signState.update(true);
                    inventory.remove(it);
                    player.sendMessage("You just bought a " + lotsize + " lot!");
                } else {
                    player.sendMessage("Insufficient or incorrectly sorted funds! Please obtain or sort funds correctly!");
                    return;
		    }
            }
            
            
            
            //medium lot
            if ((lotsize.contains("medium")) && (lotprice.contains("500"))){
			  ItemStack it2 = new ItemStack(Material.GOLD_INGOT, 500);
                    if (inventory.contains(it2)){
                    signState.setLine(1, player.getName() + "'s");
                    signState.setLine(2, lotsize + "lot");
                    signState.update(true);
                    inventory.remove(it2);
                    player.sendMessage("You just bought a " + lotsize + "lot!");
                } else {
                    player.sendMessage("Insufficient or incorrectly sorted funds! Please obtain or sort funds correctly!");
                    return;
			}
            }
        
            
            
            //large lot
            if ((lotsize.contains("large")) && ((lotprice.contains("500")))){
			    ItemStack it3 = new ItemStack(Material.GOLD_INGOT, 5000);
                    if (inventory.contains(it3)){
                    signState.setLine(1, player.getName() + "'s");
                    signState.setLine(2, lotsize + "lot");
                    signState.update(true);
                    inventory.remove(Material.GOLD_INGOT); 
                    player.sendMessage("You just bought a " + lotsize + "lot!");
                }
                    if (!inventory.contains(Material.GOLD_INGOT, 5000))
                        player.sendMessage("Insufficient or incorrectly sorted funds! Please obtain or sort funds correctly!");
                    return;
            }
            
            if (lotsize.equals("small") || lotsize.equals("medium") || lotsize.equals("large")){
            plugin.getServer().broadcastMessage(player.getName() + " just bought a " + lotsize + " lot!");
	} else {
	return;
	}
    }
}

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        String sendMessage = plugin.getConfig().getString("messages.lotunsaler.welcome").replace("%player%", event.getPlayer().getName());
        
        event.getPlayer().sendMessage(sendMessage);
    }
}
