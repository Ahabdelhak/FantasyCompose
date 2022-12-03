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



package com.ah_abdelhak.fantasycompose.ui.posters

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.ah_abdelhak.fantasycompose.R
import com.ah_abdelhak.fantasycompose.model.Poster
import com.ah_abdelhak.fantasycompose.ui.main.MainViewModel
import com.ah_abdelhak.fantasycompose.ui.theme.purple200


@Composable
fun Posters(
  viewModel: MainViewModel,
  selectPoster: (Long) -> Unit
) {
  val posters: List<Poster> by viewModel.posterList.collectAsState(initial = listOf())
  val isLoading: Boolean by viewModel.isLoading
  val selectedTab = FantasyHomeTab.getTabFromResource(viewModel.selectedTab.value)
  val tabs = FantasyHomeTab.values()

  ConstraintLayout {
    val (body, progress) = createRefs()
    Scaffold(
      backgroundColor = MaterialTheme.colors.primarySurface,
      topBar = { PosterAppBar() },
      modifier = Modifier.constrainAs(body) {
        top.linkTo(parent.top)
      },
      bottomBar = {
        BottomNavigation(
          backgroundColor = purple200,
          modifier = Modifier
            .navigationBarsHeight(56.dp)
        ) {
          tabs.forEach { tab ->
            BottomNavigationItem(
              icon = { Icon(imageVector = tab.icon, contentDescription = null) },
              label = { Text(text = stringResource(tab.title), color = Color.White) },
              selected = tab == selectedTab,
              onClick = { viewModel.selectTab(tab.title) },
              selectedContentColor = LocalContentColor.current,
              unselectedContentColor = LocalContentColor.current,
              modifier = Modifier.navigationBarsPadding()
            )
          }
        }
      }
    ) { innerPadding ->
      val modifier = Modifier.padding(innerPadding)
      Crossfade(selectedTab) { destination ->
        when (destination) {
          FantasyHomeTab.HOME -> HomePosters(modifier, posters, selectPoster)
          FantasyHomeTab.RADIO -> RadioPosters(modifier, posters, selectPoster)
          FantasyHomeTab.LIBRARY -> LibraryPosters(modifier, posters, selectPoster)
        }
      }
    }
    if (isLoading) {
      CircularProgressIndicator(
        modifier = Modifier
          .constrainAs(progress) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          }
      )
    }
  }
}

@Preview
@Composable
private fun PosterAppBar() {
  TopAppBar(
    elevation = 6.dp,
    backgroundColor = purple200,
    modifier = Modifier.height(58.dp)
  ) {
    Text(
      modifier = Modifier
        .padding(8.dp)
        .align(Alignment.CenterVertically),
      text = stringResource(R.string.app_name),
      color = Color.White,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold
    )
  }
}

enum class FantasyHomeTab(
  @StringRes val title: Int,
  val icon: ImageVector
) {
  HOME(R.string.menu_home, Icons.Filled.Home),
  RADIO(R.string.menu_radio, Icons.Filled.Radio),
  LIBRARY(R.string.menu_library, Icons.Filled.LibraryAdd);

  companion object {
    fun getTabFromResource(@StringRes resource: Int): FantasyHomeTab {
      return when (resource) {
        R.string.menu_radio -> RADIO
        R.string.menu_library -> LIBRARY
        else -> HOME
      }
    }
  }
}
