package com.fredhappyface.fhcode

class LanguageRulesPython : LanguageRules {

    override fun matchConstants(string: CharSequence): List<RuleMatch> {
        val match: Regex = "([A-Z0-9])*".toRegex()
        return match.findAll(string).map {
            RuleMatch("constant", it.range.first, it.range.last + 1)
        }.toList()
    }

    override fun matchImports(string: CharSequence): List<RuleMatch> {
        val match: Regex = "import(?=\\s)".toRegex()
        return match.findAll(string).map {
            RuleMatch("import", it.range.first, it.range.last + 1)
        }.toList()
    }

    override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
        val match: Regex = "@([\\w.]+)".toRegex()
        return match.findAll(string).map {
            RuleMatch("annotation", it.range.first, it.range.last + 1)
        }.toList()
    }

    override fun matchComments(string: CharSequence): List<RuleMatch> {
        val match: Regex = "#.*|('{3}|\"{3})(.|\\n)*?('{3}|\"{3})".toRegex()
        return match.findAll(string).map {
            RuleMatch("comment", it.range.first, it.range.last + 1)
        }.toList()
    }

    override fun matchClasses(string: CharSequence): List<RuleMatch> {
        val match: Regex = "(class|object)(?=\\s)".toRegex()
        return match.findAll(string).map {
            RuleMatch("class", it.range.first, it.range.last + 1)
        }.toList()
    }

    override fun matchKeywords(string: CharSequence): List<RuleMatch> {
        val match: Regex =
            "\\b(return|pass|lambda|with|is|not|in|from|elif|raise|del)(?=\\b)|(def)\\s+(\\w+)(?=\\()".toRegex()
        return match.findAll(string).map {
            RuleMatch("keyword", it.range.first, it.range.last + 1)
        }.toList()
    }
}