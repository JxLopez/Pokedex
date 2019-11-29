package com.jxlopez.pokedex

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.jxlopez.pokedex.ui.activities.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListPokemonActivityTest {

    @Test
    fun processFilter_Item() {
        ActivityScenario.launch(LoginActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.loginEmailEditText))
            .perform(ViewActions.typeText("jxlopez@domain.com"))
        Espresso.onView(ViewMatchers.withId(R.id.loginPasswordEditText)).perform(ViewActions.typeText("123456"))
        Espresso.onView(ViewMatchers.withId(R.id.loginAuthenticateButton)).perform(ViewActions.click())
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.actionSearch)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText("char"))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.actionLogOut)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun processComplete_And_LogOut() {
        ActivityScenario.launch(LoginActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.loginEmailEditText))
            .perform(ViewActions.typeText("jxlopez@domain.com"))
        Espresso.onView(ViewMatchers.withId(R.id.loginPasswordEditText)).perform(ViewActions.typeText("123456"))
        Espresso.onView(ViewMatchers.withId(R.id.loginAuthenticateButton)).perform(ViewActions.click())
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.actionLogOut)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }
}