package math_bot

import executables.AnswerToCallback
import executables.EditTextMessage
import executables.Executable
import helpers.convertToVertical
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup
import java.lang.Exception

interface LevelSelectingMessage {

    fun levelMessage(): List<Executable>

    class Base(
        private val mKey: String,
        private val mLevelName: String
    ) : LevelSelectingMessage {

        override fun levelMessage(): List<Executable> {
            var message = ""
            var answerCallback = ""
            when (mLevelName) {
                "levelOne" -> {
                    message = buildString {
                        appendLine("На этом уровне буду не сложные примеры\\.")
                        appendLine("Пример\\:")
                        appendLine("2 \\* 2")
                        appendLine("4 \\* 7")
                        appendLine("И т\\.п\\.")
                    }
                    answerCallback = "Выбран первый уровень"
                }
                "levelTwo" -> {
                    message = buildString {
                        appendLine("На этом уровне буду примеры посложнее\\.")
                        appendLine("Пример\\:")
                        appendLine("23 \\* 34")
                        appendLine("11 \\* 56")
                        appendLine("И т\\.п\\.")
                    }
                    answerCallback = "Выбран второй уровень"
                }
                else -> throw Exception()
            }
            return listOf(
                AnswerToCallback(
                    mKey,
                    answerCallback
                ),
                EditTextMessage(
                    mKey,
                    message,
                    mMarkup = InlineKeyboardMarkup(
                        listOf(
                            InlineButton(
                                "Начать уровень",
                                mCallbackData = "startLevel=$mLevelName"
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
}