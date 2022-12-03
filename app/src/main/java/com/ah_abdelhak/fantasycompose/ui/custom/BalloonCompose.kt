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



package com.ah_abdelhak.fantasycompose.ui.custom

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.skydoves.balloon.Balloon
import com.skydoves.orchestra.tooltips.BalloonAnchor

@Composable
fun ConstraintLayoutScope.ImageBalloonAnchor(
  reference: ConstrainedLayoutReference,
  modifier: Modifier,
  content: String,
  onClick: (Balloon, View) -> Unit
) {
  val context = LocalContext.current
  val lifecycleOwner = LocalLifecycleOwner.current
  val balloon = remember { BalloonFactory.create(context, content, lifecycleOwner) }

  BalloonAnchor(
    reference = reference,
    modifier = modifier,
    balloon = balloon,
    onAnchorClick = onClick
  )
}
