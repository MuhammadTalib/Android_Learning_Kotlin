<<<<<<< HEAD:UserLocation/app/src/androidTest/java/com/example/userlocation/ExampleInstrumentedTest.kt
package com.example.userlocation
=======
package com.example.gpslocation
>>>>>>> 393132deab666430eecd58300cd295143a848fb9:GPSLocation/app/src/androidTest/java/com/example/gpslocation/ExampleInstrumentedTest.kt

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
<<<<<<< HEAD:UserLocation/app/src/androidTest/java/com/example/userlocation/ExampleInstrumentedTest.kt
        assertEquals("com.example.userlocation", appContext.packageName)
=======
        assertEquals("com.example.gpslocation", appContext.packageName)
>>>>>>> 393132deab666430eecd58300cd295143a848fb9:GPSLocation/app/src/androidTest/java/com/example/gpslocation/ExampleInstrumentedTest.kt
    }
}
