package eu.wolfsucht.projects.lobbysystemws.util;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TeleportUtils {
    private static File file;
    public static YamlConfiguration cfg;

    public TeleportUtils(LobbySystemWS main) {
        file = new File(main.getDataFolder(), "locations.yml");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            cfg = YamlConfiguration.loadConfiguration(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void save(String name, Material material, int slot, Location location){
        String toSave = name + ":" + material.toString() + ":" + slot + location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
        List<String> list;
        try {
            list = cfg.getStringList("teleporter");
        }catch (Exception e){
            list = new ArrayList<>();
        }
        list.add(toSave);
        cfg.set("teleporter", list);
        try {
            cfg.save(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
