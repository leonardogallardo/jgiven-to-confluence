package com.leonardo.gallardo.transformers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import uol.ps.merchant.report.model.Feature
import java.io.File

class JGivenJsonToObjectTransformer {

    val REPORTS = System.getProperty("user.dir") +
            File.separator + "build" +
            File.separator + "jgiven-results" +
            File.separator + "test" +
            File.separator

    fun transform(): List<Feature> {
        val jsonFiles = File(REPORTS).listFiles()
        val objMapper = ObjectMapper().registerModule(KotlinModule())
        return jsonFiles.map { file -> objMapper.readValue(file, Feature::class.java) }
    }


}
