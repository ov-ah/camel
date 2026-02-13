package dev.camel.chat

import net.minecraft.client.MinecraftClient
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object ChatUtils {

    private val PREFIX: MutableText
        get() = Text.empty()
            .append(
                Text.literal("\u2B50 ")
                    .formatted(Formatting.GOLD)
            )
            .append(
                Text.literal("Camel")
                    .formatted(Formatting.YELLOW, Formatting.BOLD)
            )
            .append(
                Text.literal(" \u00BB ")
                    .formatted(Formatting.DARK_GRAY)
            )

    fun send(message: String) {
        send(Text.literal(message))
    }

    fun send(message: Text) {
        val full = Text.empty()
            .append(PREFIX)
            .append(message)

        MinecraftClient.getInstance().player?.sendMessage(full, false)
    }

    fun sendSuccess(message: String) {
        send(Text.literal(message).formatted(Formatting.GREEN))
    }

    fun sendError(message: String) {
        send(Text.literal(message).formatted(Formatting.RED))
    }

    fun sendInfo(message: String) {
        send(Text.literal(message).formatted(Formatting.GRAY))
    }
}
