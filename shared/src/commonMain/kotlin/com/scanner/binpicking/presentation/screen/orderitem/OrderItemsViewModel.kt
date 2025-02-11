package com.scanner.binpicking.presentation.screen.orderitem

import MessageBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.core.DataState
import com.scanner.binpicking.data.CommonErrorModel
import com.scanner.binpicking.data.DefaultErrorModel
import com.scanner.binpicking.presentation.screen.orderitem.data.model.OrderItemsRes
import com.scanner.binpicking.presentation.screen.orderitem.data.model.ItemsModel
import com.scanner.binpicking.presentation.screen.orderitem.data.repository.OrderItemsRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrderItemsViewModel(
    val entityId: String,
    val pickerName: String,
    val onBackPressed: () -> Unit,
    val onNavigateToSingInScreen: () -> Unit,
) : ViewModel() {
    lateinit var messageBarState: MessageBarState

    var isLoading by mutableStateOf(false)

    val orderItemList = mutableStateListOf<ItemsModel>()
    val orderList = MutableStateFlow(DataState())

    fun getOrderItemList(entityId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
                OrderItemsRepository.getOrderItemList(entityId)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->
                            isLoading = false
                            try {
                                if ((data as OrderItemsRes).isSuccess) {
                                    orderItemList.clear()
                                    orderItemList.addAll(data.data)
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

                    DataConfiguration.OnError -> {
                        isLoading = false

                        onNavigateToSingInScreen()
                        messageBarState.addError(exception = Exception("${it.errorResponse}"))
                    }
                }

            }.onFailure {
                isLoading = false
                messageBarState.addError(exception = Exception(it.message.toString()))
            }
        }
    }
}