package com.fredhappyface.fhcode

class LanguageRulesJava : LanguageRules {

    private val _keywords: Regex = "(void|boolean|int|float|double)(?=\\s)".toRegex()

    override fun matchKeywords(string: CharSequence): List<RuleMatch> {
        return _keywords.findAll(string).map {
            RuleMatch("keyword", it.range.first, it.range.last + 1)
        }.toList()
    }
}