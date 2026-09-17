package rainy.soul.Abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Blink extends Item {
    public Blink(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            Vec3d start = user.getPos();
            Vec3d direction = user.getRotationVector().normalize();
            Vec3d destination = start.add(direction.multiply(8));

            if (world instanceof net.minecraft.server.world.ServerWorld serverWorld) {
                for (double i = 0; i <= 1.0; i += 0.1) {
                    Vec3d p = start.lerp(destination, i);
                    serverWorld.spawnParticles(ParticleTypes.SOUL, p.x, p.y + 1.0, p.z,
                            2, 0.1, 0.1, 0.1, 0.01);
                }
            }

            user.requestTeleport(destination.x, destination.y, destination.z);
            user.setVelocity(Vec3d.ZERO);
            user.fallDistance = 0;

            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                    SoundCategory.PLAYERS, 1.0f, 1.3f);
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}