package dev.camel

import dev.camel.command.CommandHandler
import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory

object Camel : ClientModInitializer {

    const val MOD_ID = "camel"
    val logger = LoggerFactory.getLogger(MOD_ID)!!

    override fun onInitializeClient() {
        logger.info("Camel is loading...")

        CommandHandler.register()

        logger.info("Camel loaded!")
    }
}
