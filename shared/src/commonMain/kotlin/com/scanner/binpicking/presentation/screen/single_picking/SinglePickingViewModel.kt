package com.scanner.binpicking.presentation.screen.single_picking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.scanner.binpicking.core.Resource
import com.scanner.binpicking.core.SingleDataState
import com.scanner.binpicking.data.services.SinglePickingService
import com.scanner.binpicking.domain.mode.PickingModel
import com.scanner.binpicking.domain.mode.singlepicking.SinglePickingModel
import com.scanner.binpicking.domain.usecase.CreateOrderUseCase
import com.scanner.binpicking.domain.usecase.MissingProductUseCase
import com.scanner.binpicking.domain.usecase.PreviousProductUseCase
import com.scanner.binpicking.domain.usecase.ScanProductUseCase
import com.scanner.binpicking.domain.usecase.SkipProductUseCase
import com.scanner.binpicking.domain.usecase.UpdateStatusUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SinglePickingViewModel(singlePicking: SinglePickingModel) : ViewModel(), KoinComponent {

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var errorMsg by mutableStateOf("")
    var gatherQty by mutableStateOf(singlePicking.gatherQty.toInt())
    var missingQty by mutableStateOf(0)


    private val scanProductUseCase: ScanProductUseCase by inject()
    private val updateStatusUseCase: UpdateStatusUseCase by inject()
    private val missingProductUseCase: MissingProductUseCase by inject()
    private val skipProductUseCase: SkipProductUseCase by inject()
    private val previousProductUseCase: PreviousProductUseCase by inject()

    private val _scannedProductDetail = MutableStateFlow(SingleDataState())
    private val _updateStatus = MutableStateFlow(SingleDataState())
    private val _missingProductDetail = MutableStateFlow(SingleDataState())
    private val _skipProductDetail = MutableStateFlow(SingleDataState())
    private val _previousProductDetail = MutableStateFlow(SingleDataState())


    var singlePickingModel by mutableStateOf(singlePicking)
    val scannedProductDetail: StateFlow<SingleDataState> = _scannedProductDetail
    val updateStatus: StateFlow<SingleDataState> = _updateStatus
    val missingProductDetail: StateFlow<SingleDataState> = _missingProductDetail
    val skipProductDetail: StateFlow<SingleDataState> = _skipProductDetail
    val previousProductDetail: StateFlow<SingleDataState> = _previousProductDetail


    fun scanOrder(sku: String, id: Int, requested_qty: Int, picker: Int) {

        scanProductUseCase.invoke(sku, id, requested_qty, picker).onEach {
            when (it) {
                is Resource.Loading -> {
                    _scannedProductDetail.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _scannedProductDetail.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _scannedProductDetail.value =
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

    fun updateStatus(id: List<Int>,entityId: Int, sku: String, unStoredGatherUiQty: Int, picker: Int) {

        updateStatusUseCase.invoke(id, entityId, sku, unStoredGatherUiQty, picker).onEach {
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

    fun missingProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        unStoredMissingCount: Int,
        storedMissingCount: Int,
        productQty: Int,
        sku: String,
        id: Int,
        productId: Int,
        picker: Int
    ) {

        missingProductUseCase.invoke(
            unStoredGatherUiQty,
            storedGatherQty,
            unStoredMissingCount,
            storedMissingCount,
            productQty,
            sku,
            id,
            productId,
            picker
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    _missingProductDetail.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _missingProductDetail.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _missingProductDetail.value =
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

    fun skipProduct(
        unStoredGatherUiQty: Int,
        storedGatherQty: Int,
        sku: String,
        picker: Int,
        id: Int,
        parentId: Int
    ) {

        skipProductUseCase.invoke(unStoredGatherUiQty, storedGatherQty, sku, picker, id, parentId)
            .onEach {
                when (it) {
                    is Resource.Loading -> {
                        _skipProductDetail.value =
                            SingleDataState(
                                isLoading = true,
                                data = "Not Found",
                                status = it.status
                            )
                    }

                    is Resource.Success -> {
                        _skipProductDetail.value =
                            SingleDataState(isLoading = false, data = it.data, status = it.status)
                    }

                    is Resource.Error -> {
                        if (!it.status.isSuccess()) {
                            _skipProductDetail.value =
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

    fun previousProduct(id: Int, parentId: Int) {

        previousProductUseCase.invoke(id, parentId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _previousProductDetail.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _previousProductDetail.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _previousProductDetail.value =
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