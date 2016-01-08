package io.github.zacklukem.luablocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

/**
 * Created by zmayhew on 1/7/16.
 */
public class CustomCommand extends Command {

//    private CommandExecutor exe = new CustomCommandExecutor();
    private LuaValue pluginNamespace;

    public CustomCommand(String name, LuaValue pluginNamespace) {
        super(name);
        this.pluginNamespace = pluginNamespace;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel,String[] args) {
//        if(exe != null){
//            exe.onCommand(sender, this, commandLabel,args);
//        }
        LuaValue[] args0 = new LuaValue[args.length];
        for (int i = 0; i < args.length; i++) {
            args0[i] = LuaValue.valueOf(args[i]);
        }
        boolean r = pluginNamespace.get("onCommand").call(CoerceJavaToLua.coerce(new LuaCommandSender(sender)), LuaValue.valueOf(commandLabel), LuaValue.listOf(args0)).checkboolean();
        return r;
    }
    private static class LuaCommandSender {
        private CommandSender sender;

        private LuaCommandSender(CommandSender sender) {
            this.sender = sender;
        }

        public void sendMessage(String s) {

            sender.sendMessage(s);
        }

        public boolean isOp() {
            return sender.isOp();
        }

        public boolean isPermissionSet(String s ) {
            return sender.isPermissionSet(s);
        }

        public boolean hasPermission(String s) {
            return sender.hasPermission(s);
        }

        public String getName() {
            return sender.getName();
        }

        public String getType() {
            if (sender instanceof Player) {
                return "player";
            }
            return "other";
        }
    }
}
