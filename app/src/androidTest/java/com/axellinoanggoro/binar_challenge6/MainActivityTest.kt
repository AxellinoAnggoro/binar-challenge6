package com.axellinoanggoro.binar_challenge6

import android.content.Intent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.axellinoanggoro.binar_challenge6.view.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationToRegisterActivity() {
        // Wait for 3 seconds
        Thread.sleep(3000)

        // Check if the RegisterActivity is started
        val scenario = activityScenarioRule.scenario
        scenario.onActivity { activity ->
            val intent = activity.intent
            val expectedIntent = Intent(activity, RegisterActivity::class.java)
            assert(intent.component == expectedIntent.component)
        }
    }
}