package com.scanner.binpicking.di

import com.scanner.binpicking.data.repository.LoginRepository
import com.scanner.binpicking.data.repository.OrderItemListRepository
import com.scanner.binpicking.data.repository.OrderListRepository
import com.scanner.binpicking.data.repository.SinglePickingRepository
import com.scanner.binpicking.data.services.LoginService
import com.scanner.binpicking.data.services.OrderItemListService
import com.scanner.binpicking.data.services.OrderListService
import com.scanner.binpicking.data.services.SinglePickingService
import com.scanner.binpicking.domain.usecase.ContinuePickUseCase
import com.scanner.binpicking.domain.usecase.CreateOrderUseCase
import com.scanner.binpicking.domain.usecase.DeleteOrderUseCase
import com.scanner.binpicking.domain.usecase.GetOrderItemListUseCase
import com.scanner.binpicking.domain.usecase.GetOrderListUseCase
import com.scanner.binpicking.domain.usecase.GetPickerIdUseCase
import com.scanner.binpicking.domain.usecase.LoginUseCase
import com.scanner.binpicking.domain.usecase.MissingProductUseCase
import com.scanner.binpicking.domain.usecase.PreviousProductUseCase
import com.scanner.binpicking.domain.usecase.ScanProductUseCase
import com.scanner.binpicking.domain.usecase.SkipProductUseCase
import com.scanner.binpicking.domain.usecase.UpdateStatusUseCase
import com.scanner.binpicking.utils.SoundMediaPlayer
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin() {
    startKoin {

        modules(
            platformUtilModule,
            loginModule,
            SinglePickingModule,
            OrderModule,
            OrderItemModule,
        )
    }.koin
}
val platformUtilModule = module {
    single { SoundMediaPlayer() }
}

//login
val loginModule: Module = module {
    single<LoginService> { LoginRepository() } bind LoginService::class
    single { LoginUseCase(get()) }
    single { GetPickerIdUseCase(get()) }
}

//singlePicking
val SinglePickingModule: Module = module {
    single<SinglePickingService> { SinglePickingRepository() } bind SinglePickingService::class
    single { CreateOrderUseCase(get()) }
    single { ScanProductUseCase(get()) }
    single { MissingProductUseCase(get()) }
    single { SkipProductUseCase(get()) }
    single { PreviousProductUseCase(get()) }
    single { UpdateStatusUseCase(get()) }
}

//singlePicking
val OrderModule: Module = module {
    single<OrderListService> { OrderListRepository() } bind OrderListService::class
    single { GetOrderListUseCase(get()) }
    single { ContinuePickUseCase(get()) }
    single { DeleteOrderUseCase(get()) }
}

//singlePicking
val OrderItemModule: Module = module {
    single<OrderItemListService> { OrderItemListRepository() } bind OrderItemListService::class
    single { GetOrderItemListUseCase(get()) }
}