package com.leonardo.gallardo

import com.googlecode.htmlcompressor.compressor.HtmlCompressor
import com.leonardo.gallardo.transformers.ConfluenceCommunicator
import com.leonardo.gallardo.transformers.JGivenJsonToObjectTransformer
import com.leonardo.gallardo.transformers.ObjectToHtmlTransformer

fun main(args: Array<String>) {
    JgivenToConfluence().send()
}

class JgivenToConfluence {

    fun send() {
        val features = JGivenJsonToObjectTransformer().transform()
        val html = ObjectToHtmlTransformer().transform(features)
        val htmlMinified = HtmlCompressor().compress(html.joinToString(separator = ""))
        ConfluenceCommunicator().sendToConfluence(htmlMinified)
    }

}