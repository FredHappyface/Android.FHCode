package com.fredhappyface.fhcode

interface LanguageRules {

    fun matchKeywords(string: CharSequence): List<RuleMatch>


    fun createHighlighting(string: CharSequence): List<RuleMatch> {
        return matchKeywords(string)
    }
}