package admin_bot_functions.add_new_admins

import Storages
import admin_bot_functions.admins_storage.AdminsHandling
import chain.Chain
import core.Updating
import executables.DeleteMessage
import executables.EditTextMessage
import executables.Executable
import handlers.OnTextGotten
import staging.safetyBoolean
import updating.UpdatingMessage

class AddNewAdminChainFinal(
    private val mAdminsHandling: AdminsHandling
) : Chain(
    OnTextGotten()
) {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return if (mStates.state(updating).safetyBoolean("isAwaitForBotPassword")) {
            if (updating.map(UpdatingMessage()) == Storages.stConfig.configValueString("botPassword")) {
                mStates.state(updating).editor(mStates).apply {
                    deleteValue("isAwaitForBotPassword")
                }.commit()
                mAdminsHandling.addNewAdmin(updating)
                listOf(
                    DeleteMessage(
                        mKey,
                        updating
                    ),
                    EditTextMessage(
                        mKey,
                        "Отлично вы добавлены в список администраторов\\!",
                        mEditingMessageId = mStates.state(updating).long("addNewAdminMessage")
                    )
                )
            } else {
                listOf(
                    DeleteMessage(
                        mKey,
                        updating
                    ),
                    EditTextMessage(
                        mKey,
                        "Пароль ввдён неверно\\!",
                        mEditingMessageId = mStates.state(updating).long("addNewAdminMessage")
                    )
                )
            }
        } else {
            emptyList()
        }
    }
}