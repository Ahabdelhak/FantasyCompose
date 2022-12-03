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



package com.ah_abdelhak.fantasycompose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ah_abdelhak.fantasycompose.model.Poster

@Database(entities = [Poster::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

  abstract fun posterDao(): PosterDao
}
