package rainy.soul.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.soul.Abilities.ScytheAbility;
import rainy.soul.Soul;

public class SoulItems {

    public static final Item SCYTHE = registerItem("scythe", new ScytheAbility(new Item.Settings()));

    public static final Item CORRUPTED_SOUL = registerItem("corrupted_soul", new Item(new Item.Settings()));
    public static final Item REFINED_SOUL = registerItem("refined_soul", new Item(new Item.Settings()));



    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, Identifier.of(Soul.MOD_ID, name), item);
    }





    public static void SoulItems() {
        Soul.LOGGER.info("Registering SoulItemsSoulItems for " + Soul.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(SoulItems.SCYTHE);
            entries.add(SoulItems.CORRUPTED_SOUL);
            entries.add(SoulItems.REFINED_SOUL);
        });
    }
}
