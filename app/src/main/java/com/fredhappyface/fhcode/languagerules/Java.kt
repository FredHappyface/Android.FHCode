package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesJava implements interface LanguageRules
 */
class Java : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		val match: Regex = "final(?=\\s)".toRegex()
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
		val match: Regex = "@\\S+(\\(.*?\\))?".toRegex()
		return match.findAll(string).map {
			RuleMatch("annotation", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		val match: Regex = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		val match: Regex = "class(?=\\s)".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		val keywords: Regex =
			"\\b(void|boolean|int|float|double|char|long|short|byte|final|static|abstract|extends|implements|if|else|for|while|do|switch|case|break|continue|return|new|this|super|instanceof|try|catch|finally|throw|throws|assert|package|import)\\b".toRegex()
		return keywords.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
