package com.scanner.binpicking.domain.mode

data class PickingModel(
    var orderNo: String,
    var shippingMethod: String,
    var isYes: Boolean,
    var totalQty: Int,
    var totalCompletedQty: Int,
    var binNo: String,
    var binColor: Long,
    var productImgUrl: String,
    var pickingTotalQty: Int,
    var pickingCompletedQty: Int,
    var sku: String,
    var stockQty: Int,
    var creditedQty: Int,
    var missingQty: Int,
    var scannedBarCode: String,
)

fun fetchPickingData(): List<PickingModel> {

    return listOf<PickingModel>(
        PickingModel(
            orderNo = "#313543",
            shippingMethod = "UPS Overnight",
            isYes = false,
            totalQty = 15,
            totalCompletedQty = 3,
            binNo = "2-C7-4B",
            binColor = 0xffff0000,
            productImgUrl = "",
            pickingTotalQty = 1,
            pickingCompletedQty = 0,
            sku = "SGS22U-SPBLK",
            stockQty = 41,
            creditedQty = 0,
            missingQty = 0,
            scannedBarCode = ""
        )
    )
}


