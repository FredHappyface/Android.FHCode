package com.fredhappyface.fhcode.languagerules

/**
 * LanguageRulesCSharp implements interface LanguageRules
 */
class CSharp : LanguageRules {
	override fun matchConstants(string: CharSequence): List<RuleMatch> {
		// Match C# constants (e.g., numbers, strings)
		val match = """\b(?:\d*\.\d+|\d+)(?:[eE][+-]?\d+)?\b|(["'])(?:(?!\1).|\\\1)*\1""".toRegex()
		return match.findAll(string).map {
			RuleMatch("constant", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchImports(string: CharSequence): List<RuleMatch> {
		// Match C# using directives
		val match = """\busing\s+[A-Za-z_][A-Za-z_\d.]*\s*;""".toRegex()
		return match.findAll(string).map {
			RuleMatch("import", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchAnnotations(string: CharSequence): List<RuleMatch> {
		// C# does not have explicit annotations, but you can customize this
		return emptyList()
	}

	override fun matchComments(string: CharSequence): List<RuleMatch> {
		// Match C# single-line and multi-line comments
		val match = """\/\/.*|\/\*[\s\S]*?\*\/""".toRegex()
		return match.findAll(string).map {
			RuleMatch("comment", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchClasses(string: CharSequence): List<RuleMatch> {
		// Match C# class declarations
		val match = """\b(?:class|interface|enum|struct)\s+[A-Za-z_][A-Za-z_\d]*""".toRegex()
		return match.findAll(string).map {
			RuleMatch("class", it.range.first, it.range.last + 1)
		}.toList()
	}

	override fun matchKeywords(string: CharSequence): List<RuleMatch> {
		// Match common C# keywords
		val match = """\b(?:if|else|for|while|switch|case|break|continue|return|new|this|base|typeof|sizeof|null|true|false)\b""".toRegex()
		return match.findAll(string).map {
			RuleMatch("keyword", it.range.first, it.range.last + 1)
		}.toList()
	}
}
