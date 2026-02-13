# Camel

A Hypixel Skyblock utility mod for Minecraft 1.21.10 (Fabric), written in Kotlin.

## Requirements

- Java 21+
- Minecraft 1.21.10
- Fabric Loader 0.17.2+
- Fabric API

## Building

```bash
./gradlew build
```

The compiled jar will be in `build/libs/`. Use the one **without** `-sources` in the name.

## Development Setup

1. Clone the repo
2. Open the project in IntelliJ IDEA (recommended) or your preferred IDE
3. Run `./gradlew genSources` to generate Minecraft sources for IDE navigation
4. Import as a Gradle project

### Useful Commands

| Command | Description |
|---|---|
| `./gradlew build` | Compile and package the mod |
| `./gradlew genSources` | Decompile Minecraft sources for IDE reference |
| `./gradlew runClient` | Launch Minecraft with the mod loaded |
| `./gradlew clean` | Delete all build artifacts |
| `./gradlew remapJar` | Remap the jar for production use |

## Project Structure

```
src/main/kotlin/dev/camel/
├── Camel.kt                      # Mod entrypoint (ClientModInitializer)
├── chat/
│   └── ChatUtils.kt              # Chat messaging utilities (prefix, colors)
└── command/
    ├── CamelCommand.kt           # Command interface
    ├── CommandHandler.kt          # Registers all commands via Fabric API
    └── commands/
        └── PingCommand.kt        # /ping → "Pong!"
```

## In-Game Commands

| Command | Description |
|---|---|
| `/ping` | Responds with "Pong!" — useful for checking the mod is loaded |

## License

MIT
