package maowcraft.fingerprinting.event;

import maowcraft.fingerprinting.util.PersistentDataKeys;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;

public class EntityEvents implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player killer = event.getEntity().getKiller();
            assert killer != null;
            PlayerInventory killerInventory = killer.getInventory();
            ItemStack weapon = killerInventory.getItem(killerInventory.getHeldItemSlot());
            assert weapon != null;
            ItemMeta meta = weapon.getItemMeta();
            assert meta != null;
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
            dataContainer.set(PersistentDataKeys.IS_FINGERPRINTED, PersistentDataType.INTEGER, 1);
            dataContainer.set(PersistentDataKeys.MURDERER, PersistentDataType.STRING, killer.getDisplayName());
            dataContainer.set(PersistentDataKeys.MURDERED, PersistentDataType.STRING, ((Player) event.getEntity()).getDisplayName());
            meta.setLore(Collections.singletonList(ChatColor.GRAY + "Murder Weapon"));
            weapon.setItemMeta(meta);
        }
    }
}
