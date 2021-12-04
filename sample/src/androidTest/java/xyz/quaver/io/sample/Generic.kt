package xyz.quaver.io.sample

import android.content.Context
import xyz.quaver.io.FileX
import xyz.quaver.io.util.getChild

class Generic {

    companion object {
        fun create_directory(context: Context, root: FileX) {
            assert(!root.getChild("testFolder").exists())
            assert(root.getChild("testFolder").mkdir())

            val testFolder = FileX(context, root, "testFolder")
            assert(testFolder.exists())

            assert(testFolder.getChild("testFile1").createNewFile())
            assert(testFolder.getChild("testFile2").createNewFile())
            assert(testFolder.getChild("testFile3").createNewFile())

            val fileList = testFolder.list()
            if (fileList != null) {
                assert(3 == fileList.size)
            } else assert(false)
        }
    }
}