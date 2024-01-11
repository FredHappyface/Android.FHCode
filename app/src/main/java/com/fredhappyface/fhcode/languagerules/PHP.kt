package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesPHP implements interface LanguageRules
 */
class PHP : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match PHP constants (e.g., numbers, strings)
		val match = """\b(?:\d*\.\d+|\d+)(?:[eE][+-]?\d+)?\b|(["'])(?:(?!\1).|\\\1)*\1""".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match PHP include and require statements
		val match = """\b(?:include|include_once|require|require_once)\s*\(.*?\)""".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		// Match PHP single-line and multi-line comments
		val match = """\/\/.*|\/\*[\s\S]*?\*\/""".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// Match PHP class declarations
		val match = """\bclass\s+[A-Za-z_][A-Za-z_\d]*\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		// Match common PHP keywords
		val match = """\b(?:echo|if|else|elseif|while|do|for|foreach|switch|case|break|continue|return|function|include|require|include_once|require_once|namespace|use|final|class|interface|abstract|trait|extends|implements|public|protected|private|static)\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
