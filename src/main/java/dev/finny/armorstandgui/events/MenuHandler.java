package dev.finny.armorstandgui.events;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import dev.finny.armorstandgui.ArmorStandGui;
import dev.finny.armorstandgui.utils.MenuUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MenuHandler implements Listener {
    private ArmorStandGui plugin;

    final Component MAIN_MENU_TITLE = Component.text("Armor Stand Gui", TextColor.color(5592575));
    final Component CREATE_MENU_TITLE = Component.text("Create a Armor Stand", TextColor.color(5635925));
    final Component CONFIRM_MENU_TITLE = Component.text("Confirm Option", TextColor.color(5635925));
    final Component ARMOR_MENU_TITLE = Component.text("Choose some Armor", TextColor.color(5592575));

    public MenuHandler(ArmorStandGui plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        final Component TITLE = e.getView().title();

        if (TITLE.equals(MAIN_MENU_TITLE)) {

            switch (e.getCurrentItem().getType()) {
                case ARMOR_STAND:
                    p.sendMessage("Opened Armor Stand Create Menu");
                    MenuUtils.openCreateMenu(p);
                    break;
                case BARRIER:
                    p.sendMessage("Closing main menu");
                    p.closeInventory();
                    break;
                default:
                    break;
            }
            e.setCancelled(true);

        } else if (TITLE.equals(CREATE_MENU_TITLE)) {

            if (!plugin.armorStands.containsKey(p)) {
                ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                stand.setVisible(false);
                plugin.armorStands.put(p, stand);
            }

            p.sendMessage("Create Menu");
            switch (e.getCurrentItem().getType()) {
                case ARMOR_STAND:
                    p.sendMessage("Add Arms?");
                    MenuUtils.openConfirmMenu(p, Material.ARMOR_STAND);
                    break;
                case BEACON:
                    p.sendMessage("Give Glow?");
                    MenuUtils.openConfirmMenu(p, Material.BEACON);
                    break;
                case STONE_SLAB:
                    p.sendMessage("Add Base?");
                    MenuUtils.openConfirmMenu(p, Material.STONE_SLAB);
                    break;
                case LEATHER_CHESTPLATE:
                    MenuUtils.openArmorMenu(p);
                    break;
                case GREEN_WOOL:
                    p.sendMessage("Create Armor Stand");
                    if (!plugin.armorStands.containsKey(p))
                        break;
                    plugin.armorStands.get(p).setVisible(true);
                    plugin.armorStands.remove(p);
                    p.closeInventory();
                    break;
                case RED_WOOL:
                    p.sendMessage("Cancel");
                    p.closeInventory();
                    if (!plugin.armorStands.containsKey(p))
                        break;
                    plugin.armorStands.get(p).remove();
                    plugin.armorStands.remove(p);
                    break;

                default:
                    p.sendMessage("not found block");
                    break;
            }
            e.setCancelled(true);
        } else if (TITLE.equals(CONFIRM_MENU_TITLE)) {
            if (e.getClickedInventory().contains(Material.ARMOR_STAND)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        p.sendMessage("Option Confirmed");
                        if (plugin.armorStands.containsKey(p)) {
                            plugin.armorStands.get(p).setArms(true);
                        }
                        MenuUtils.openCreateMenu(p);
                        break;
                    case RED_WOOL:
                        p.sendMessage("Cancelled Option");
                        if (plugin.armorStands.containsKey(p)) {
                            plugin.armorStands.get(p).setArms(false);
                        }
                        MenuUtils.openCreateMenu(p);
                        break;
                    default:
                        break;
                }
            } else if (e.getClickedInventory().contains(Material.BEACON)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        p.sendMessage("Option Confirmed");
                        if (plugin.armorStands.containsKey(p)) {
                            plugin.armorStands.get(p).setGlowing(true);
                        }
                        MenuUtils.openCreateMenu(p);
                        break;
                    case RED_WOOL:
                        p.sendMessage("Cancelled Option");
                        if (plugin.armorStands.containsKey(p)) {
                            plugin.armorStands.get(p).setGlowing(false);
                        }
                        MenuUtils.openCreateMenu(p);
                        break;
                    default:
                        break;
                }
            } else if (e.getClickedInventory().contains(Material.STONE_SLAB)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        p.sendMessage("Option Confirmed");
                        if (plugin.armorStands.containsKey(p)) {
                            plugin.armorStands.get(p).setBasePlate(true);
                        }
                        MenuUtils.openCreateMenu(p);
                        break;
                    case RED_WOOL:
                        p.sendMessage("Cancelled Option");
                        if (plugin.armorStands.containsKey(p)) {
                            plugin.armorStands.get(p).setBasePlate(false);
                        }
                        MenuUtils.openCreateMenu(p);
                        break;
                    default:
                        break;
                }
            }

            e.setCancelled(true);
        } else if (TITLE.equals(ARMOR_MENU_TITLE)) {
            if (!plugin.armorStands.containsKey(p))
                return;
            ArmorStand stand = plugin.armorStands.get(p);

            // TODO: Add other than diamond
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_HELMET:
                    if (stand.getEquipment().getHelmet().getType() == Material.DIAMOND_HELMET) {
                        stand.getEquipment().setHelmet(null);
                        break;
                    }
                    stand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                    break;
                case DIAMOND_CHESTPLATE:
                    if (stand.getEquipment().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
                        stand.getEquipment().setChestplate(null);
                        break;
                    }
                    stand.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                    break;
                case DIAMOND_LEGGINGS:
                    if (stand.getEquipment().getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
                        stand.getEquipment().setLeggings(null);
                        break;
                    }
                    stand.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                    break;
                case DIAMOND_BOOTS:
                    if (stand.getEquipment().getBoots().getType() == Material.DIAMOND_BOOTS) {
                        stand.getEquipment().setBoots(null);
                        break;
                    }
                    stand.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                    break;
                case GREEN_WOOL:
                    MenuUtils.openCreateMenu(p);
                    break;
                default:
                    break;
            }
            e.setCancelled(true);
        }
    }
}
