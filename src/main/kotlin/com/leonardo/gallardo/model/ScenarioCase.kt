package uol.ps.merchant.report.model

import com.fasterxml.jackson.annotation.JsonCreator

data class ScenarioCase @JsonCreator constructor(

        var caseNr: Int,

        var explicitArguments: List<String> = arrayListOf(),

        var derivedArguments: List<String> = arrayListOf(),

        var status: String,

        var success: Boolean,

        var durationInNanos: Long,

        var steps: List<Step> = arrayListOf(),

        var errorMessage: String?,

        var stackTrace: List<String> = arrayListOf()

)