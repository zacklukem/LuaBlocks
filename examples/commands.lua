require 'io.github.zacklukem.bukkit.bukkit'
require 'io.github.zacklukem.bukkit.command'

commands = {}
local name = "commands"

function commands.onEnable ()
	command.registerCommand(name, "repeat")
end

function commands.onDisable ()
	bukkit.logger.info("Test One onDisable")
end

function commands.onCommand (sender, label, args)
	if label == "sender" then
		sender:sendMessage(args[1])
		return true;
	end
	return false;
end