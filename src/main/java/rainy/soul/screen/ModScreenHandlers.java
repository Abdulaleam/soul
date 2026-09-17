package rainy.soul.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import rainy.soul.Soul;

public class ModScreenHandlers {

    public static final ScreenHandlerType<RefinerScreenHandler> REFINER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Soul.MOD_ID, "refiner_screen_handler"),
                    new ExtendedScreenHandlerType<>(RefinerScreenHandler:: new, BlockPos.PACKET_CODEC));



    public static void registerScreenHandlers() {
    }
}
