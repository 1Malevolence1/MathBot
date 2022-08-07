package admin_bot_functions.statistic.chains

import chain.Chain
import core.Updating
import executables.DeleteMessage
import executables.Executable
import executables.SendMessage
import handlers.CommandEvent
import helpers.convertToVertical
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup
import admin_bot_functions.statistic.period_time.StatisticsTimePeriod
import handlers.OnCallbackGotten

class StartViewingStatisticChain(
    private val mStatPeriods: StatisticsTimePeriod
) : Chain(OnCallbackGotten("startViewStat")) {

    override suspend fun executableChain(updating: Updating): List<Executable> {
        mStatPeriods.setupDefaultPeriod(updating)
        return listOf(
            DeleteMessage(
                mKey,
                updating
            ),
            SendMessage(
                mKey,
                "*Статистика*\n" +
                        "Здесь представлены разделы по которым вы можете посмотреть статистику",
                mMarkup = InlineKeyboardMarkup(
                    listOf(
                        InlineButton(
                            "Активные пользователи",
                            mCallbackData = "activeUsersStatistic"
                        ),
                        InlineButton(
                            "Новые пользователи",
                            mCallbackData = "newUsersStatistic"
                        ),
                        InlineButton(
                            "Взаимодействия",
                            mCallbackData = "actionsStatistic"
                        ),
                        InlineButton(
                            "Вернуться",
                            mCallbackData = "backToAdminPanel"
                        )
                    ).convertToVertical()
                )
            )
        )
    }
}