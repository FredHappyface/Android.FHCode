//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[ActivityMain](index.md)

# ActivityMain

[androidJvm]\
class [ActivityMain](index.md) : [ActivityThemable](../-activity-themable/index.md)

ActivityMain class inherits from the ActivityThemable class - provides the settings view

## Constructors

| | |
|---|---|
| [ActivityMain](-activity-main.md) | [androidJvm]<br>fun [ActivityMain](-activity-main.md)() |

## Functions

| Name | Summary |
|---|---|
| [doFileSave](do-file-save.md) | [androidJvm]<br>private fun [doFileSave](do-file-save.md)()<br>Call this when the user clicks menu -> save |
| [doNewFile](do-new-file.md) | [androidJvm]<br>private fun [doNewFile](do-new-file.md)()<br>Call this when the user clicks menu -> new file |
| [getExtFromURI](get-ext-from-u-r-i.md) | [androidJvm]<br>private fun [getExtFromURI](get-ext-from-u-r-i.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Somewhat unintuitive way to obtain the file extension from a URI as android often uses non file path URIs |
| [onCreate](on-create.md) | [androidJvm]<br>protected open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Override the onCreate method from ActivityThemable adding the activity_main view and configuring the codeEditText, the textHighlight and the initial text |
| [onCreateOptionsMenu](on-create-options-menu.md) | [androidJvm]<br>open override fun [onCreateOptionsMenu](on-create-options-menu.md)(menu: [Menu](https://developer.android.com/reference/kotlin/android/view/Menu.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Override the onCreateOptionsMenu method (used to create the overflow menu - see three dotted menu on the title bar) |
| [onOptionsItemSelected](on-options-item-selected.md) | [androidJvm]<br>open override fun [onOptionsItemSelected](on-options-item-selected.md)(item: [MenuItem](https://developer.android.com/reference/kotlin/android/view/MenuItem.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Override the onOptionsItemSelected method. This is essentially a callback method triggered when the end user selects a menu item. Here we filter the item/ action selection and trigger a corresponding action. E.g. action_open -> startFileOpen() |
| [onResume](on-resume.md) | [androidJvm]<br>protected open override fun [onResume](on-resume.md)()<br>Triggered when an activity is resumed. If the text size differs from the current text size, then the activity is recreated |
| [onSaveInstanceState](on-save-instance-state.md) | [androidJvm]<br>protected open override fun [onSaveInstanceState](on-save-instance-state.md)(outState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html))<br>Override onSaveInstanceState to save the _languageID and _uri when recreating the activity |
| [readTextFromUri](read-text-from-uri.md) | [androidJvm]<br>private fun [readTextFromUri](read-text-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Read the file text from the URI |
| [showDialogMessageSave](show-dialog-message-save.md) | [androidJvm]<br>private fun [showDialogMessageSave](show-dialog-message-save.md)()<br>Show a 'saved' dialog. In a function as its reused a couple of times |
| [startFileOpen](start-file-open.md) | [androidJvm]<br>private fun [startFileOpen](start-file-open.md)()<br>Call this when the user clicks menu -> open |
| [startFileSaveAs](start-file-save-as.md) | [androidJvm]<br>private fun [startFileSaveAs](start-file-save-as.md)()<br>Call this when the user clicks menu -> save as |
| [writeTextToUri](write-text-to-uri.md) | [androidJvm]<br>private fun [writeTextToUri](write-text-to-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Write the file text to the URI |

## Properties

| Name | Summary |
|---|---|
| [completeFileOpen](complete-file-open.md) | [androidJvm]<br>private val [completeFileOpen](complete-file-open.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;<br>Handles ACTION_OPEN_DOCUMENT result and sets this.uri, mLanguageID and codeEditText |
| [completeFileSaveAs](complete-file-save-as.md) | [androidJvm]<br>private val [completeFileSaveAs](complete-file-save-as.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;<br>Handles ACTION_CREATE_DOCUMENT result and sets this.uri, mLanguageID and triggers writeTextToUri |
| [currentTextSize](current-text-size.md) | [androidJvm]<br>private var [currentTextSize](current-text-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0<br>Storage of private vars. These being _uri (stores uri of opened file); _createFileRequestCode (custom request code); _readRequestCode (request code for reading a file) |
| [languageID](language-i-d.md) | [androidJvm]<br>private var [languageID](language-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [uri](uri.md) | [androidJvm]<br>private var [uri](uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
