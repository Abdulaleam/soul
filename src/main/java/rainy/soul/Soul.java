package rainy.soul;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rainy.soul.Abilities.HarvesterBlock;
import rainy.soul.Abilities.ScytheAbility;
import rainy.soul.Abilities.TheGuide;
import rainy.soul.SoulBlock.SoulBlocks;
import rainy.soul.item.SoulGroup;
import rainy.soul.item.SoulItems;

public class Soul implements ModInitializer {
	public static final String MOD_ID = "soul";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		SoulItems.SoulItems();
		ScytheAbility.registerReaper();
		TheGuide.registerAttract();
		SoulGroup.registerSoulGroups();
		SoulBlocks.registerSoulBlocks();
		HarvesterBlock.Tracker.registerTick();


	}

}
