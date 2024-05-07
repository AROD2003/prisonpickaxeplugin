package arod.prisonpickaxe.events;

import arod.prisonpickaxe.PrisonPickaxePlugin;
import arod.prisonpickaxe.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class JoinEvent implements Listener {

    private final PrisonPickaxePlugin plugin;

    public JoinEvent(PrisonPickaxePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File playerFile = new File(dataFolder, uuid.toString() + ".yml");
        try {
            if (!playerFile.exists()) {
                playerFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        YamlConfiguration playerConfiguration = YamlConfiguration.loadConfiguration(playerFile);
        playerConfiguration.set("PlayerName", event.getPlayer().getName());
        playerConfiguration.set("PlayerUUID", uuid.toString());
        try {
            playerConfiguration.save(playerFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ItemStack pickaxe = new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setName(ChatColor.AQUA + event.getPlayer().getName() + "'s pickaxe")
                .setLore(Arrays.asList("Autosmelt"))
                .build();
        player.getInventory().addItem(pickaxe);

    }

}
