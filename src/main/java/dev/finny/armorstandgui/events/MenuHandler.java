package dev.finny.armorstandgui.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev.finny.armorstandgui.utils.MenuUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MenuHandler implements Listener {
    final Component MAIN_MENU_TITLE = Component.text("Armor Stand Gui", TextColor.color(5592575));
    final Component CREATE_MENU_TITLE = Component.text("Create a Armor Stand", TextColor.color(5635925));
    final Component CONFIRM_MENU_TITLE = Component.text("Confirm Option", TextColor.color(5635925));

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
                    p.sendMessage("Create Armor");
                    break;
                case RED_WOOL:
                    p.sendMessage("Cancel");
                    p.closeInventory();
                    break;

                default:
                    p.sendMessage("not found block");
                    break;
            }
            e.setCancelled(true);
        }
    }
}
