package me.raydond123.customsmelting;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomSmelting extends JavaPlugin {

    public Logger logger = Logger.getLogger("Minecraft");

    public void onEnable() {

        saveDefaultConfig();
        saveConfig();

        logger.info("[CustomSmelting] The plugin has been enabled!");

        setupRecipes();

    }

    public void onDisable() {

        logger.info("[CustomSmelting] The plugin has been disabled!");

    }

    public void setupRecipes() {

        for (String raw : getConfig().getStringList("smelt-recipes")) {

            String[] data = raw.split(":");
            String sourceRawData = data[0];
            int sourceData = Integer.valueOf(sourceRawData);

            String resultItemRawData = data[1];
            int resultItemData = Integer.valueOf(resultItemRawData);

            ItemStack result = new ItemStack(Material.getMaterial(resultItemData));
            ItemMeta resultMeta = result.getItemMeta();

            if (data[2] != null) {

                String resultAmountRawData = data[2];
                int resultAmountData = Integer.valueOf(resultAmountRawData);
                result.setAmount(resultAmountData);

            }

            if (data[3] != null) {

                String resultNameData = data[3];
                resultMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', resultNameData));

            }

            if (data[4] != null) {

                String resultLoreData = data[4];
                String[] lores = resultLoreData.split("~");

                List<String> lore = new ArrayList<String>();

                for (int i = 0; i < lores.length; i++) {

                    lore.add(ChatColor.translateAlternateColorCodes('&', lores[i]));

                }

                resultMeta.setLore(lore);

            }

            result.setItemMeta(resultMeta);

            FurnaceRecipe recipe = new FurnaceRecipe(result, Material.getMaterial(sourceData));

            Bukkit.addRecipe(recipe);

        }

    }

}
