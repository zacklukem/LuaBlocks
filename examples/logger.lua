require 'io.github.zacklukem.bukkit.bukkit'

commands = {}
local name = "commands"

function commands.onEnable ()
    bukkit.logger.info("info")
    bukkit.logger.warning("warning")
    bukkit.logger.severe("error")
end

function commands.onDisable ()
    bukkit.logger.info("Test One onDisable")
end

