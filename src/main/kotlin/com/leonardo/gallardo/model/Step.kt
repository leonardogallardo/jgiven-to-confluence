package uol.ps.merchant.report.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class Step @JsonCreator constructor(

        var name: String,
        var status: String,
        var durationInNanos: Long,
        var words: List<Words> = arrayListOf()


)