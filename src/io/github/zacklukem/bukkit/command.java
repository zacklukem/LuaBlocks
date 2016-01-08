package io.github.zacklukem.bukkit;

import io.github.zacklukem.luablocks.CustomCommand;
import io.github.zacklukem.luablocks.LuaPlugin;
import io.github.zacklukem.luablocks.PluginDelegate;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * Created by zmayhew on 1/7/16.
 */
public class command extends TwoArgFunction {

    public command() {}

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaValue library = tableOf();

        library.set("registerCommand", new registerCommand());

        env.set( "command", library );
        return library;
    }

    static class registerCommand extends TwoArgFunction {

        @Override
        public LuaValue call(LuaValue luaPluginName, LuaValue luaCommandName) {
            String pluginName = luaPluginName.checkjstring();
            String commandName = luaCommandName.checkjstring();
            for (LuaPlugin p : PluginDelegate.pluginFiles) {
                System.out.println(pluginName + ", " + p.getName());
                if (p.getName().equals(pluginName)) {
                    System.out.println("Adding command");
                    PluginDelegate.cmap.register("", new CustomCommand(commandName, p.getPluginNamespace()));
                }
            }

            return null;
        }
    }
}
