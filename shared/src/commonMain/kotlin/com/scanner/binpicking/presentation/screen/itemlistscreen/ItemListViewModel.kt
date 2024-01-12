package com.scanner.binpicking.presentation.screen.itemlistscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.core.SingleDataState
import com.scanner.binpicking.domain.mode.ItemListModelList
import com.scanner.binpicking.domain.mode.ItemModel
import com.scanner.binpicking.domain.mode.Order
import com.scanner.binpicking.domain.usecase.GetOrderItemListUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ItemListViewModel(entityId: String) : ViewModel(), KoinComponent {

    var isLoading by mutableStateOf(false)
    private val getOrderListUseCase: GetOrderItemListUseCase by inject()
    private var _orderList = MutableStateFlow(SingleDataState())
    var orderList: StateFlow<SingleDataState> = _orderList

    val orderItemList = mutableStateListOf<ItemModel>()

    init {
        getOrderItemList(entityId)
    }

    fun getOrderItemList(entityId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            getOrderListUseCase.invoke(entityId = entityId).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _orderList.value =
                            SingleDataState(
                                isLoading = true,
                                data = "Not Found",
                                status = it.status
                            )
                    }

                    is Resource.Success -> {
                        _orderList.value =
                            SingleDataState(isLoading = false, data = it.data, status = it.status)
                    }

                    is Resource.Error -> {
                        if (!it.status.isSuccess()) {
                            _orderList.value =
                                SingleDataState(
                                    isLoading = false,
                                    data = "Not Found",
                                    status = it.status
                                )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}