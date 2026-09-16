package rainy.soul.Abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LostSoulState {

    private static final Set <UUID> LOSTSOULS = Collections.synchronizedSet(new HashSet<UUID>());

    public static void markAsLostSoul(LivingEntity entity) {
        LOSTSOULS.add(entity.getUuid());
    }
    public static boolean isLostSoul(Entity entity) {
        return LOSTSOULS.contains(entity.getUuid());
    }
    public static void unmark(Entity entity) {
        LOSTSOULS.remove(entity.getUuid());
    }
    public static Set<UUID> getAllSouls() {
        return LOSTSOULS;
    }
}
