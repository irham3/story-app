package org.irham3.storyapp.data

import org.irham3.storyapp.data.local.SessionManager
import javax.inject.Inject

class UserRepository @Inject constructor(private val session: SessionManager) {
    fun registerUser(name: String, email: String, password: String) {
        session.saveToPreference(SessionManager.KEY_NAME, name)
        session.saveToPreference(SessionManager.KEY_EMAIL, email)
        session.saveToPreference(SessionManager.KEY_PASSWORD, password)
        session.createLoginSession()
    }

    fun loginUser(email: String, password: String) {
        val sessionEmail = session.getFromPreference(SessionManager.KEY_EMAIL)
        val sessionPassword = session.getFromPreference(SessionManager.KEY_PASSWORD)

        if (email == sessionEmail && password == sessionPassword)
            session.createLoginSession()
    }
}