package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesGo implements interface LanguageRules
 */
class Go : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match Go constants (e.g., numbers, strings)
		val match = """\b(?:\d*\.\d+|\d+)(?:[eE][+-]?\d+)?\b|(["'])(?:(?!\1).|\\\1)*\1""".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match Go import statements
		val match = """\bimport\s*\(\s*[^)]*\s*\)""".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		// Match Go single-line and multi-line comments
		val match = """\/\/.*|\/\*[\s\S]*?\*\/""".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// Match Go type and struct declarations
		val match = """\b(?:type|struct)\s+[A-Za-z_][A-Za-z_\d]*""".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		// Match common Go keywords
		val match = """\b(?:func|var|const|if|else|for|switch|case|break|continue|return|defer|panic|recover)\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
