package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModJukeboxSongs {
    RegistryKey<JukeboxSong> SUGAR_WATER_CYANIDE = of("sugar_water_cyanide");


    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Amaranthine.id(id));
    }
}
