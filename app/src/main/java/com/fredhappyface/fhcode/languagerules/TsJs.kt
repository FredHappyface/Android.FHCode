package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesTSJS implements interface LanguageRules
 */
class TSJS : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match constants (e.g., numbers, strings)
		val match = """(['"`])((?:(?!\1).|\\\1)*)(\1)|\b(?:\d*\.\d+|\d+)(?:[eE][+-]?\d+)?\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match import statements
		val match = """\b(import|require)\s*\(.+?\)""".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		// Match TypeScript/JavaScript annotations
		val match = """@(\w+)""".toRegex()
		return match.findAll(string).map {
			RuleMatch("annotation", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		// Match single-line and multi-line comments
		val match = """\/\/.*|\/\*[\s\S]*?\*\/""".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// Match class declarations
		val match = """\bclass\s+\w+""".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		// Match common JavaScript/TypeScript keywords
		val match = """\b(?:if|else|for|while|switch|case|break|continue|return|function|const|let|var|async|await)\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
