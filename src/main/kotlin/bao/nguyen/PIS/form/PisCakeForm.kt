package bao.nguyen.PIS.form

import javax.validation.constraints.NotNull

data class PisCakeForm (
    @NotNull
    var name: String = String(),
    @NotNull
    var price: String = String()
): BaseForm()