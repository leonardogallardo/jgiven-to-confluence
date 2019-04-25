package uol.ps.merchant.report.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonIgnoreProperties(ignoreUnknown = true)
data class Feature @JsonCreator constructor(

        var className: String,

        var name: String,

        var scenarios: List<Scenario> = arrayListOf()

)