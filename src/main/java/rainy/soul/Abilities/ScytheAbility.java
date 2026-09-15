package rainy.soul.Abilities;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import rainy.soul.item.SoulItems;

public class ScytheAbility extends Item {

    public ScytheAbility(Settings settings) {
        super(settings);
    }

    public static void registerReaper() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (!(damageSource.getAttacker() instanceof PlayerEntity player))
                return;

            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();


            boolean killedWithScythe =
                    mainHand.getItem() instanceof  ScytheAbility ||
                            offHand.getItem() instanceof ScytheAbility;
            if (!killedWithScythe)
                return;
            if (!(entity.getWorld() instanceof ServerWorld serverWorld))

                return;

            spawnLostSoul(serverWorld, entity);
        });
    }

    private static void spawnLostSoul(ServerWorld world, LivingEntity original) {
        EntityType<?> type = original.getType();
        Entity spawned =  type.spawn(world, original.getBlockPos(),SpawnReason.MOB_SUMMONED);
        if(!(spawned instanceof LivingEntity lostsoul))
            return;

        lostsoul.refreshPositionAndAngles(
                original.getX(), original.getY(), original.getZ(), original.getYaw(), original.getPitch()
        );
        if (lostsoul instanceof MobEntity mob) {
            mob.setAiDisabled(true);
        }
        lostsoul.setInvulnerable(true);
        lostsoul.setInvisible(true);
        lostsoul.setGlowing(true);
        lostsoul.setNoGravity(true);
        lostsoul.addStatusEffect(new StatusEffectInstance(
                StatusEffects.INVISIBILITY,StatusEffectInstance.INFINITE,20, false , false , false
        ));
        lostsoul.addStatusEffect(new StatusEffectInstance(
                StatusEffects.RESISTANCE,StatusEffectInstance.INFINITE,100, false , false , false
        ));


        LostSoulState.markAsLostSoul(lostsoul);
        world.spawnEntity(lostsoul);

    }
}
