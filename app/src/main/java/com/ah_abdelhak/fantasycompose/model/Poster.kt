/*
 * Copyright 2022 AHMED ABDELHAK. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ah_abdelhak.fantasycompose.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Poster(
  @PrimaryKey val id: Long,
  val name: String,
  val club: String,
  val bio: String,
  val poster: String,
  val gif: String
) {

  companion object {

    fun mock() = Poster(
      id = 0,
      name = "Mohamed Salah",
      club = "Liverpool",
      bio = "Mohamed Salah is one of the world's best and most prolific forwards and a serial champion with Liverpool.",
      poster = "https://user-images.githubusercontent.com/20733292/205058581-044ee829-58db-4d88-8e2a-142178beba1f.jpeg",
      gif = "https://media.giphy.com/media/mxCK3EcADG1F7krLP6/giphy.gif?cid=ecf05e4701sln9u63lr3z17lh5f3n3h3owrk54zh1183hqmi&rid=giphy.gif&ct=g"
    )
  }
}
