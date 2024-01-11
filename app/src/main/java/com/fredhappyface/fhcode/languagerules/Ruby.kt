package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesRuby implements interface LanguageRules
 */
class Ruby : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match Ruby constants (e.g., numbers, strings)
		val match = """\b(?:\d*\.\d+|\d+)(?:[eE][+-]?\d+)?\b|(["'])(?:(?!\1).|\\\1)*\1""".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match Ruby require statements
		val match = """\brequire\s*['"].+?['"]""".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		return emptyList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		// Match Ruby single-line and multi-line comments
		val match = """#.*|=begin[\s\S]*?=end""".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// Match Ruby class and module declarations
		val match = """\b(?:class|module)\s+[A-Za-z_][A-Za-z_\d]*""".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		// Match common Ruby keywords
		val match = """\b(?:if|else|elsif|unless|while|until|do|end|def|alias|undef|return|break|next|retry|super|self|true|false|nil|and|or|not)\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
