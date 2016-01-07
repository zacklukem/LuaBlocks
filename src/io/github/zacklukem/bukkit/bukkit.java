package io.github.zacklukem.bukkit;

import org.bukkit.Bukkit;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * Created by zmayhew on 1/6/16.
 */
public class bukkit extends TwoArgFunction {
    public bukkit() {}

    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaValue library = tableOf();
        LuaValue logger = tableOf();

        logger: {
            logger.set("info", new loggerInfo());
            logger.set("warning", new loggerWarning());
            logger.set("loggersevere", new loggerSevere());
            library.set("logger", logger);
        }



        env.set( "bukkit", library );
        return library;

    }

    static class loggerInfo extends OneArgFunction {
        public LuaValue call(LuaValue value) {
            Bukkit.getLogger().info(value.checkjstring());
            return null;
        }
    }

    static class loggerWarning extends OneArgFunction {
        public LuaValue call(LuaValue value) {
            Bukkit.getLogger().warning(value.checkjstring());
            return null;
        }
    }

    static class loggerSevere extends OneArgFunction {
        public LuaValue call(LuaValue value) {
            Bukkit.getLogger().severe(value.checkjstring());
            return null;
        }
    }

}
