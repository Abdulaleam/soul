package rainy.soul.Abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SoulSword extends SwordItem {
    private static final int RADIUS = 4;
    private static final int DAMAGE = 8;
    private static final float HEAL = 4.0f;


    public SoulSword(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            world.getEntitiesByClass(LivingEntity.class, user.getBoundingBox().expand(RADIUS),
                    e -> e != user && e.isAlive()).forEach(e -> {
                e.damage(world.getDamageSources().playerAttack(user), DAMAGE);
                e.addVelocity(
                        (e.getX() - user.getX()) * 0.4, 0.3, (e.getZ() - user.getZ()) * 0.4);

            });
            user.heal(HEAL);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WITHER_HURT,
                    SoundCategory.PLAYERS, 1.0f, 0.7f);
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}