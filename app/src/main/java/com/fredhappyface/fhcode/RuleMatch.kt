package com.fredhappyface.fhcode

/**
 * Rule match representation
 *
 * @property ruleName name of the rule (eg. "keywords")
 * @property startIndex start char index
 * @property endIndex end char index
 */
data class RuleMatch(
	val ruleName: String,
	val startIndex: Int,
	val endIndex: Int
)
