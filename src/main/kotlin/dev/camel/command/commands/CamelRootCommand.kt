package dev.camel.command.commands

import com.mojang.brigadier.CommandDispatcher
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelCommand
import dev.camel.command.CamelSubCommand
import dev.camel.command.commands.InfoCommand
import dev.camel.command.commands.DebugCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource


object CamelRootCommand : CamelCommand {

    override val name = "camel"

    private val subCommands = listOf(
        InfoCommand,     
        DebugCommand,   
    )

    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        val root = literal(name)
            .executes {
                ChatUtils.sendSuccess("Camel!")
                1
            }

        subCommands.forEach {
            root.then(it.build())
        }

        dispatcher.register(root)
    }
}