package org.irham3.storyapp.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import org.irham3.storyapp.R

class PasswordEditText : AppCompatEditText {
    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private fun init() {
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        hint = context.getString(R.string.password_hint)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setAutofillHints(AUTOFILL_HINT_PASSWORD)
        }

        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                error = if (s.length < MIN_PASSWORD_LENGTH)
                    context.getString(R.string.password_error_message)
                else
                    null
            }

        })
    }


}