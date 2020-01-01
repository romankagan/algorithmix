/*
 * Copyright 2019 Nazmul Idris. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stacksqueues

import com.importre.crayon.brightCyan
import com.importre.crayon.brightWhite
import com.importre.crayon.green
import com.importre.crayon.red
import utils.heading
import java.util.*


fun main(args: Array<String>) {
  println("Stacks & Queues".heading())
  val root = makeSampleFolders()
  val found = dfs("jdk11", root)
  println("\njdk11 found: $found")
}

/*
Create a tree of folders that need to be searched.

    root
      + opt
        + chrome
      + apps
        + idea
        + androidstudio
      + dev
        + java
          + jdk8
          + jdk11

*/
fun makeSampleFolders(): Folder {
  val root = Folder("root")

  val opt = Folder("opt", root)
  val apps = Folder("apps", root)
  val dev = Folder("dev", root)

  val apps_idea = Folder("idea", apps)
  val apps_as = Folder("androidstudio", apps)

  val opt_chrome = Folder("chrome", opt)

  val dev_java = Folder("java", dev)
  val dev_java_jdk8 = Folder("jdk8", dev_java)
  val dev_java_jdk11 = Folder("jdk11", dev_java)

  return root
}


fun dfs(name: String, root: Folder): Boolean {
  val stack = ArrayDeque<Folder>()
  stack.push(root)
  var found = false
  while (stack.isNotEmpty()) {
    println("\n...while loop start... ".brightWhite() + "stack=$stack".brightCyan())
    val currentFolder = stack.pop()
    println("👆️️popped: " + currentFolder.toDetailedString().red())
    if (currentFolder.isNamed(name)) {
      found = true
      println("\tfound a matching folder")
    }
    for (f in currentFolder.subFolders) {
      stack.push(f)
      println("👇️push: " + f.toDetailedString().green())
    }
  }
  return found

}

class Folder {
  val name: String

  private var _subFolders: MutableList<Folder> = mutableListOf()
  val subFolders: MutableList<Folder>
    get() = Collections.unmodifiableList(_subFolders)

  fun toDetailedString(): String {
    return "{name: $name, subFolders: ${subFolders.size}}"
  }

  override fun toString(): String {
    return name
  }

  fun isNamed(nameArg: String): Boolean {
    return name == nameArg
  }

  constructor(name: String) {
    this.name = name
  }

  constructor(name: String, root: Folder) {
    this.name = name
    root.addSubfolder(this)
  }

  fun addSubfolders(vararg folders: Folder) {
    folders.forEach { addSubfolder(it) }
  }

  fun addSubfolder(f: Folder) {
    if (!_subFolders.contains(f)) {
      _subFolders.add(f)
    }
  }

  fun hasSubfolders(): Boolean {
    return !_subFolders.isEmpty()
  }

}