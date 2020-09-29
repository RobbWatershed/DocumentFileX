/*
 *   ____                                        _   _____ _ _     __  __
 *  |  _ \  ___   ___ _   _ _ __ ___   ___ _ __ | |_|  ___(_) | ___\ \/ /
 *  | | | |/ _ \ / __| | | | '_ ` _ \ / _ \ '_ \| __| |_  | | |/ _ \\  /
 *  | |_| | (_) | (__| |_| | | | | | |  __/ | | | |_|  _| | | |  __//  \
 *  |____/ \___/ \___|\__,_|_| |_| |_|\___|_| |_|\__|_|   |_|_|\___/_/\_\
 *
 *     Copyright 2020 tom5079
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package xyz.quaver.io.util

import android.annotation.SuppressLint
import androidx.annotation.RequiresApi
import xyz.quaver.io.FileX
import xyz.quaver.io.RawFileX
import xyz.quaver.io.SAFileX
import java.io.File

fun FileX.getChild(fileName: String, cached: Boolean = false): FileX =
    FileX(this.context, this, fileName, cached = cached)

@RequiresApi(21)
fun FileX.getNeighbor(fileName: String, cached: Boolean = false): FileX =
    FileX(this.context, this.uri.getNeighborUri(fileName), cached = cached)

@SuppressLint("NewApi")
fun FileX.deleteRecursively(): Boolean =
    when (this) {
        is SAFileX -> this.uri.delete(this.context)
        is RawFileX -> File(this.path). deleteRecursively()
        else -> throw UnsupportedOperationException()
    }