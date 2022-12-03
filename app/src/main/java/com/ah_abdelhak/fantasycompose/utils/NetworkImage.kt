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
package com.ah_abdelhak.fantasycompose.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.ah_abdelhak.fantasycompose.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PaletteLoadedListener
import com.skydoves.landscapist.palette.PalettePlugin

/**
 * A wrapper around [CoilImage] setting a default [contentScale] and showing
 * an indicator when loading poster images.
 *
 * @see CoilImage https://github.com/skydoves/landscapist#coil
 */
@Preview
@Composable
fun NetworkImage(
  @PreviewParameter(NetworkUrlPreviewProvider::class) url: String,
  modifier: Modifier = Modifier,
  circularRevealEnabled: Boolean = false,
  contentScale: ContentScale = ContentScale.Crop,
  paletteLoadedListener: PaletteLoadedListener? = null
) {
  CoilImage(
    imageModel = { url },
    modifier = modifier,
    imageOptions = ImageOptions(contentScale = contentScale),
    component = rememberImageComponent {
      if (circularRevealEnabled) {
        +CircularRevealPlugin()
      }
      if (paletteLoadedListener != null) {
        +PalettePlugin(paletteLoadedListener = paletteLoadedListener)
      }
      +CrossfadePlugin(duration = 350)
    },
    previewPlaceholder = R.drawable.poster,
    failure = {
      Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "image request failed.",
          style = MaterialTheme.typography.body2
        )
      }
    },
  )
}
