package dev.camel.command.commands

import com.mojang.brigadier.CommandDispatcher
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object CamelRootCommand : CamelCommand {

    override val name = "camel"

    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            literal(name).executes { context -> 
                ChatUtils.sendSuccess("Camel!")
                1
            }
        )
    }
}