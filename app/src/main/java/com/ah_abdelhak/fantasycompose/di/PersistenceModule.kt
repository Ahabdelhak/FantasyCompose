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

package com.ah_abdelhak.fantasycompose.di

import android.app.Application
import androidx.room.Room
import com.ah_abdelhak.fantasycompose.persistence.AppDatabase
import com.ah_abdelhak.fantasycompose.persistence.PosterDao
import com.ah_abdelhak.fantasycompose.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room
      .databaseBuilder(
        application,
        AppDatabase::class.java,
        application.getString(R.string.database)
      )
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun providePosterDao(appDatabase: AppDatabase): PosterDao {
    return appDatabase.posterDao()
  }
}
