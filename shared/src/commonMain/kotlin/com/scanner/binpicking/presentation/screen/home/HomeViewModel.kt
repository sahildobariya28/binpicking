package com.scanner.binpicking.presentation.screen.home

import MessageBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.arkivanov.decompose.ComponentContext
import com.scanner.binpicking.core.DataConfiguration
import com.scanner.binpicking.data.CommonErrorModel
import com.scanner.binpicking.data.DefaultErrorModel
import com.scanner.binpicking.presentation.screen.home.data.model.DeleteResponse
import com.scanner.binpicking.presentation.screen.home.data.model.Order
import com.scanner.binpicking.presentation.screen.home.data.model.OrderList
import com.scanner.binpicking.presentation.screen.home.data.model.UpdateResponse
import com.scanner.binpicking.presentation.screen.home.data.repository.HomeRepository
import com.scanner.binpicking.presentation.screen.picking.data.model.PickingModelWrapper
import com.scanner.binpicking.util.ActionType
import com.scanner.binpicking.util.SoundMediaPlayer
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    componentContext: ComponentContext,
    val onNavigateToPicking: (pickingModel: PickingModelWrapper) -> Unit,
    val onNavigateToSingInScreen: () -> Unit,
    val onNavigateToItemListScreen: (entityId: String, pickerName: String) -> Unit
) : ViewModel() {

    lateinit var messageBarState: MessageBarState
//    lateinit var snackHostState: StackedSnackBarHostState

    //orderlist
    var isLoading by mutableStateOf(false)


    var orderList by mutableStateOf(OrderList(success = false))

    //filter
    var noOfOrder by mutableStateOf("")
    var pickingStatus by mutableStateOf("")
    var pickingEmployee by mutableStateOf("")
    var filterByTime by mutableStateOf("")

    var isSignOutDialog by mutableStateOf(false)
    var isPickingDialog by mutableStateOf(false)
    var orderNoCheck by mutableStateOf("")
    var isPickingConformDialog by mutableStateOf(false)
    var isSelectionMode by mutableStateOf(false)
    var isRefreshing by mutableStateOf(false)

    var selectedOrderList = mutableStateListOf<Order>()
    var searchText by mutableStateOf(TextFieldValue(""))

    var currentPage = 1
    val pageSize = 10
    var isMoreDataRequest by mutableStateOf(true)
    var itemFinish by mutableStateOf(false)

    var isFilterSlider by mutableStateOf(false)
    var lastFilter by mutableStateOf("")


    fun createOrder(orderNumber: String, picker: String) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
                HomeRepository.createOrder(orderNumber, picker)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        isLoading = false
                        playErrorSound()

                        onNavigateToSingInScreen()
//                        snackHostState.showErrorSnackbar(
//                            title = "${it.errorResponse}",
//                            duration = if (it.responseStatus == HttpStatusCode.Unauthorized) StackedSnackbarDuration.Indefinite else StackedSnackbarDuration.Short,
//                            actionTitle = if (it.responseStatus == HttpStatusCode.Unauthorized) "Re Login" else null,
//                            action = if (it.responseStatus == HttpStatusCode.Unauthorized) {
//                                {
//                                    onNavigateToSingInScreen()
//                                }
//                            } else null
//                        )
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
                                    playSuccessSound()
                                    refreshOrderList()
                                    onNavigateToPicking(data)
//                                    snackHostState.showSuccessSnackbar(
//                                        title = "Order created successfully.",
//                                        duration = StackedSnackbarDuration.Short
//                                    )
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
                playErrorSound()
                isLoading = false
                messageBarState.addError(exception = Exception("${it.message}"))

            }
        }
    }

    fun continuePicking(orderNumber: String) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
                HomeRepository.continuePicking(orderNumber)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
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
                                    onNavigateToPicking(data)
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
                isLoading = false
                messageBarState.addError(exception = Exception(it.message))
            }
        }
    }

    fun updateStatus(ids: List<Int>) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
                HomeRepository.updateStatus(id = ids)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {
                        isLoading = false
                        onNavigateToSingInScreen()
//                        snackHostState.showErrorSnackbar(
//                            title = "${it.errorResponse}",
//                            duration = if (it.responseStatus == HttpStatusCode.Unauthorized) StackedSnackbarDuration.Indefinite else StackedSnackbarDuration.Short,
//                            actionTitle = if (it.responseStatus == HttpStatusCode.Unauthorized) "Re Login" else null,
//                            action = if (it.responseStatus == HttpStatusCode.Unauthorized) {
//                                {
//                                    onNavigateToSingInScreen()
//                                }
//                            } else null
//                        )
                    }

                    DataConfiguration.OnIdle -> TODO()
                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->
                            isLoading = false

                            try {
                                if ((data as UpdateResponse).isSuccess) {
                                    refreshOrderList()
                                    messageBarState.addSuccess(message = "Status changed successfully.")
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
                isLoading = false
                messageBarState.addError(exception = Exception("${it.message}"))
            }
        }
    }

    fun deleteOrder(selectedOrderEntityId: List<Int>) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
                HomeRepository.deleteOrder(selectedOrderEntityId)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnIdle -> {
                        isLoading = false
                    }

                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        isLoading = false
                        it.successResponse?.let { data ->
                            try {
                                if ((data as DeleteResponse).isSuccess) {
                                    refreshOrderList()
                                    messageBarState.addSuccess(message = "Order deleted successfully.")
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

                    DataConfiguration.OnError -> {
                        isLoading = false
                        messageBarState.addError(exception = Exception("${it.errorResponse}"))
                    }
                }
            }.onFailure {
                isLoading = false
                messageBarState.addError(exception = Exception("${it.message}"))
            }
        }
    }

    fun getOrderList(pageSize: Int, currentPage: Int, filter: String = "") {
        CoroutineScope(Dispatchers.Main).launch {
            kotlin.runCatching {
                isLoading = true
                HomeRepository.getOrder(pageSize, currentPage, filter)
            }.onSuccess {
                when (it.state) {
                    DataConfiguration.OnError -> {

                        isLoading = false
                        messageBarState.addError(Exception("${it.errorResponse}"))
//                        snackHostState.showErrorSnackbar(
//                            title = "${it.errorResponse}",
//                            duration = if (it.responseStatus == HttpStatusCode.Unauthorized) StackedSnackbarDuration.Indefinite else StackedSnackbarDuration.Short,
//                            actionTitle = if (it.responseStatus == HttpStatusCode.Unauthorized) "Re Login" else null,
//                            action = if (it.responseStatus == HttpStatusCode.Unauthorized) {
//                                {
//                                    onNavigateToSingInScreen()
//                                }
//                            } else null
//                        )
                    }

                    DataConfiguration.OnIdle -> TODO()

                    DataConfiguration.OnLoading -> {
                        isLoading = true
                    }

                    DataConfiguration.OnSuccess -> {
                        it.successResponse?.let { data ->
                            isLoading = false

                            try {
                                if ((data as OrderList).success) {
                                    val arrayList = ArrayList<Order>()
                                    arrayList.addAll(orderList.data)
                                    arrayList.addAll(data.data)

                                    orderList.data = arrayList

                                    isSelectionMode = false
                                    selectedOrderList.clear()

                                    if (data.data.isEmpty() || data.data.size < pageSize) {
                                        itemFinish = true
                                    } else {
                                        itemFinish = false
                                        isMoreDataRequest = false
                                    }
                                } else {
                                    messageBarState.addError(exception = Exception("Something Want Wrong"))
                                }
                            }catch (e: Exception){
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
                isLoading = false
                messageBarState.addError(exception = Exception("${it.message}"))
            }
        }
    }

    fun orderListWithApplyFilter(filter: String = "") {
        lastFilter = filter
        orderList.let {
            val orderListSize = it.data.size
            getOrderList(
                if (orderListSize > pageSize) orderListSize else pageSize,
                1,
                lastFilter
            )
            it.data = listOf()
        }
    }

    fun refreshOrderList() {
        orderList.let {
            val orderListSize = it.data.size
            getOrderList(
                if (orderListSize > pageSize) orderListSize else pageSize,
                1,
                lastFilter
            )
            it.data = listOf()
        }
    }

    fun loadMoreProducts(isSuccess: Boolean) {
        if (isSuccess) {
            currentPage++
            getOrderList(pageSize, currentPage, lastFilter)
            isMoreDataRequest = true
        }
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