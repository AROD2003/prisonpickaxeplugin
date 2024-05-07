package arod.prisonpickaxe.events;

import arod.prisonpickaxe.PrisonPickaxePlugin;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class aBlockBreakEvent implements Listener {

    private final PrisonPickaxePlugin plugin;

    public aBlockBreakEvent(PrisonPickaxePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack pickaxe = player.getInventory().getItemInMainHand();
        if (pickaxe.getType().toString().contains("_PICKAXE") && pickaxe.hasItemMeta() && pickaxe.getItemMeta().hasLore()) {
            if (pickaxe.getItemMeta().getLore().contains("Autosmelt")) {
                int level = pickaxe.getEnchantmentLevel(Enchantment.getByKey(new NamespacedKey("prisonpickaxeplugin", "autosmelt")));
                if (level > 0 && (event.getBlock().getType() == Material.IRON_ORE || event.getBlock().getType() == Material.DEEPSLATE_IRON_ORE ||
                        event.getBlock().getType() == Material.GOLD_ORE || event.getBlock().getType() == Material.DEEPSLATE_GOLD_ORE)) {
                    double smeltChance = level * 0.2; // Smelt chance: 20% * level
                    if (Math.random() <= smeltChance) {
                        event.setDropItems(false);
                        Material ingotType = event.getBlock().getType() == Material.IRON_ORE || event.getBlock().getType() == Material.DEEPSLATE_IRON_ORE ? Material.IRON_INGOT : Material.GOLD_INGOT;
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(ingotType));
                    }
                }
            }
        }
    }

}
