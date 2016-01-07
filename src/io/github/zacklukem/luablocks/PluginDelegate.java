package io.github.zacklukem.luablocks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.luaj.vm2.Globals;
import org.luaj.vm2.Lua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by zmayhew on 1/6/16.
 */
public class PluginDelegate extends JavaPlugin {

    private File pluginsDir;

    public static final Globals GLOBALS = JsePlatform.standardGlobals();

    private ArrayList<LuaPlugin> pluginFiles = new ArrayList<>();

    @Override
    public void onEnable() {
        // Organize directorys
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        pluginsDir = new File(this.getDataFolder().getPath() + "/plugins/");
        if (!pluginsDir.exists()) {
            pluginsDir.mkdir();
        }
        for (File f : pluginsDir.listFiles()) {
            if (f.getName().endsWith(".lua")) {
                LuaPlugin plugin = new LuaPlugin(f);
                plugin.onEnable();
                pluginFiles.add(plugin);
            }
        }
    }

    @Override
    public void onDisable() {
        for (LuaPlugin plugin : pluginFiles) {
            plugin.onDisable();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("luaplugins")) { // If the player typed /basic then do the following...
            StringBuilder message = new StringBuilder();
            message.append("Lua Plugins (").append(pluginFiles.size()).append("): ").append(ChatColor.GREEN);
            for (LuaPlugin p : pluginFiles) {
                message.append(p.getName()).append(", ");
            }

            sender.sendMessage(message.toString());
            return true;
        }
        return false;
    }
}
