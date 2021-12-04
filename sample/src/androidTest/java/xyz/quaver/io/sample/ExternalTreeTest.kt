package xyz.quaver.io.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import xyz.quaver.io.FileX
import xyz.quaver.io.util.deleteRecursively

const val TEST_PACKAGE = "xyz.quaver.io.sample"
const val TEST_FOLDER = ".documentfilex-test"
const val LAUNCH_TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
class ExternalTreeTest {
    private lateinit var rootUri: Uri

    @Before
    fun initTest() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(TEST_PACKAGE)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        rootUri = when (Build.VERSION.SDK_INT) {
            21 -> obtainSdCardPermissionSDK21()
            else -> error("SDK not supported")
        }
    }

    @Test
    fun create_directory() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val root = FileX(context, rootUri)

        Generic.create_directory(context, root)
    }

    @After
    fun cleanup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val root = FileX(context, rootUri)

        root.deleteRecursively()
    }
}