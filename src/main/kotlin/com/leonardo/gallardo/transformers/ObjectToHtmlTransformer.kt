package com.leonardo.gallardo.transformers

import com.leonardo.gallardo.util.ResourceLoader
import j2html.TagCreator
import j2html.TagCreator.*
import j2html.tags.ContainerTag
import uol.ps.merchant.report.model.Feature
import uol.ps.merchant.report.model.Scenario
import java.util.function.Function

class ObjectToHtmlTransformer {

    companion object {
        val EXPAND_MACRO = "expand_macro.html"
        val EXPAND_NAME = "#(expand_name)"
        val EXPAND_CONTENT = "#(expand_content)"
    }

    val resourceLoader = ResourceLoader()

    fun transform(features: List<Feature>): List<String> {
        return features.map { feature ->

            val htmlScenarios = generateHtmlScenarios(feature).joinToString(separator = "")

            var htmlFeature = resourceLoader.loadAsString(EXPAND_MACRO)

            htmlFeature.replace(EXPAND_NAME, feature.name).replace(EXPAND_CONTENT, htmlScenarios)
        }
    }

    private fun generateHtmlScenarios(feature: Feature): List<String> {
        return feature.scenarios.map { scenario ->

            val scenarioContent = generateScenarioContent(scenario)

            var htmlScenario = resourceLoader.loadAsString(EXPAND_MACRO)
            htmlScenario.replace(EXPAND_NAME, scenario.description).replace(EXPAND_CONTENT, scenarioContent)
        }
    }

    private fun generateScenarioContent(scenario: Scenario): String {
        return each(scenario.scenarioCases[0].steps) { step ->
            p(
                span(step.words[0].value + " "),
                span(step.name)
            )
        }.render() +
                table(
                    tbody(
                        tr(
                            each(scenario.explicitParameters) { th(span(it)) }
                        ),
                        each(scenario.scenarioCases) { case -> tr(each(case.explicitArguments) { td(span(it)) }) }
                    )
                ).render()
    }


}
