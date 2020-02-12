package bao.nguyen.PIS.form

import javax.validation.constraints.NotNull

data class PisBakeryForm(
        @NotNull
        var name: String = String(),
        @NotNull
        var username: String = String(),
        @NotNull
        var password: String = String(),
        @NotNull
        var address: String = String(),
        @NotNull
        var phone: String = String(),
        @NotNull
        var email: String = String()
): BaseForm()