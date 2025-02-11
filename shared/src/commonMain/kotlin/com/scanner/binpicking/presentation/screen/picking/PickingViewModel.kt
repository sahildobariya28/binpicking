package com.scanner.binpicking.presentation.screen.picking

import MessageBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import com.arkivanov.decompose.ComponentContext
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.data.CommonErrorModel
import com.scanner.binpicking.data.DefaultErrorModel
import com.scanner.binpicking.presentation.screen.picking.data.model.LowQtyDataUpdate
import com.scanner.binpicking.presentation.screen.picking.data.model.PickingModelWrapper
import com.scanner.binpicking.presentation.screen.picking.data.model.UpdateStatusRes
import com.scanner.binpicking.presentation.screen.picking.data.repository.PickingRepository
import com.scanner.binpicking.util.ActionType
import com.scanner.binpicking.util.SoundMediaPlayer
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PickingViewModel(
    val context: ComponentContext,
    val dataModel: PickingModelWrapper,
    val onBackPressed: () -> Unit,
    val onNavigateToSingInScreen: () -> Unit,
) : ViewModel() {

    lateinit var keyboardController: SoftwareKeyboardController
    lateinit var focusRequester: FocusRequester
    lateinit var focusManager: FocusManager

    var isLoading by mutableStateOf(false)
    var productScannerText by mutableStateOf(TextFieldValue(""))
    var uiGatherQty by mutableStateOf(0)
    var uiMissingQty by mutableStateOf(0)

    var pickingModel by mutableStateOf(dataModel)
    lateinit var messageBarState: MessageBarState

    fun scanOrder(picker: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
//                LogErrorRepository.logError("call scanOrder   (${AppConfig.getPickerId()})   :::" + pickingModel.toString())
                PickingRepository.scanProduct(
                    pickingModel.data.sku,
                    pickingModel.data.entityId.toInt(),
                    (pickingModel.data.gatherQty + uiGatherQty),
                    picker
                )
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        messageBarState.addError(exception = Exception(it.errorResponse.toString()))
//                        LogErrorRepository.logError("error scanOrder   (${AppConfig.getPickerId()})   :::" + pickingModel.toString() + it.errorResponse)
                        isLoading = false
                        onNavigateToSingInScreen()

                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->

                            isLoading = false
                            playSuccessSound()
                            try {
                                if ((data as PickingModelWrapper).isSuccess) {
//                                    LogErrorRepository.logError("success scanOrder   (${AppConfig.getPickerId()})   :::" + data )
                                    if (data.proccessFinish) {
                                        messageBarState.addSuccess(message = "Order Completed successfully!")

                                        onBackPressed()
                                    } else if (data.changeProduct && !data.proccessFinish) {
                                        pickingModel = data
                                        uiGatherQty = 0
                                        uiMissingQty = 0
                                    } else if (!data.changeProduct && !data.proccessFinish) {
                                        val newPickingModel = pickingModel
                                        newPickingModel.data.ordertotalqty = data.data.ordertotalqty
                                        newPickingModel.data.ordergetqty = data.data.ordergetqty
                                        newPickingModel.data.missing = data.data.missing
                                        newPickingModel.data.credit = data.data.credit
                                        newPickingModel.data.gatherQty = data.data.gatherQty

                                        pickingModel = newPickingModel
                                        uiGatherQty = 0
                                        uiMissingQty = 0
                                    } else {
                                        messageBarState.addError(exception = Exception("Something Want Wrong!"))
                                    }
                                } else {
                                    messageBarState.addError(exception = Exception("Something Want Wrong!"))
                                }
                            } catch (e: Exception) {
                                if(data is CommonErrorModel){
                                    messageBarState.addError(exception = Exception(data.message))
                                }else if (data is DefaultErrorModel){
                                    messageBarState.addError(exception = Exception(data.message))
                                }

                            }
                        }
                    }
                }
            }.onFailure {
//                LogErrorRepository.logError("error scanOrder   (${AppConfig.getPickerId()})   :::" + pickingModel.toString() + it.message)
                isLoading = false
                playErrorSound()
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun updateStatus(picker: Int = 0) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
//                LogErrorRepository.logError("call updateStatus ${AppConfig.getPickerId()}  ::: " + pickingModel.toString())
                isLoading = true
                PickingRepository.updateStatus(
                    listOf(pickingModel.data.mainRefEntityId.toInt()),
                    pickingModel.data.entityId.toInt(),
                    pickingModel.data.sku,
                    uiGatherQty,
                    picker
                )
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        messageBarState.addError(exception = Exception(it.errorResponse.toString()))
//                        LogErrorRepository.logError("error updateStatus ${AppConfig.getPickerId()}  :::"+pickingModel.toString() + it.responseStatus)
                        isLoading = false
                        onNavigateToSingInScreen()
                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->
//                            LogErrorRepository.logError("success updateStatus ${AppConfig.getPickerId()}  " + data)
                            isLoading = false
                            try {
                                if ((data as UpdateStatusRes).isSuccess) {
                                    onBackPressed()
                                } else {
                                    messageBarState.addError(exception = Exception("Something Want Wrong!"))

                                }
                            } catch (e: Exception) {
                                when (data) {
                                    is CommonErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    is DefaultErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    else -> { messageBarState.addError(exception = Exception(e.message)) }
                                }
                            }
                        }
                    }
                }
            }.onFailure {
//                LogErrorRepository.logError("error updateStatus ${AppConfig.getPickerId()}  :::"+pickingModel.toString() + it.message)
                isLoading = false
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun missingProduct(pickerId: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
//                LogErrorRepository.logError("call missingProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString())
                isLoading = true
                PickingRepository.missingProduct(
                    uiGatherQty,
                    pickingModel.data.gatherQty,
                    uiMissingQty,
                    pickingModel.data.missing,
                    pickingModel.data.qty.toInt(),
                    pickingModel.data.sku,
                    pickingModel.data.entityId.toInt(),
                    pickingModel.data.refEntityId.toInt(),
                    pickerId
                )
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        messageBarState.addError(exception = Exception(it.errorResponse.toString()))
//                        LogErrorRepository.logError("error missingProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + "  :::  " + it)
                        isLoading = false
                        onNavigateToSingInScreen()
                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->

//                            LogErrorRepository.logError("success missingProduct ${AppConfig.getPickerId()}  :::" + data)
                            isLoading = false

                            playSuccessSound()
                            try {
                                if ((data as PickingModelWrapper).isSuccess) {
                                    if (data.proccessFinish) {
                                        onBackPressed()
                                        messageBarState.addSuccess(message = "Order finished successfully!")
                                    } else if (data.changeProduct && !data.proccessFinish) {
                                        pickingModel = data
                                        this@PickingViewModel.uiGatherQty = 0
                                        this@PickingViewModel.uiMissingQty = 0
                                    } else if (!data.changeProduct && !data.proccessFinish) {
                                        val newPickingModel = pickingModel
                                        newPickingModel.data.ordertotalqty = data.data.ordertotalqty
                                        newPickingModel.data.ordergetqty = data.data.ordergetqty
                                        newPickingModel.data.missing = data.data.missing
                                        newPickingModel.data.credit = data.data.credit
                                        newPickingModel.data.gatherQty = data.data.gatherQty

                                        pickingModel = newPickingModel
                                        this@PickingViewModel.uiGatherQty = 0
                                        this@PickingViewModel.uiMissingQty = 0
                                    } else {
                                        messageBarState.addError(exception = Exception("Something Want Wrong!"))
                                    }
                                } else {
                                    messageBarState.addError(exception = Exception("Something Want Wrong!"))
                                }
                            } catch (e: Exception) {
                                when (data) {
                                    is CommonErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    is DefaultErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    else -> { messageBarState.addError(exception = Exception(e.message)) }
                                }
                            }
                        }
                    }
                }


            }.onFailure {
//                LogErrorRepository.logError("error missingProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + it.message)
                isLoading = false
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun skipProduct(picker: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
//                LogErrorRepository.logError("call skipProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString())

                isLoading = true
                PickingRepository.skipProduct(
                    uiGatherQty,
                    pickingModel.data.gatherQty,
                    pickingModel.data.sku,
                    picker,
                    pickingModel.data.entityId.toInt(),
                    pickingModel.data.mainRefEntityId.toInt()
                )
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        messageBarState.addError(exception = Exception(it.errorResponse.toString()))
//                        LogErrorRepository.logError("error skipProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + "  :::  " + it)
                        isLoading = false

                        onNavigateToSingInScreen()
                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->
                            isLoading = false
                            try {
                                if ((data as PickingModelWrapper).isSuccess) {
//                                    LogErrorRepository.logError("success skipProduct ${AppConfig.getPickerId()}  :::" + data)
                                    playSuccessSound()
                                    if (data.isSuccess) {
                                        pickingModel = data
                                        this@PickingViewModel.uiGatherQty = 0
                                        uiMissingQty = 0
                                    } else {
                                        isLoading = false
                                        messageBarState.addError(exception = Exception("No more products further."))
                                    }
                                } else {
                                    messageBarState.addError(exception = Exception("Something Want Wrong!"))
                                }
                            } catch (e: Exception) {
                                when (data) {
                                    is CommonErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    is DefaultErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    else -> { messageBarState.addError(exception = Exception(e.message)) }
                                }
                            }
                        }
                    }
                }
            }.onFailure {
//                LogErrorRepository.logError("error skipProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString() +"  :::  "+ it.message)
                isLoading = false
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun previousProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
//                LogErrorRepository.logError("call previousProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString())
                isLoading = true
                PickingRepository.previousProduct(pickingModel.data.entityId.toInt(), pickingModel.data.mainRefEntityId.toInt())
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        messageBarState.addError(exception = Exception(it.errorResponse.toString()))
//                        LogErrorRepository.logError("error previousProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + "  :::  " + it)
                        isLoading = false
                        onNavigateToSingInScreen()
                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->
//                            LogErrorRepository.logError("success previousProduct ${AppConfig.getPickerId()}  :::" + data)
                            isLoading = false

                            try {
                                if ((data as PickingModelWrapper).isSuccess) {
                                    playSuccessSound()
                                    if (data.isSuccess) {

                                        pickingModel = data
                                        uiGatherQty = 0
                                        uiMissingQty = 0
                                    } else {
                                        messageBarState.addError(exception = Exception("No more products further."))
                                    }
                                } else {
                                    messageBarState.addError(exception = Exception("Something Want Wrong"))
                                }
                            } catch (e: Exception) {
                                when (data) {
                                    is CommonErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    is DefaultErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    else -> { messageBarState.addError(exception = Exception(e.message)) }
                                }
                            }
                        }
                    }
                }
            }.onFailure {
//                LogErrorRepository.logError("error previousProduct ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + it.message)
                isLoading = false
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun lowQtyDataUpdate() {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
//                LogErrorRepository.logError("call lowQtyDataUpdate ${AppConfig.getPickerId()}  :::" + pickingModel.toString())
                isLoading = true
                PickingRepository.lowQtyDataUpdate(pickingModel.data.sku)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        messageBarState.addError(exception = Exception(it.errorResponse.toString()))
//                        LogErrorRepository.logError("error lowQtyUpdate ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + "  :::  " + it)
                        isLoading = false

                        onNavigateToSingInScreen()
                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->

//                            LogErrorRepository.logError("success lowQtyDataUpdate ${AppConfig.getPickerId()}  :::" + data)
                            isLoading = false
                            try {
                                if ((data as LowQtyDataUpdate).success) {
                                    messageBarState.addSuccess(message = data.message)
                                } else {
                                    messageBarState.addSuccess(message = data.message)
                                }

                            } catch (e: Exception) {
                                when (data) {
                                    is CommonErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    is DefaultErrorModel -> { messageBarState.addError(exception = Exception(data.message)) }
                                    else -> { messageBarState.addError(exception = Exception(e.message)) }
                                }
                            }
                        }
                    }
                }
            }.onFailure {
//                LogErrorRepository.logError("error lowQtyDataUpdate  ${AppConfig.getPickerId()}  :::" + pickingModel.toString() + it.message)
                isLoading = false
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun showKeyboard() {
        if (isKeyboardControllerInitialization() && isFocusRequesterControllerInitialization() && isFocusManagerControllerInitialization()) {
            focusManager.clearFocus()
            focusRequester.requestFocus()
            keyboardController.show()
        }
    }

    fun hideKeyboard() {
        focusManager.clearFocus()
        keyboardController.hide()
    }

    fun isKeyboardControllerInitialization(): Boolean {
        return ::keyboardController.isInitialized
    }

    fun isFocusRequesterControllerInitialization(): Boolean {
        return ::focusRequester.isInitialized
    }

    fun isFocusManagerControllerInitialization(): Boolean {
        return ::focusManager.isInitialized
    }

    fun playSuccessSound() {
        CoroutineScope(Dispatchers.Main).launch {
            SoundMediaPlayer().playSound(ActionType.SUCCESS)
        }
    }

    fun playErrorSound() {
        CoroutineScope(Dispatchers.Main).launch {
            SoundMediaPlayer().playSound(ActionType.ERROR)
        }
    }
}