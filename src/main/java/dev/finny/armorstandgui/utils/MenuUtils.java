package dev.finny.armorstandgui.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MenuUtils {

    public static void openMainMenu(Player p) {
        Inventory main_menu = Bukkit.createInventory(p, 9, Component.text("Armor Stand Gui", TextColor.color(5592575)));

        // Create Button
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta create_meta = create.getItemMeta();
        create_meta.displayName(Component.text("Create", TextColor.color(5635925)));
        ArrayList<Component> create_lore = new ArrayList<>();
        create_lore.add(Component.text("Create a new armor stand.", TextColor.color(11141290)));
        create_meta.lore(create_lore);
        create.setItemMeta(create_meta);

        // Close Button
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.displayName(Component.text("Close", TextColor.color(16733525)));
        close.setItemMeta(close_meta);

        // Add Buttons to Menu
        main_menu.setItem(0, create);
        main_menu.setItem(8, close);
        p.openInventory(main_menu);
    }

    public static void openCreateMenu(Player p) {
        Inventory create_menu = Bukkit.createInventory(p, 9,
                Component.text("Create a Armor Stand", TextColor.color(5635925)));

        ItemStack arms = new ItemStack(Material.ARMOR_STAND);
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack base = new ItemStack(Material.STONE_SLAB);
        ItemStack complete = new ItemStack(Material.GREEN_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);

        ItemMeta arms_meta = arms.getItemMeta();
        arms_meta.displayName(Component.text("Arms", TextColor.color(16777045)));
        arms.setItemMeta(arms_meta);

        ItemMeta glow_meta = glow.getItemMeta();
        glow_meta.displayName(Component.text("Glow"));
        glow.setItemMeta(glow_meta);

        ItemMeta armor_meta = armor.getItemMeta();
        armor_meta.displayName(Component.text("Armor", TextColor.color(5636095)));
        armor.setItemMeta(armor_meta);

        ItemMeta base_meta = base.getItemMeta();
        base_meta.displayName(Component.text("Base", TextColor.color(16755200)));
        base.setItemMeta(base_meta);

        ItemMeta complete_meta = complete.getItemMeta();
        complete_meta.displayName(Component.text("Complete & Create", TextColor.color(5635925)));
        complete.setItemMeta(complete_meta);

        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.displayName(Component.text("Cancel Creation", TextColor.color(16733525)));
        cancel.setItemMeta(cancel_meta);

        create_menu.setItem(0, arms);
        create_menu.setItem(1, glow);
        create_menu.setItem(2, armor);
        create_menu.setItem(3, base);
        create_menu.setItem(7, complete);
        create_menu.setItem(8, cancel);

        p.openInventory(create_menu);
    }

    public static void openConfirmMenu(Player p, Material option) {
        Inventory confirm_menu = Bukkit.createInventory(p, 27,
                Component.text("Confirm Option", TextColor.color(5635925)));

        ItemStack option_item = new ItemStack(option);
        ItemMeta option_meta = option_item.getItemMeta();

        switch (option) {
            case ARMOR_STAND:
                option_meta.displayName(Component.text("Give Arms?", TextColor.color(16777045)));
                option_item.setItemMeta(option_meta);
                break;
            case BEACON:
                option_meta.displayName(Component.text("Add Glow?"));
                option_item.setItemMeta(option_meta);

                break;
            case STONE_SLAB:
                option_meta.displayName(Component.text("Give Base?"));
                option_item.setItemMeta(option_meta);
                break;
            default:
                break;
        }

        ItemStack yes = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.displayName(Component.text("Yes", TextColor.color(5635925)));
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.RED_WOOL);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.displayName(Component.text("No", TextColor.color(16733525)));
        no.setItemMeta(no_meta);

        confirm_menu.setItem(13, option_item);
        confirm_menu.setItem(11, no);
        confirm_menu.setItem(15, yes);

        p.openInventory(confirm_menu);
    }

    public static void openArmorMenu(Player p) {
        Inventory armor_menu = Bukkit.createInventory(p, 45,
                Component.text("Choose some Armor", TextColor.color(5592575)));

        ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack body = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

        ItemStack yes = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.displayName(Component.text("Done", TextColor.color(5635925)));
        yes.setItemMeta(yes_meta);

        armor_menu.setItem(11, head);
        armor_menu.setItem(12, body);
        armor_menu.setItem(14, legs);
        armor_menu.setItem(15, boots);
        armor_menu.setItem(40, yes);
        p.openInventory(armor_menu);
    }
}
