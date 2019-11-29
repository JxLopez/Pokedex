package com.jxlopez.pokedex

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.jxlopez.pokedex.ui.activities.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Test
    fun loginError_All_Empty_Error() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(ViewMatchers.withId(R.id.loginAuthenticateButton)).perform(ViewActions.click())
    }

    @Test
    fun loginError_Email_Format_Error() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(ViewMatchers.withId(R.id.loginEmailEditText))
            .perform(ViewActions.typeText("jxlopez@domain"))
        onView(ViewMatchers.withId(R.id.loginPasswordEditText)).perform(ViewActions.typeText("123456"))
        onView(ViewMatchers.withId(R.id.loginAuthenticateButton)).perform(ViewActions.click())
    }

    @Test
    fun loginError_Password_Error() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(ViewMatchers.withId(R.id.loginEmailEditText))
            .perform(ViewActions.typeText("jxlopez@domain.com"))
        onView(ViewMatchers.withId(R.id.loginPasswordEditText)).perform(ViewActions.typeText("1"))
        onView(ViewMatchers.withId(R.id.loginAuthenticateButton)).perform(ViewActions.click())
    }

    @Test
    fun loginSuccess_And_LogOut_Automatically() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(ViewMatchers.withId(R.id.loginEmailEditText))
            .perform(ViewActions.typeText("jxlopez@domain.com"))
        onView(ViewMatchers.withId(R.id.loginPasswordEditText)).perform(ViewActions.typeText("123456"))
        onView(ViewMatchers.withId(R.id.loginAuthenticateButton)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.actionLogOut)).perform(ViewActions.click())
        onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

}