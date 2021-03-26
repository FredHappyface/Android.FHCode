package com.fredhappyface.fhcode

interface LanguageRules {

    fun matchConstants(string: CharSequence): List<RuleMatch>
    fun matchComments(string: CharSequence): List<RuleMatch>
    fun matchAnnotations(string: CharSequence): List<RuleMatch>
    fun matchClasses(string: CharSequence): List<RuleMatch>
    fun matchImports(string: CharSequence): List<RuleMatch>
    fun matchKeywords(string: CharSequence): List<RuleMatch>


    fun createHighlighting(string: CharSequence): List<RuleMatch> {
        return matchKeywords(string) + matchAnnotations(string) + matchClasses(string) +
                matchConstants(string) + matchComments(string) + matchImports(string)
    }
}