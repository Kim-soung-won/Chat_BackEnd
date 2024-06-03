package com.kkk.kotlinapp.SpringExam.DTO

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