package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import javax.validation.constraints.NotNull

data class HomeForm(
        @NotNull
        var cake: PisCake = PisCake(),

        @NotNull
        var bakery: PisBakery = PisBakery(),

        @NotNull
        var minStock: Int = 0, // day

        @NotNull
        var maxStock: Int = 0, // day

        @NotNull
        var currentStock: Int = 0, // pieces

        @NotNull
        var dailySale: Int = 0, // pieces

        @NotNull
        var miss: Int = 0

): BaseForm()