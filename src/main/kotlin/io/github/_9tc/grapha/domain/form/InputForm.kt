package io.github._9tc.grapha.domain.form

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.*


class InputForm (
    @field:NotNull(message = "頂点数が空欄です")
    @field:Range(min=1, max=100, message = "頂点数が範囲を超えています")
    var vertices: Long? = 1,

    @field:NotNull(message = "確率が空欄です")
    @field:Range(min=0, max=100, message = "確率が範囲を超えています")
    var percentage: Long? = 0,

    @field:Max(9999999999)
    var seed: Long? = null,

    var hasLabel: Boolean = false
        ){
}