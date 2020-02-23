package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailySale
import java.util.*
import javax.validation.constraints.NotNull

data class DeliveryForm(
        @NotNull
        var cake: PisCake = PisCake(),

        @NotNull
        var bakery: PisBakery = PisBakery(),

        @NotNull
        var create_at: Date = Date(),

        @NotNull
        var pieces: Int = 1,

        @NotNull
        var tray: Float = 0.toFloat()


): BaseForm()