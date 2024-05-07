package arod.prisonpickaxe.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class Autosmelt extends Enchantment {
    @Override
    public String getName() {
        return "Molten Core";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return item.getType().toString().contains("_PICKAXE");
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey("prisonpickaxeplugin", "autosmelt");
    }

    @Override
    public String getTranslationKey() {
        return "enchantment.minecraft.autosmelt";
    }
}
