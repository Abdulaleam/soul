package rainy.soul;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import rainy.soul.screen.ModScreenHandlers;
import rainy.soul.screen.RefinerScreen;

public class SoulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.REFINER_SCREEN_HANDLER, RefinerScreen::new);

    }
}
