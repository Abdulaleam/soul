package rainy.soul.entity;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.soul.Soul;
import rainy.soul.SoulBlock.SoulBlocks;
import rainy.soul.entity.custom.RefinerBlockEntity;

public class SoulBlockEntities {

    public static final BlockEntityType<RefinerBlockEntity> REFINER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Soul.MOD_ID, "refiner_be"),
                    BlockEntityType.Builder.create(RefinerBlockEntity::new, SoulBlocks.REFINER).build(null));

    public static void registerBlockEntities(){}
}

