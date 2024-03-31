# Changelog

All major and minor version changes will be documented in this file. Details of
patch-level version changes can be found in [commit messages](../../commits/master).

## 20240401 - 2024/03/31

- Update dependencies
- Add new languages:
	- c/c++
	- c#
	- go/golang
	- json
	- php
	- ruby
	- swift
	- ts/js
- Add line numbers

Note - Known bugs are:

- alignment issues for edittext and line numbers
- language support hasn't been thoroughly tested

## 20230820 - 2023/08/20

- Update dependencies
- New Feature: Add Gradle tasks
	- ktlintCheck (`gradlew ktlintCheck`): run ktlint over the codebase
	- genDocs (`gradlew genDocs`): generate the api reference using dokka

## 20220110 - 2022/01/10

- Update dependencies
- Remove old launcher foreground
- Set file size limit to 1Mb to defend against `java.lang.OutOfMemoryError`

## 20211104 - 2021/11/04

- Add feature description
- Use cards (similar style to EweSticker)
- Add comments to TextHighlight
- Update fonts
- Code clean up + documentation improvements
- The new target SDK version is 31 (Android 12) - previously 29 (Android 10)
- The new minimum SDK version is 26 (Android 8 Oreo) - previously 23 (Android 6 Marshmallow)
- Add launcher Shortcuts for Settings, About, New File

## 20211030 - 2021/10/30

- Add screenshots to readme
- Add fastlane metadata
- Gradle to kotlin
- Improve documentation
- Code clean up
- replace deprecated onActivityResult with registerForActivityResult logic
- tidy up files committed to git

## 2021/03/04

- Created repo
- Created docs
- Made a start on project structure and the text edit class and requirements
