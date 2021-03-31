package com.fredhappyface.fhcode

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import java.io.*


class ActivityMain : ActivityThemable() {

    /**
     * Storage of private vars. These being _uri (stores uri of opened file); _createFileRequestCode
     * (custom request code); _readRequestCode (request code for reading a file)
     */
    private var currentTextSize = 0
    private var _uri: String? = null
    private var _languageID = "java"
    private var _createFileRequestCode: Int = 41
    private var _readRequestCode: Int = 42

    /**
     * Override the onCreate method from ActivityThemable adding the activity_main view and configuring
     * the codeEditText, the textHighlight and the initial text
     *
     * @param savedInstanceState saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get saved state
        _uri = savedInstanceState?.getString("_uri", null)
        _languageID = savedInstanceState?.getString("_languageID", "java").toString()

        // Set up correct colour
        var colours: Colours = ColoursDark()
        if (currentTheme == 0) {
            colours = ColoursLight()
        }

        // Set up correct language
        var languageRules: LanguageRules = LanguageRulesJava()
        when (_languageID) {
            "py" -> languageRules = LanguageRulesPython()
            "xml" -> languageRules = LanguageRulesXML()
        }

        // Set up code edit, apply highlighting and some startup text
        val codeEditText: EditText = findViewById(R.id.codeHighlight)
        val textHighlight = TextHighlight(
            codeEditText,
            languageRules,
            colours
        )
        textHighlight.start()
        codeEditText.setText(R.string.blank_file_text)

        // Apply text size
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        currentTextSize = sharedPreferences.getInt("text", 18)
        codeEditText.textSize = currentTextSize.toFloat()
    }

    /**
     * Triggered when an activity is resumed. If the text size differs from the current text size,
     * then the activity is recreated
     */
    override fun onResume() {
        super.onResume()
        val textSize = sharedPreferences.getInt("text", 18)
        if (currentTextSize != textSize) {
            currentTextSize = textSize
            recreate()
        }
    }


    /**
     * Override the onCreateOptionsMenu method (used to create the overflow menu - see three dotted
     * menu on the title bar)
     *
     * @param menu Menu - this is the popup menu (containing a series of actions)
     * @return Boolean - success!
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * Override the onOptionsItemSelected method. This is essentially a callback method triggered when
     * the end user selects a menu item. Here we filter the item/ action selection and trigger a
     * corresponding action. E.g. action_open -> startFileOpen()
     *
     * @param item MenuItem - this is the item/ action that the user taps
     * @return Boolean - success!
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_new_file -> {
                doNewFile(); true
            }
            R.id.action_open -> {
                startFileOpen(); true
            }
            R.id.action_save -> {
                doFileSave(); true
            }
            R.id.action_save_as -> {
                startFileSaveAs(); true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, ActivitySettings::class.java)); true
            }
            R.id.action_about -> {
                startActivity(Intent(this, ActivityAbout::class.java)); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Override onSaveInstanceState to save the _languageID and _uri when recreating the activity
     *
     * @param outState: Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("_languageID", _languageID)
        outState.putString("_uri", _uri)
    }


    /**
     * Somewhat unintuitive way to obtain the file extension from a URI as android often uses non
     * file path URIs
     *
     * @param uri
     * @return String file extension (short) eg. py for a python file
     */
    private fun getExtFromURI(uri: Uri?): String {
        if (uri != null) {
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val ext = cursor.getString(2) // Get the file name
                cursor.close()
                return ext.split(".").last()
            }
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(
                contentResolver.getType(uri)
            ).toString()
        }
        return "java"
    }

    /**
     * Show a 'saved' dialog. In a function as its reused a couple of times
     *
     */
    private fun showDialogMessageSave() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this, R.style.DialogTheme).create()
        alertDialog.setTitle(getString(R.string.dialog_saved_title))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, getString(R.string.dialog_saved_button)
        ) { dialog, which ->
            dialog.dismiss()
            recreate()
        }
        alertDialog.show()
    }

    /**
     * Call this when the user clicks menu -> new file
     *
     */
    private fun doNewFile() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this, R.style.DialogTheme).create()
        alertDialog.setTitle(getString(R.string.dialog_new_title))
        // Cancel/ No - Do nothing
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, getString(R.string.dialog_new_cancel)
        ) { dialog, which -> dialog.dismiss(); }
        // Confirm/ Yes - Overwrite text, reset language id and uri and refresh
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, getString(R.string.dialog_new_confirm)
        ) { dialog, which ->
            dialog.dismiss()
            val codeEditText: EditText = findViewById(R.id.codeHighlight)
            codeEditText.setText(R.string.blank_file_text)
            _languageID = "java"
            _uri = null
            recreate()
        }
        alertDialog.show()
    }


    /**
     * Call this when the user clicks menu -> save
     *
     */
    private fun doFileSave() {
        if (_uri != null) {
            writeTextToUri(Uri.parse(_uri!!))
            showDialogMessageSave()
        } else {
            startFileSaveAs()
        }
    }


    /**
     * Call this when the user clicks menu -> open
     *
     */
    private fun startFileOpen() {
        intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        startActivityForResult(intent, _readRequestCode)
    }

    /**
     * Callback from onActivityResult
     *
     * @param data Intent - some intent with a uri (accessed with .data)
     */
    private fun completeFileOpen(data: Intent?) {
        _uri = data!!.data.toString()
        _languageID = getExtFromURI(Uri.parse(_uri))
        val codeEditText: EditText = findViewById(R.id.codeHighlight)
        codeEditText.setText(readTextFromUri(Uri.parse(_uri)))
        recreate()
    }

    /**
     * Call this when the user clicks menu -> save as
     *
     */
    private fun startFileSaveAs() {
        intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        startActivityForResult(intent, _createFileRequestCode)
    }

    /**
     * Callback from onActivityResult
     *
     * @param data Intent - some intent with a uri (accessed with .data)
     */
    private fun completeFileSaveAs(data: Intent?) {
        _uri = data!!.data.toString()
        _languageID = getExtFromURI(Uri.parse(_uri))
        writeTextToUri(Uri.parse(_uri))
        showDialogMessageSave()
    }


    /**
     * Write the file text to the URI
     *
     * @param uri Uri - the uri of the file we are going to overwrite
     * @return Boolean - success/ failure!
     */
    private fun writeTextToUri(uri: Uri): Boolean {
        val codeEditText: EditText = findViewById(R.id.codeHighlight)
        try {
            contentResolver.openFileDescriptor(uri, "rwt")?.use { it ->
                FileOutputStream(it.fileDescriptor).use {
                    val bytes = codeEditText.text.toString()
                        .toByteArray()
                    it.write(bytes, 0, bytes.size)

                }
            }
            return true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * Read the file text from the URI
     *
     * @param uri Uri - the uri of the file we are going to read
     * @return String - contents of the file (decoded per readLines())
     */
    private fun readTextFromUri(uri: Uri): String {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readLines().joinToString("\n")
    }

    /**
     * This is basically a callback from an activity
     *
     * @param requestCode Int - RequestCode as defined under the Activity private vars
     * @param resultCode Int - The result code, we only want to do stuff if successful
     * @param data Intent? - Extra data in the form of an intent. tend to access .data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == _readRequestCode) {
                completeFileOpen(data)
            }
            if (requestCode == _createFileRequestCode) {
                completeFileSaveAs(data)
            }
        }

    }
}