package bao.nguyen.PIS.form

import javax.validation.constraints.NotNull

data class AddPisBakeryForm(
        @NotNull
        val name: String = "",
        @NotNull
        val username: String = "",
        @NotNull
        val password: String = "",
        @NotNull
        val address: String = "",
        @NotNull
        val phone: String = "",
        @NotNull
        val email: String = ""
)