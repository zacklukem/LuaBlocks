package io.github.zacklukem.luablocks;

import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import sun.plugin2.main.server.Plugin;

import java.io.File;

/**
 * Created by zmayhew on 1/6/16.
 */
public class LuaPlugin {

    private LuaValue pluginNamespace;

    public LuaPlugin(File file) {
        this.file = file;
        this.name = file.getName().replaceAll("\\.lua", "");
        PluginDelegate.GLOBALS.get("dofile").call( LuaValue.valueOf(file.getPath()));
        pluginNamespace = PluginDelegate.GLOBALS.get(name);
        //System.out.println(name);
    }

//    public File getFile() {
//        return file;
//    }

    private File file;

    public String getName() {
        return name;
    }

    private String name;

    public void onEnable() {

        pluginNamespace.get("onEnable").call();
//        LuaValue chunk = PluginDelegate.GLOBALS.loadfile(file.getPath());
//        chunk.call();
    }

    public void onDisable() {

        pluginNamespace.get("onDisable").call();
//        LuaValue chunk = PluginDelegate.GLOBALS.loadfile(file.getPath());
//        chunk.call();
    }
}
