package rainy.soul.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import rainy.soul.Soul;
import rainy.soul.SoulBlock.SoulBlocks;

public class SoulGroup {

    public static final ItemGroup SOUL_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Soul.MOD_ID, "soul_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(SoulItems.SCYTHE))
                    .displayName(Text.translatable("itemgroup.soul.soul_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(SoulItems.SCYTHE);
                        entries.add(SoulItems.REFINED_SOUL);
                        entries.add(SoulItems.CORRUPTED_SOUL);
                        entries.add(SoulItems.SOUL_LANTERN);
                        entries.add(SoulBlocks.PURIFIER);
                        entries.add(SoulBlocks.REFINER);
                    }).build());

    public static void registerSoulGroups() {
    }
}