package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModSounds {
    Map<SoundEvent, Identifier> SOUNDS = new LinkedHashMap<>();

    SoundEvent INSANITY_VOICES = create("ambient.insanity_voices");
    SoundEvent CHEM_SQUISH = create("block.chem_squish");
    SoundEvent WEATHERGIRL = create("music.weathergirl");

    private static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(Amaranthine.id(name));
        SOUNDS.put(soundEvent, Amaranthine.id(name));
        return soundEvent;
    }

    static void init() {
        SOUNDS.keySet().forEach(soundEvent -> {
            Registry.register(Registries.SOUND_EVENT, SOUNDS.get(soundEvent), soundEvent);
        });
    }
}
