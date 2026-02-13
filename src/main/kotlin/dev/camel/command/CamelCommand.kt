package dev.camel.command

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

interface CamelCommand {
    val name: String
    val description: String get() = ""
    val subCommands: List<CamelSubCommand> get() = emptyList()
    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>)
}
