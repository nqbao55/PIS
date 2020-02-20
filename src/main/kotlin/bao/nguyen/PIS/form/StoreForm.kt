package bao.nguyen.PIS.form

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisStore
import javax.validation.constraints.NotNull

data class StoreForm(

        @NotNull
        var bakery: PisBakery = PisBakery(),

        @NotNull
        var listStore: MutableList<PisStore> = mutableListOf(),

        @NotNull
        var listId: MutableList<Int> = mutableListOf()


): BaseForm()