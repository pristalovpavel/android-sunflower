/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.adapters.swipetodelete

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.google.samples.apps.sunflower.adapters.GardenPlantingAdapter
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings
import com.google.samples.apps.sunflower.utilities.InjectorUtils

class SwipeToDeleteTouchHelperCallback(private val rv: RecyclerView, dragDirs: Int, swipeDirs: Int)
    : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    override fun onMove(rv: RecyclerView, vh1: RecyclerView.ViewHolder, vh2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(vh: RecyclerView.ViewHolder, direction: Int) {
        if (vh is GardenPlantingAdapter.ViewHolder) {
            val item = vh.itemView.tag
            if (item is PlantAndGardenPlantings) {
                for (gardenPlanting in item.gardenPlantings) {
                    InjectorUtils.provideGardenPlantingRepository(rv.context)
                        .removeGardenPlanting(gardenPlanting)
                    rv.adapter?.notifyItemRemoved(vh.adapterPosition)
                }
            }
        }
    }
}