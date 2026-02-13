package dev.camel.command

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

interface CamelCommand {
    val name: String
    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>)
}
