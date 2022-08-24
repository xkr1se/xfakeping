package xkr1se.xfakeping;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class FakePingPlugin extends JavaPlugin {

    private static FakePingPlugin instance;
    private Random random;
    private String version;

    private int timeUpdate;
    private int maxPing;

    @Override
    public void onEnable() {
        instance = this;

        random = new Random();
        version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        timeUpdate = getConfig().getInt("time-update") * 20;
        maxPing = getConfig().getInt("max-ping");

        saveDefaultConfig();

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.getOnlinePlayers()
                    .forEach(player -> {
                        if(ReflectionUtils.getPing(player) > maxPing) {
                            ReflectionUtils.setPing(player, random.nextInt(maxPing));
                        }
                    });
        }, timeUpdate, timeUpdate);
    }

    public String getVersion() {
        return version;
    }

    public static FakePingPlugin getInstance() {
        return instance;
    }
}
