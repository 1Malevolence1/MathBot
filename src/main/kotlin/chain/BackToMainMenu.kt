package chain

import core.Updating
import executables.AnswerToCallback
import executables.EditTextMessage
import executables.Executable
import executables.SendMessage
import handlers.OnCallbackGotten
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup

class BackToMainMenu : Chain(
    OnCallbackGotten("backToMainMenu")
) {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return listOf(
            AnswerToCallback(
                mKey, "Возвращаюсь на главную страницу\\."
            ),
            EditTextMessage(
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
            )

        )
    }
}