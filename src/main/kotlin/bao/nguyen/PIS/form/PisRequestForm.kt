package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import java.util.*
import javax.persistence.JoinColumn
import javax.validation.constraints.NotNull

data class PisRequestForm (
//        @NotNull
//        var bakery_id: Int = 0,
//        @NotNull
//        var cake_id:  Int = 0,
        @JoinColumn(name="cake_id")
        var pisCake: PisCake?=null,
        @JoinColumn(name="bakery_id")
        var pisBakery: PisBakery?=null,
        @NotNull
        var createAt: Date = Date(),
        @NotNull
        var piece: Int = 0
): BaseForm()