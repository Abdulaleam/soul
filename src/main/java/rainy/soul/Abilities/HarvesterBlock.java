package rainy.soul.Abilities;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rainy.soul.item.SoulItems;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HarvesterBlock extends Block {

    public HarvesterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            Tracker.register(pos);
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            Tracker.unregister(pos);
        }
        return super.onBreak(world, pos, state, player);
    }
    public static class Tracker {

        private static final Set<BlockPos> ACTIVE_HARVESTERS =
                Collections.synchronizedSet(new HashSet<>());

        private static final double COLUMN_RADIUS = 0.6;

        public static void register(BlockPos pos) {
            ACTIVE_HARVESTERS.add(pos.toImmutable());
        }

        public static void unregister(BlockPos pos) {
            ACTIVE_HARVESTERS.remove(pos);
        }

        public static void registerTick() {
            ServerTickEvents.END_WORLD_TICK.register(Tracker::tick);
        }

        private static void tick(ServerWorld world) {
            if (ACTIVE_HARVESTERS.isEmpty()) return;

            for (UUID id : Set.copyOf(LostSoulState.getAllSouls())) {
                Entity entity = world.getEntity(id);
                if (!(entity instanceof LivingEntity soul)) continue;
                if (soul.getWorld() != world) continue;

                double soulX = soul.getX();
                double soulY = soul.getY();
                double soulZ = soul.getZ();

                for (BlockPos harvesterPos : ACTIVE_HARVESTERS) {
                    double dx = soulX - (harvesterPos.getX() + 0.5);
                    double dz = soulZ - (harvesterPos.getZ() + 0.5);
                    double horizontalDistSq = dx * dx + dz * dz;

                    boolean aligned = horizontalDistSq <= COLUMN_RADIUS * COLUMN_RADIUS;
                    boolean aboveBlock = soulY >= harvesterPos.getY();

                    if (aligned && aboveBlock) {
                        LostSoulState.unmark(soul);
                        soul.dropStack(new ItemStack(SoulItems.CORRUPTED_SOUL));
                        soul.discard();
                        break;
                    }
                }
            }
        }
    }
}