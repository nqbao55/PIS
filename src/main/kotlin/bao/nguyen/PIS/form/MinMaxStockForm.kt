package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisSetting
import javax.validation.constraints.NotNull

data class MinMaxStockForm(
        @NotNull
        var cake: PisCake = PisCake(),

        @NotNull
        var listSetting: MutableList<PisSetting> = mutableListOf(),

        @NotNull
        var listId: MutableList<Int> = mutableListOf()

): BaseForm()