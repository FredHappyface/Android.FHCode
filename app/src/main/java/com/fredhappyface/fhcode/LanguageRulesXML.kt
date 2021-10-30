package com.fredhappyface.fhcode

/**
 * LanguageRulesXML implements interface LanguageRules
 */
class LanguageRulesXML : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		val match: Regex = "<(.*?)>".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		val match: Regex = "\".*?\"".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		val match: Regex = "".toRegex()
		return match.findAll(string).map {
			RuleMatch("annotation", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		val match: Regex = "<!--(.|\\n)*?-->".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		val match: Regex = "".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		val match: Regex = "".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
