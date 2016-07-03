# LuaBlocks
### A Bukkit plugin for scripting the Bukkit server with Lua
######http://dev.bukkit.org/bukkit-plugins/luablocks/

  This plugin lets you write small scripts in Lua which can be executed by the Bukkit Server. It exposes some of the Bukkit API through some abstractions. Currently, not a lot could be done using it except very basic things because it is still in Alpha and very little of API is exposed, and very little work was done by the original author which is why this text was originally in a fork, since the original readme was not complete and was totally useless.

  Here is a sample to get you started:

Filesystem Tree (from the Bukkit directory):
```
Server
│
├──plugins
│  │
│  ├──LuaBlocks.jar
│  │
│  ┗──LuaBlocks
│      │  
│      ┗──plugins
│        │
│        ┗──sampleplugin.lua
│
┗──Bukkit.jar
```

sampleplugin.lua
```lua

name = "sampleplugin" -- Must be a global expressing the plugin name

sampleplugin = {} -- Needs to have the same name

require 'io.github.zacklukem.bukkit.bukkit' -- Get the APIs

require 'io.github.zacklukem.bukkit.command'


function sampleplugin.onEnable () -- On enabling of our plugin

	command.registerCommand("sampleplugincmd", "repeat") -- register command

end


function sampleplugin.onDisable () -- When our plugin is disabled

	bukkit.logger.info("Sample Plugin Disabled :(")

end


function sampleplugin.onCommand (sender, label, args)

        if label == "sampleplugincmd" then

              sender:sendMessage("Block"..args[1].." placed at (x="..args[2]..", y="..args[3]..", z="..args[4]..")")

              bukkit.block.placeBlock(args[2]*1,args[3]*1,args[4]*1,args[1]) --multiply to make args converted to numbers

              bukkit.logger.info("Block"..args[1].." placed at (x="..args[2]..", y="..args[3]..", z="..args[4]..")")

       	      return true

        else
              return false

        end

end

```
