package us.dosantos.buildffa.mangers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.dosantos.buildffa.BuildFFA;

public class KitManager {
    private BuildFFA plugin;


    public void GiveKit(Player player) {
        ItemStack Helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        Helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Helmet.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        Chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Chestplate.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        Leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Leggings.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        Boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Boots.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        Sword.addEnchantment(Enchantment.DURABILITY, 3);
        Sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        ItemStack Gapple = new ItemStack(Material.GOLDEN_APPLE, 7);
        ItemStack Bow = new ItemStack(Material.BOW, 1);
        Bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        ItemStack Arrows = new ItemStack(Material.ARROW, 12);
        ItemStack Blocks = new ItemStack(Material.COBBLESTONE, 64);
        ItemStack FISHING = new ItemStack(Material.FISHING_ROD, 1);
        FISHING.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Lava1 = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemStack Lava2 = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemStack Water1 = new ItemStack(Material.WATER_BUCKET, 1);
        ItemStack Water2 = new ItemStack(Material.WATER_BUCKET, 1);
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
        player.setMaxHealth(20.0D);
        player.setHealth(20.0D);
        for (PotionEffect pe : player.getActivePotionEffects())
            player.removePotionEffect(pe.getType());
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 1));
        player.getInventory().setHelmet(Helmet);
        player.getInventory().setChestplate(Chestplate);
        player.getInventory().setLeggings(Leggings);
        player.getInventory().setBoots(Boots);
        player.getInventory().setItem(0, Sword);
        player.getInventory().setItem(1, Bow);
        player.getInventory().setItem(15, Arrows);
        player.getInventory().setItem(3, FISHING);
        player.getInventory().setItem(4, Blocks);
        player.getInventory().setItem(8, Lava1);
        player.getInventory().setItem(7, Lava2);
        player.getInventory().setItem(6, Water1);
        player.getInventory().setItem(5, Water2);
        player.getInventory().setItem(2, Gapple);

        player.updateInventory();
    }

    public void Refill(Player player) {
        ItemStack Helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        Helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Helmet.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        Chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Chestplate.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        Leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Leggings.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        Boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Boots.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        Sword.addEnchantment(Enchantment.DURABILITY, 3);
        Sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        ItemStack Gapple = new ItemStack(Material.GOLDEN_APPLE, 7);
        ItemStack Bow = new ItemStack(Material.BOW, 1);
        Bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        ItemStack Arrows = new ItemStack(Material.ARROW, 12);
        ItemStack Blocks = new ItemStack(Material.COBBLESTONE, 64);
        ItemStack FISHING = new ItemStack(Material.FISHING_ROD, 1);
        FISHING.addEnchantment(Enchantment.DURABILITY, 3);
        ItemStack Lava1 = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemStack Lava2 = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemStack Water1 = new ItemStack(Material.WATER_BUCKET, 1);
        ItemStack Water2 = new ItemStack(Material.WATER_BUCKET, 1);


        player.getInventory().clear();
        player.getInventory().setHelmet(Helmet);
        player.getInventory().setChestplate(Chestplate);
        player.getInventory().setLeggings(Leggings);
        player.getInventory().setBoots(Boots);
        player.getInventory().setItem(0, Sword);
        player.getInventory().setItem(1, Bow);
        player.getInventory().setItem(15, Arrows);
        player.getInventory().setItem(3, FISHING);
        player.getInventory().setItem(4, Blocks);
        player.getInventory().setItem(8, Lava1);
        player.getInventory().setItem(7, Lava2);
        player.getInventory().setItem(6, Water1);
        player.getInventory().setItem(5, Water2);
        player.getInventory().setItem(2, Gapple);

        player.updateInventory();
    }
}
