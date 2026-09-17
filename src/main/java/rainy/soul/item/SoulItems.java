package rainy.soul.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.soul.Abilities.Blink;
import rainy.soul.Abilities.ScytheAbility;
import rainy.soul.Abilities.SoulSword;
import rainy.soul.Abilities.TheGuide;
import rainy.soul.Soul;
import rainy.soul.SoulBlock.SoulBlocks;

public class SoulItems {

    public static final Item SCYTHE = registerItem("scythe", new ScytheAbility(new Item.Settings().maxCount(1).attributeModifiers(
            AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                            Identifier.of("soul", "scythe_attack_damage"), 199.0,
                            EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND
            ).build()
    )));
    public static final Item CORRUPTED_SOUL = registerItem("corrupted_soul", new Item(new Item.Settings()));
    public static final Item REFINED_SOUL = registerItem("refined_soul", new Item(new Item.Settings()));

    public static final Item SOUL_LANTERN = registerItem("soul_lantern", new TheGuide(new Item.Settings().maxCount(1)));

    public static final Item SOUL_SWORD = registerItem("soul_sword", new SoulSword(ToolMaterials.NETHERITE, new Item.Settings().maxCount(1)));

    public static final Item SOUL_BLINKER = registerItem("soul_blink", new Blink(new Item.Settings().maxCount(1)));


    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, Identifier.of(Soul.MOD_ID, name), item);
    }





    public static void SoulItems() {
        Soul.LOGGER.info("Registering SoulItemsSoulItems for " + Soul.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(SoulItems.SCYTHE);
            entries.add(SoulItems.CORRUPTED_SOUL);
            entries.add(SoulItems.REFINED_SOUL);
            entries.add(SoulItems.SOUL_LANTERN);
            entries.add(SoulBlocks.PURIFIER);
        });
    }
}
