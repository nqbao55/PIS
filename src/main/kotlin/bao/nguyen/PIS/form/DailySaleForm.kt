package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailySale
import javax.validation.constraints.NotNull

data class DailySaleForm(
        @NotNull
        var cake: PisCake = PisCake(),

        @NotNull
        var listDailySale: MutableList<PisDailySale> = mutableListOf(),

        @NotNull
        var listId: MutableList<Int> = mutableListOf()


): BaseForm()