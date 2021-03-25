package com.fredhappyface.fhcode

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import java.io.*


class ActivityMain : ActivityThemable() {

    /**
     * Storage of private vars. These being _uri (stores uri of opened file); _createFileRequestCode
     * (custom request code); _readRequestCode (request code for reading a file)
     */
    private var _uri: Uri? = null
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

        val codeEditText: EditText = findViewById(R.id.codeHighlight)
        val textHighlight = TextHighlight(
            codeEditText,
            LanguageRulesJava(),
            ColoursDark()
        )

        textHighlight.start()
        codeEditText.setText("void bar")
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
                showDialogMessage("To implement")
                true
            }
            R.id.action_open -> {
                startFileOpen()
                true
            }
            R.id.action_save -> {
                doFileSave()
                true
            }
            R.id.action_save_as -> {
                startFileSaveAs()
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, ActivitySettings::class.java))
                true
            }
            R.id.action_about -> {
                startActivity(Intent(this, ActivityAbout::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialogMessage(message: String="Saved!"){
        val alertDialog: AlertDialog = AlertDialog.Builder(this, R.style.DialogTheme).create()
        alertDialog.setTitle(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }


    /**
     * Call this when the user clicks menu -> save
     *
     */
    private fun doFileSave() {
        if (_uri != null) {
            Log.e("FHCODE_0", _uri.toString())
            writeTextToUri(_uri!!)
            showDialogMessage()
        } else {
            Log.e("FHCODE_0", "_uri is null!")
            showDialogMessage("Please save as")
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
        _uri = data!!.data
        val codeEditText: EditText = findViewById(R.id.codeHighlight)
        codeEditText.setText(readTextFromUri(_uri!!))

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
        _uri = data!!.data
        writeTextToUri(_uri!!)
        showDialogMessage()
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