package chain

import core.Updating
import executables.Executable
import executables.SendMessage
import handlers.CommandEvent
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup

class StartGreeting : Chain(CommandEvent("/start")) {

    override suspend fun executableChain(updating: Updating): List<Executable> {
        return listOf(
            SendMessage(
                mKey,
                buildString {
                    appendLine("Приветствую тебя\\, дорогой игрок\\.")
                    appendLine("Хочу предложить тебе проверить твои математичесике знания\\.")
                    appendLine("Выбирите один из представленных уровней сложности\\:")
                },
                mMarkup = InlineKeyboardMarkup(
                    listOf(
                        listOf(
                            InlineButton(
                                "Первый уровень",
                                mCallbackData = "levelOne"
                            )
                        ),
                        listOf(
                            InlineButton(
                                "Второй уровень",
                                mCallbackData = "levelTwo"
                            )
                        )
                    )
                )
            ) {
                mStates.state(updating).editor(mStates).apply {
                    putInt("mainMessage", it)
                }.commit()
            }
        )
    }
}

