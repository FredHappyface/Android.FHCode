package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesSwift implements interface LanguageRules
 */
class Swift : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match Swift constants (e.g., numbers, strings)
		val match = """\b(?:\d*\.\d+|\d+)(?:[eE][+-]?\d+)?\b|(["'])(?:(?!\1).|\\\1)*\1""".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match Swift import statements
		val match = """\bimport\s+[A-Za-z_][A-Za-z_\d]*\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		// Match Swift single-line and multi-line comments
		val match = """\/\/.*|\/\*[\s\S]*?\*\/""".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// Match Swift class, struct, enum, and protocol declarations
		val match = """\b(?:class|struct|enum|protocol)\s+[A-Za-z_][A-Za-z_\d]*""".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		// Match common Swift keywords
		val match = """\b(?:if|else|guard|switch|case|default|for|in|while|do|repeat|break|continue|return|throw|try|catch|as|is|new|deinit|init|self|super|true|false|nil|associatedtype|class|struct|enum|protocol|extension|func|var|let)\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
