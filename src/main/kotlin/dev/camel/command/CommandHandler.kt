package dev.camel.command

import com.mojang.brigadier.CommandDispatcher
import dev.camel.command.commands.PingCommand
import dev.camel.command.commands.CamelRootCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object CommandHandler {

    private val commands = listOf(
        CamelRootCommand,
    )

    fun register() {
        ClientCommandRegistrationCallback.EVENT.register(
            ClientCommandRegistrationCallback { dispatcher, registryAccess ->
                registerAll(dispatcher)
            }
        )
    }

    private fun registerAll(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        commands.forEach { it.register(dispatcher) }
    }
}
