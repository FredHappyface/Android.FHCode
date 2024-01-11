package com.fredhappyface.fhcode.languagerules

class JSON : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		val numberMatch: Regex = "-?\\b\\d+(\\.\\d+)?([eE][-+]?\\d+)?\\b".toRegex()
		return numberMatch.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		val booleanMatch: Regex = "\\b(?:true|false)\\b".toRegex()
		return booleanMatch.findAll(string).map {
			RuleMatch("annotation", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		val nullMatch: Regex = "\\bnull\\b".toRegex()
		return nullMatch.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		val stringMatch: Regex = "\"(?:\\\\\"|[^\"])*\"".toRegex()
		return stringMatch.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}
}
