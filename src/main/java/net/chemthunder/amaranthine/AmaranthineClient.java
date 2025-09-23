package net.chemthunder.amaranthine;

import net.chemthunder.amaranthine.init.ModBlocks;
import net.fabricmc.api.ClientModInitializer;

public class AmaranthineClient implements ClientModInitializer {
    public void onInitializeClient() {
        ModBlocks.clientInit();
    }
}
