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
package com.ah_abdelhak.fantasycompose.ui.main

import androidx.annotation.WorkerThread
import com.ah_abdelhak.fantasycompose.model.Poster
import com.ah_abdelhak.fantasycompose.network.FantasyService
import com.ah_abdelhak.fantasycompose.persistence.PosterDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val fantasyService: FantasyService,
  private val posterDao: PosterDao
) {

  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadFantasyPosters(
    onStart: () -> Unit,
    onCompletion: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val posters: List<Poster> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network call asynchronously.
      fantasyService.fetchFantasyPosterList()
        // handle the case when the API request gets a success response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        // handle the case when the API request is fails.
        // e.g. internal server error.
        .onFailure { onError(message()) }
    } else {
      emit(posters)
    }
  }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}
