package dev.finny.armorstandgui.events;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev.finny.armorstandgui.ArmorStandGui;
import dev.finny.armorstandgui.utils.MenuUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MenuHandler implements Listener {
    private ArmorStandGui plugin;

    final Component MAIN_MENU_TITLE = Component.text("Armor Stand Gui", TextColor.color(5592575));
    final Component CREATE_MENU_TITLE = Component.text("Create a Armor Stand", TextColor.color(5635925));
    final Component CONFIRM_MENU_TITLE = Component.text("Confirm Option", TextColor.color(5635925));

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
        }
    }
}
