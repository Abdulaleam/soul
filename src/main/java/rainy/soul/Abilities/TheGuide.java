package rainy.soul.Abilities;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class TheGuide extends Item {

    private static final double GUIDE_RADIUS = 40;
    public TheGuide(Settings settings) {
        super(settings);
    }

    public static void registerAttract() {
        ServerTickEvents.END_WORLD_TICK.register(TheGuide::attractSouls);
    }

    private static void attractSouls(ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            if (!isHoldingLantern(player))
                continue;

            for (UUID id : LostSoulState.getAllSouls()) {
                Entity entity = world.getEntity(id);
                if (!(entity instanceof LivingEntity soul))
                    continue;
                if (soul.getWorld() != world)
                    continue;

                double distance = soul.getPos().distanceTo(player.getPos());
                if (distance > GUIDE_RADIUS || distance < 1)
                    continue;

                Vec3d direction = player.getPos().subtract(soul.getPos()).normalize();
                Vec3d newPos = soul.getPos().add(direction.multiply(0.2));

                soul.setPosition(newPos.x, newPos.y, newPos.z);
            }
        }
    }
    private static boolean isHoldingLantern(ServerPlayerEntity player) {
        ItemStack mainHand = player.getMainHandStack();
        ItemStack offHand = player.getOffHandStack();

        return mainHand.getItem() instanceof TheGuide || offHand.getItem() instanceof TheGuide;
    }

}
