package xyz.quaver.io.sample

import android.content.Context
import xyz.quaver.io.FileX
import xyz.quaver.io.util.deleteRecursively
import xyz.quaver.io.util.getChild

class Generic {

    companion object {
        fun create_directory(context: Context, root: FileX): FileX {
            assert(!root.getChild("testFolder").exists())
            assert(root.getChild("testFolder").mkdir())

            val testFolder = FileX(context, root, "testFolder")
            assert(testFolder.exists())

            return testFolder
        }

        fun createListfiles(root: FileX) {
            val fileNames = listOf("testFile1", "testFile2", "testFile3")

            for (name in fileNames)
                assert(root.getChild(name).createNewFile())

            val fileList = root.listFiles()
            if (fileList != null) {
                assert(fileNames.size == fileList.size)
                var found: Boolean
                for (name in fileNames) {
                    found = false
                    for (file in fileList) {
                        if (file.name.equals(name)) {
                            found = true
                            break
                        }
                    }
                    assert(found)
                }
                assert(fileList[0].delete())
            } else assert(false)

            assert(root.deleteRecursively())
        }
    }
}