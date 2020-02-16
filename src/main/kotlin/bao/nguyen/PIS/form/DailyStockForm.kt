package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisDailyStock
import javax.validation.constraints.NotNull

data class DailyStockForm(
        @NotNull
        var bakery: PisBakery = PisBakery(),

        @NotNull
        var listDailyStock: MutableList<PisDailyStock> = mutableListOf(),

        @NotNull
        var listId: MutableList<Int> = mutableListOf()


): BaseForm()