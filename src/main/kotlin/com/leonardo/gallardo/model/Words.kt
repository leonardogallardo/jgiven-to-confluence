package uol.ps.merchant.report.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@JsonIgnoreProperties(ignoreUnknown = true)
data class Words @JsonCreator constructor(

        var value: String

)