import admin_bot_functions.admins_storage.AdminToJson
import admin_bot_functions.admins_storage.AdminsFileHandling
import admin_bot_functions.admins_storage.AdminsHandling
import admin_bot_functions.deeplinking.handling.DeeplinkToJson
import admin_bot_functions.deeplinking.storage.DeeplinkFileHandling
import admin_bot_functions.deeplinking.storage.DeeplinkStorage
import configuration.Config
import helpers.storage.JsonFile
import helpers.storage.StatesFileHelping
import helpers.storage.UserFileHelping
import staging.StateHandling
import admin_bot_functions.statistic.storage.StatisticFileHandling
import admin_bot_functions.statistic.storage.StatisticHandling
import admin_bot_functions.statistic.storage.StatisticItemToJson
import users.AllUsersStorage
import users.UserToJson
import java.io.File

object Storages {
    val stAdmins = AdminsHandling.Base(
        AdminsFileHandling(
            JsonFile.Base(
                File("${sBasePath}admins.json")
            ), AdminToJson()
        )
    )
    val stConfig = Config.Base(File("${sBasePath}config.json"))
    val stStateStorage = StateHandling.Base(
        StatesFileHelping(
            JsonFile.Base(File("${sBasePath}states.json"))
        )
    )
    val stUsersStorage = AllUsersStorage.Base(
        UserFileHelping(
            JsonFile.Base(File("${sBasePath}allUsers.json")),
            UserToJson()
        )
    )
    val stStatistics = StatisticHandling.Base(
        StatisticFileHandling(
            JsonFile.Base(File("${sBasePath}statistic.json")),
            StatisticItemToJson()
        )
    )
    val stDeeplink = DeeplinkStorage.Base(
        DeeplinkFileHandling(
            JsonFile.Base(File("${sBasePath}deeplink.json")),
            DeeplinkToJson()
        ),
        stConfig.configValueString("botName")
    )
}