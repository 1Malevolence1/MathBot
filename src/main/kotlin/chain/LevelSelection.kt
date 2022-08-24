package chain

import core.Updating
import executables.AnswerToCallback
import executables.EditTextMessage
import executables.Executable
import executables.SendMessage
import handlers.CommandEvent
import handlers.OnCallbackGotten
import helpers.convertToVertical
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup
import updating.UpdatingChatId

class LevelSelection : Chain(

    OnCallbackGotten("levelOne")
) {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return listOf(
            AnswerToCallback(
                mKey,
                "Выбран первый уровень."
            ),

            EditTextMessage(
                mKey,
                buildString {
                    appendLine("На этом уровне буду не сложные примеры\\.")
                    appendLine("Пример\\:")
                    appendLine("2 \\* 2")
                    appendLine("4 \\* 7")
                    appendLine("И т\\.п\\.")
                },
                mMarkup = InlineKeyboardMarkup(
                    listOf(

                        InlineButton(
                            "Начать уровень",
                                mCallbackData = "startLevel"
                        ),



                        InlineButton(
                            "Вернуться",
                            mCallbackData = "backToMainMenu"
                        )
                    ).convertToVertical()
                )

            )
        )

    }
}