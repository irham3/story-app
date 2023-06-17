package org.irham3.storyapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

class MyPasswordEditText : AppCompatEditText, View.OnTouchListener {
    private lateinit var hidePasswordIcon: Drawable
    private lateinit var showPasswordIcon: Drawable
    private var isPasswordVisible = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP && event.x >= (width - paddingEnd - showPasswordIcon.intrinsicWidth)) {
            togglePasswordVisibility();
            return true;
        }
        return false
    }

    private fun init() {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        hidePasswordIcon = ContextCompat.getDrawable(context, R.drawable.ic_visibility_off_24dp) as Drawable
        showPasswordIcon = ContextCompat.getDrawable(context, R.drawable.ic_visibility_24dp) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                error = if (s.length < MIN_PASSWORD_LENGTH)
                    "Password minimal harus 8 karakter"
                else
                    null
            }

        })
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        updatePasswordToggleIcon()
        inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        setSelection(text!!.length)
    }

    private fun updatePasswordToggleIcon() {
        if (isPasswordVisible) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, hidePasswordIcon, null)
        } else {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, showPasswordIcon, null)
        }
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}