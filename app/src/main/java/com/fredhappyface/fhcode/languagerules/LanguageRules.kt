package com.fredhappyface.fhcode.languagerules

/**
 * interface LanguageRules implemented by LanguageRulesJava, LanguageRulesPython, LanguageRulesXML, ...
 */
interface LanguageRules {
	/**
	 * match on Constants
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun matchConstants(string: CharSequence): List<RuleMatch>

	/**
	 * match on Comments
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun matchComments(string: CharSequence): List<RuleMatch>

	/**
	 * match on Annotations
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun matchAnnotations(string: CharSequence): List<RuleMatch>

	/**
	 * match on Classes
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun matchClasses(string: CharSequence): List<RuleMatch>

	/**
	 * match on Imports
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun matchImports(string: CharSequence): List<RuleMatch>

	/**
	 * match on Keywords
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun matchKeywords(string: CharSequence): List<RuleMatch>

	/**
	 * Combine matching rules
	 *
	 * @param string CharSequence sequence of chars to perform rule matching on
	 * @return List<RuleMatch> list of rule matches
	 */
	fun createHighlighting(string: CharSequence): List<RuleMatch> {
		return matchKeywords(string) + matchAnnotations(string) + matchClasses(string) +
				matchConstants(string) + matchComments(string) + matchImports(string)
	}
}
