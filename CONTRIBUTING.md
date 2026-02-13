# Contributing to Camel

## Getting Started

1. Fork and clone the repository
2. Ensure you have **Java 21+** installed
3. Run `./gradlew genSources` to decompile Minecraft sources
4. Open the project in IntelliJ IDEA (with the Kotlin and Minecraft Development plugins)

## Adding a New Command

1. Create a new file in `src/main/kotlin/dev/camel/command/commands/`
2. Implement the `CamelCommand` interface:

```kotlin
package dev.camel.command.commands

import com.mojang.brigadier.CommandDispatcher
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object ExampleCommand : CamelCommand {

    override val name = "example"
    override val description = "help command description"
    
    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
            val root = literal(name)
                .executes {
                    ChatUtils.send("Hello from example!")
                    1
                }
            dispatcher.register(root)
        )
    }
}
```

3. Register it in `CommandHandler.kt` by adding it to the `commands` list:

```kotlin
private val commands = listOf(
    PingCommand,
    ExampleCommand,  // add here
)
```

## Sub-commands
1. Create a new file in `src/main/kotlin/dev/camel/command/commands`
2. Implement the `CamelSubCommand` interface:

```kotlin
package dev.camel.command.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelSubCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object ExampleSubCommand : CamelSubCommand {

    override val description = "Help menu description"

    override fun build(): LiteralArgumentBuilder<FabricClientCommandSource> {
        val root = literal("sub-command name")
            .executes {
                ChatUtils.send("Hello from example sub-command!")
            }
        return root
    }
}
```

3. To attach your sub-command to a root command, add this line to the beggining of your root command (Note: This can be done on `CamelCommand` or `CamelSubCommand`):

```kotlin
private val _subCommands = listOf(
    ExampleSubCommand,
)

override val subCommands: List<CamelSubCommand> get() = _subCommands
//Creating a backup list is beneficial to ensure consistency across uses of subCommands.
```

4. In the `register()`/`build()` function of your root command, build the sub-commands by adding:
```kotlin
subCommands.forEach {
    root.then(it.build())
}
```

## Chat Output

**All mod chat output must use `ChatUtils`** to ensure the Camel prefix is displayed. Never call `player.sendMessage()` directly. Available methods:

- `ChatUtils.send(message)` — default white text
- `ChatUtils.sendSuccess(message)` — green text
- `ChatUtils.sendError(message)` — red text
- `ChatUtils.sendInfo(message)` — gray text
- `ChatUtils.send(Text)` — for custom formatted `Text` components

## Adding a New Feature Module

For larger features (e.g., a dungeon helper, auction tracker), create a new package under `dev.camel`:

```
src/main/kotlin/dev/camel/
├── feature/
│   └── yourfeature/
│       └── YourFeature.kt
```

Initialize it from `Camel.kt` in the `onInitializeClient()` method.

## Code Style

- **Kotlin** — all new code should be written in Kotlin, not Java
- Use `object` for singletons (commands, handlers, utilities)
- Keep files focused and small — one class/object per file
- Follow standard Kotlin naming conventions (camelCase for functions/properties, PascalCase for classes)

## Testing

```bash
./gradlew build        # Compile check
./gradlew runClient    # Test in-game
```

## Branching

- `main` — stable, working code
- Feature branches: `feature/your-feature-name`
- Bug fixes: `fix/description`
