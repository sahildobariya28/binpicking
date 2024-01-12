package com.scanner.binpicking.presentation.screen.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.scanner.binpicking.applicationContext
import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.core.SingleDataState
import com.scanner.binpicking.domain.mode.MultiPickingData
import com.scanner.binpicking.domain.mode.Order
import com.scanner.binpicking.domain.usecase.ContinuePickUseCase
import com.scanner.binpicking.domain.usecase.CreateOrderUseCase
import com.scanner.binpicking.domain.usecase.DeleteOrderUseCase
import com.scanner.binpicking.domain.usecase.GetOrderListUseCase
import com.scanner.binpicking.domain.usecase.UpdateStatusUseCase
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

class HomeScreenViewModel : ViewModel(), KoinComponent {

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isSignOutDialog by mutableStateOf(false)
    var isSinglePickingDialog by mutableStateOf(false)
    var isSelectionMode by mutableStateOf(false)
    var isRefreshing by mutableStateOf(false)
    var errorMsg by mutableStateOf("")
    var selectedOrderList = mutableStateListOf<Order>()
    var multiOrderList = mutableStateListOf<MultiPickingData>()
    var searchText by mutableStateOf("")


    private val createOrderUseCase: CreateOrderUseCase by inject()
    private val updateStatusUseCase: UpdateStatusUseCase by inject()
    private val continuePickUseCase: ContinuePickUseCase by inject()
    private val deleteOrderUseCase: DeleteOrderUseCase by inject()
    private val getOrderListUseCase: GetOrderListUseCase by inject()

    private var _orderDetail = MutableStateFlow(SingleDataState())
    private val _updateStatus = MutableStateFlow(SingleDataState())
    private var _orderDelete = MutableStateFlow(SingleDataState())
    private var _orderList = MutableStateFlow(SingleDataState())

    var orderDetail: StateFlow<SingleDataState> = _orderDetail
    val updateStatus: StateFlow<SingleDataState> = _updateStatus
    var orderDelete: StateFlow<SingleDataState> = _orderDelete
    var orderList: StateFlow<SingleDataState> = _orderList

    var currentPage = 1
    val pageSize = 50
    var isMoreDataRequest by mutableStateOf(true)
    var itemFinish by mutableStateOf(false)
    val orderItemList = mutableStateListOf<Order>()

    var isFilter by mutableStateOf(false)
    var lastFilter by mutableStateOf("")

    init {
        applicationContext?.getString("login_token")?.let {
            getOrderList(pageSize, currentPage, it, lastFilter)
        }
    }

    fun createOrder(orderNumber: String, picker: String) {
        CoroutineScope(Dispatchers.IO).launch {
            createOrderUseCase.invoke(orderNumber, picker).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _orderDetail.value =
                            SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                    }

                    is Resource.Success -> {
                        _orderDetail.value =
                            SingleDataState(isLoading = false, data = it.data, status = it.status)
                    }

                    is Resource.Error -> {
                        if (!it.status.isSuccess()) {
                            _orderDetail.value =
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

    fun continuePicking(orderNumber: String) {
        continuePickUseCase.invoke(orderNumber).onEach {
            when (it) {
                is Resource.Loading -> {
                    _orderDetail.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _orderDetail.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _orderDetail.value =
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

    fun deleteOrder(selectedOrderEntityId: List<Int>) {
        deleteOrderUseCase.invoke(selectedOrderEntityId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _orderDelete.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _orderDelete.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _orderDelete.value =
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


    private fun getOrderList(pageSize: Int, currentPage: Int, token: String, filter: String = "") {
        CoroutineScope(Dispatchers.IO).launch {
            getOrderListUseCase.invoke(pageSize, currentPage, token, filter).onEach {
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

    fun updateStatus(ids: List<Int>) {

        updateStatusUseCase.invoke(ids).onEach {
            when (it) {
                is Resource.Loading -> {
                    _updateStatus.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _updateStatus.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _updateStatus.value =
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

    fun orderListWithApplyFilter(filter: String = "") {
        lastFilter = filter
        applicationContext?.getString("login_token")?.let {
            val orderListSize = orderItemList.size
            getOrderList(if (orderListSize > pageSize) orderListSize else pageSize, 1, it, lastFilter)
            orderItemList.clear()
        }
    }

    fun refreshOrderList() {
        applicationContext?.getString("login_token")?.let {
            val orderListSize = orderItemList.size
            getOrderList(if (orderListSize > pageSize) orderListSize else pageSize, 1, it, lastFilter)
            orderItemList.clear()
        }
    }

    fun loadMoreProducts(isSuccess: Boolean) {

        if (isSuccess) {
            applicationContext?.getString("login_token")?.let {
                currentPage++

                getOrderList(pageSize, currentPage, it, lastFilter)
                isMoreDataRequest = true
            }
        }
    }

}