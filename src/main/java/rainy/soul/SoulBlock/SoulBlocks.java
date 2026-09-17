package rainy.soul.SoulBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.soul.Abilities.HarvesterBlock;
import rainy.soul.Soul;

public class SoulBlocks {

    public static final Block PURIFIER = registerBlock("purifier",
            new HarvesterBlock(AbstractBlock.Settings.create()));

    public static final Block REFINER = registerBlock("refiner",
            new RefinerBlock(AbstractBlock.Settings.create()));

    private static Block registerBlock(String name , Block block) {
        RegisterBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Soul.MOD_ID, name), block);
    }

    private static void RegisterBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Soul.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerSoulBlocks(){
        Soul.LOGGER.info("Registering Soul Blocks for " + Soul.MOD_ID);
    }
}
