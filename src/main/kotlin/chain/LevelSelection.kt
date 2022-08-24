package chain

import core.Updating
import executables.Executable
import handlers.OnCallbackGotten
import math_bot.LevelSelectingMessage
import updating.UpdatingCallbackData

class LevelSelection : Chain(
    OnCallbackGotten()
) {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            LevelSelectingMessage.Base(
                mKey,
                updating.map(UpdatingCallbackData())
            ).levelMessage()
        } catch (e: Exception) {
            emptyList()
        }
    }
}