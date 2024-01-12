package com.scanner.binpicking.data.services

import com.scanner.binpicking.domain.mode.ItemListModelList
import com.scanner.binpicking.domain.mode.ItemModel
import com.scanner.binpicking.domain.mode.OrderList
import io.ktor.http.HttpStatusCode

interface OrderItemListService {

    suspend fun getOrderItemList(entityId: String): Pair<ItemListModelList?, HttpStatusCode?>

}