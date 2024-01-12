package com.scanner.binpicking.presentation.screen.multi_picking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.scanner.binpicking.domain.mode.PickingModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class MultiPickingViewModel() : ViewModel(){

    var scannerInput by mutableStateOf("")
    var reason by mutableStateOf("")
    var creditCount by mutableStateOf(0)
    var missingCount by mutableStateOf(0)

    var singlePickingModel = mutableStateListOf(emptyList<PickingModel>())

}