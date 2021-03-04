package com.fredhappyface.fhcode

class LanguageRulesJava(): LanguageRules {

    private val KEYWORDS: Regex = "(void|boolean|int|float|double)(?=\\s)".toRegex()

    override fun matchKeywords(string: CharSequence){
        KEYWORDS.findAll(string).map {
            RuleMatch(it.range.first, it.range.last + 1)
        }.toList()
    }
}