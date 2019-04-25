package uol.ps.merchant.report.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class Scenario @JsonCreator constructor(

        var className: String,

        var testMethodName: String,

        var description: String,

        var explicitParameters: List<String> = arrayListOf(),

        var derivedParameters: List<String> = arrayListOf(),

        var casesAsTable: Boolean,

        var scenarioCases: List<ScenarioCase> = arrayListOf(),

        var durationInNanos: Long,

        var tagIds: List<String> = arrayListOf()

)