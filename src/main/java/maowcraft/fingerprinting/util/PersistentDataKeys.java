package maowcraft.fingerprinting.util;

import maowcraft.fingerprinting.Fingerprinting;
import org.bukkit.NamespacedKey;

public class PersistentDataKeys {
    public static final NamespacedKey IS_FINGERPRINTED = new NamespacedKey(Fingerprinting.getInstance(), "is_fingerprinted");
    public static final NamespacedKey MURDERER = new NamespacedKey(Fingerprinting.getInstance(), "murderer");
    public static final NamespacedKey MURDERED = new NamespacedKey(Fingerprinting.getInstance(), "murdered");
}
