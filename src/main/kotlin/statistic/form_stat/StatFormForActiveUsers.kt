package statistic.form_stat

import core.Updating
import statistic.period_time.StatisticsTimePeriod
import statistic.storage.StatisticHandling
import statistic.storage.StatisticType

class StatFormForActiveUsers(
    private val mStats: StatisticHandling,
    private val mPeriod: StatisticsTimePeriod
) : StatisticForm {

    override fun statisticValue(updating: Updating): Int {
        val slice = mStats.statSliceByDate(
            StatisticType.NewComing,
            mPeriod.dateRange(updating)
        )
        val users = mutableListOf<Long>()
        for (i in slice.indices) {
            if (!users.contains(slice[i].map(UserIdFromStat()))) {
                users.add(slice[i].map(UserIdFromStat()))
            }
        }
        return users.size
    }
}