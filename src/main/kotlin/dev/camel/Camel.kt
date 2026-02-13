package dev.camel

import dev.camel.command.CommandHandler
import dev.camel.gui.ImGuiManager
import dev.camel.gui.windows.DemoWindow
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.util.Identifier
import org.lwjgl.glfw.GLFW
import org.slf4j.LoggerFactory

object Camel : ClientModInitializer {

    const val MOD_ID = "camel"
    val logger = LoggerFactory.getLogger(MOD_ID)!!

    private val toggleGuiKey: KeyBinding = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.camel.toggle_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            KeyBinding.Category.create(Identifier.of(MOD_ID, "camel"))
        )
    )

    override fun onInitializeClient() {
        logger.info("Camel is loading...")

        CommandHandler.register()

        ImGuiManager.register(DemoWindow)

        ClientTickEvents.END_CLIENT_TICK.register { _ ->
            while (toggleGuiKey.wasPressed()) {
                ImGuiManager.visible = !ImGuiManager.visible
            }
        }

        logger.info("Camel loaded!")
    }
}
