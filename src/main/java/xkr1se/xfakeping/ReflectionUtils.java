package xkr1se.xfakeping;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;

/**
 * @author xkr1se
 */
public class ReflectionUtils {

    /**
     * Через рефлексию достает пинг игрока
     * @param player - Игрок у которого получить пинг
     * @return возвращает пинг игрока
     */
    public static int getPing(Player player) {
        try {
            Class<?> clazz = Class.forName(String.format("org.bukkit.craftbukkit.%s.entity.CraftEntity", FakePingPlugin.getInstance().getVersion()));
            Method method = clazz.getMethod("getHandle");
            Object o = method.invoke(player);
            return o.getClass().getField("ping").getInt(o);
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка при получении пинга!");
        }
    }

    /**
     * Через рефлексию меняет пинг игрока
     * @param player - Игрок которому установить пинг
     * @param ping - Пинг на который установить
     */
    public static void setPing(Player player, int ping) {
        try {
            Class<?> clazz = Class.forName(String.format("org.bukkit.craftbukkit.%s.entity.CraftEntity", FakePingPlugin.getInstance().getVersion()));
            Method method = clazz.getMethod("getHandle");
            Object o = method.invoke(player);
            o.getClass().getField("ping").set(o, ping);
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка при установке пинга!");
        }
    }
}
