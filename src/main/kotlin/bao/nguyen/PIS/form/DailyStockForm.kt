package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailyStock
import javax.validation.constraints.NotNull

data class DailyStockForm(
        @NotNull
        var cake: PisCake = PisCake(),

        @NotNull
        var listDailyStock: MutableList<PisDailyStock> = mutableListOf(),

        @NotNull
        var listId: MutableList<Int> = mutableListOf()


): BaseForm()