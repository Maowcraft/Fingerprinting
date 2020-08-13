package maowcraft.fingerprinting.command;

import maowcraft.fingerprinting.Fingerprinting;
import maowcraft.fingerprinting.util.Permissions;
import maowcraft.fingerprinting.util.PersistentDataKeys;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class CommandInspect implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.INSPECT_USE)) {
                PlayerInventory inventory = player.getInventory();
                ItemStack weapon = inventory.getItem(inventory.getHeldItemSlot());
                if (weapon != null) {
                    NamespacedKey key = new NamespacedKey(Fingerprinting.getInstance(), "is_fingerprinted");
                    ItemMeta meta = weapon.getItemMeta();
                    assert meta != null;
                    PersistentDataContainer container = meta.getPersistentDataContainer();

                    if (container.has(key, PersistentDataType.INTEGER)) {
                        int isFingerprinted = Objects.requireNonNull(container.get(PersistentDataKeys.IS_FINGERPRINTED, PersistentDataType.INTEGER));
                        if (isFingerprinted == 1) {
                            String killer = Objects.requireNonNull(container.get(PersistentDataKeys.MURDERER, PersistentDataType.STRING));
                            String killed = Objects.requireNonNull(container.get(PersistentDataKeys.MURDERED, PersistentDataType.STRING));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[Fingerprinting]&r " + killed + " was murdered by " + killer));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[Fingerprinting]&r This item is not a murder weapon."));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[Fingerprinting]&r You are not holding an item."));
                }

                return true;
            }
        }
        return false;
    }
}
