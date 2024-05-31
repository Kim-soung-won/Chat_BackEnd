package com.kkk.kotlinapp.Member.DTO

import jakarta.validation.constraints.NotEmpty
import lombok.Data

@Data
class MemberInsertDTO {
    @NotEmpty
    var name: String = ""

    @NotEmpty
    var email: String = ""

    @NotEmpty
    var password: String = ""
}