package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesXML implements interface LanguageRules
 */
class XML : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match XML tags (both start and end tags)
		val tagMatch: Regex = "<(.*?)/?>".toRegex()
		return tagMatch.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match XML attributes within tags
		val attributeMatch: Regex = "\\w+\\s*=\\s*\"[^\"]*\"".toRegex()
		return attributeMatch.findAll(string).map {
			RuleMatch("annotation", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		// Match attribute values (in double quotes)
		val match: Regex = "\".*?\"".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		val match: Regex = "<!--(.|\\n)*?-->".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// CDATA sections can be highlighted as classes
		val match: Regex = "<!\\[CDATA\\[(.|\\n)*?]]>".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}
}
