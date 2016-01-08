package io.github.zacklukem.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.*;

/**
 * Created by zmayhew on 1/6/16.
 */
public class bukkit extends TwoArgFunction {

    public static final Varargs EMPTY = new Varargs() {
        @Override
        public LuaValue arg(int i) {
            return null;
        }

        @Override
        public int narg() {
            return 0;
        }

        @Override
        public LuaValue arg1() {
            return null;
        }

        @Override
        public Varargs subargs(int i) {
            return null;
        }
    };

    public bukkit() {}

    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaValue library = tableOf();

        logger: {
            LuaValue logger = tableOf();
            logger.set("info", new loggerInfo());
            logger.set("warning", new loggerWarning());
            logger.set("loggersevere", new loggerSevere());
            library.set("logger", logger);
        }

        block: {
            LuaValue block = tableOf();
            block.set("placeBlock", new blockPlaceBlock());
            library.set("block", block);
        }

        env.set( "bukkit", library );
        return library;

    }

    static class blockPlaceBlock extends LibFunction {
        @Override
        public Varargs invoke(Varargs varargs) {
            int x = varargs.arg1().checkint();
            int y = varargs.arg(2).checkint();
            int z = varargs.arg(3).checkint();
            String block = varargs.arg(4).checkjstring();
            Bukkit.getServer().getWorld("world").getBlockAt(x,y,z).setType(Material.getMaterial(block));
            return EMPTY;
        }
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
