package com.scanner.binpicking.domain.mode.singlepicking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SinglePickingModel(

    @SerialName("success")
    var isSuccess: Boolean = false,
    @SerialName("error")
    var isError: Boolean = false,
    @SerialName("errorMsg")
    var errorMsg: String = "",
    @SerialName("change_product")
    var isChangeProduct: Int = -1,
    @SerialName("color_code")
    var colorCode: String = "",
    @SerialName("created_at")
    var createdAt: String = "",
    @SerialName("credit")
    var credit: String = "",
    @SerialName("entity_id")
    var entityId: String = "",
    @SerialName("gather_qty")
    var gatherQty: String = "",
    @SerialName("image_url")
    var imageUrl: String = "",
    @SerialName("in_missing")
    var inMissing: String = "",
    @SerialName("location")
    var location: String = "",
    @SerialName("main_ref_entity_id")
    var mainRefEntityId: String = "",
    @SerialName("missing")
    var missing: String = "",
    @SerialName("missing_picker")
    var missingPicker: String = "",
    @SerialName("order_number")
    var orderNumber: String = "",
    @SerialName("ordergetqty")
    var ordergetqty: Int = 0,
    @SerialName("ordertotalqty")
    var ordertotalqty: Int = 0,
    @SerialName("picker")
    var picker: String = "",
    @SerialName("picking")
    var picking: String = "",
    @SerialName("pickup_status")
    var pickupStatus: String = "",
    @SerialName("product_name")
    var productName: String = "",
    @SerialName("qty")
    var qty: String = "",
    @SerialName("reason")
    var reason: String = "",
    @SerialName("ref_entity_id")
    var refEntityId: String = "",
    @SerialName("shipping")
    var shipping: String = "",
    @SerialName("sku")
    var sku: String = "",
    @SerialName("total_missing")
    var totalMissing: String = "",
    @SerialName("total_qty")
    var totalQty: Int = 0,
    @SerialName("updated_at")
    var updatedAt: String = "",
    @SerialName("proccess_finish")
    var proccessFinish: Boolean = false
)

//  order create kare tyare
//[
//    {
//        "success": true,
//        "data": {
//            "entity_id": "4187",
//            "order_number": "313613",
//            "picking": "313613",
//            "location": "2-B3-3C",
//            "qty": "3",
//            "sku": "IP11-QV7-V2",
//            "product_name": "iPhone 11 LCD Assembly w/ Steel Plate (INCELL / QV7 / Version 2) (Exclusive LCD Breakage Warranty)",
//            "pickup_status": "0",
//            "credit": "0",
//            "missing": "0",
//            "gather_qty": "0",
//            "ref_entity_id": "288",
//            "created_at": "2023-12-04 04:16:00",
//            "updated_at": "2023-12-04 04:16:00",
//            "main_ref_entity_id": "284",
//            "picker": "",
//            "reason": "",
//            "color_code": "",
//            "missing_picker": "",
//            "in_missing": "0",
//            "total_missing": "0",
//            "image_url": "https://delta.phonelcdparts.com/media/catalog/product/cache/b417cf1c51d2fc922dcaa740afabb2ea/i/p/ip11-q7v-v2.jpg",
//            "total_qty": 1040,
//            "shipping": "  UPS Ground",
//            "ordertotalqty": 6,
//            "ordergetqty": 0
//        }
//    }
//]
//
//
//product pick complete thay and new product ave tyare
//[
//    {
//        "success": true,
//        "change_product": true,
//        "data": {
//            "entity_id": "4164",
//            "order_number": "313613",
//            "picking": "313613",
//            "location": "1-O3-5B",
//            "qty": "3",
//            "sku": "XR-HCB",
//            "product_name": "POWERCELL PRO X iPhone XR High Capacity Replacement Battery (3610 mAh)",
//            "pickup_status": "0",
//            "credit": "0",
//            "missing": "0",
//            "gather_qty": "0",
//            "ref_entity_id": "276",
//            "created_at": "2023-12-04 01:15:51",
//            "updated_at": "2023-12-04 01:15:51",
//            "main_ref_entity_id": "272",
//            "picker": "",
//            "reason": "",
//            "color_code": "",
//            "missing_picker": "",
//            "in_missing": "0",
//            "total_missing": "0",
//            "total_qty": 369,
//            "image_url": "https://delta.phonelcdparts.com/static/version1701668485/webapi_rest/_view/en_US/Magento_Catalog/images/product/placeholder/.jpg",
//            "order_pick_qty": 0,
//            "shipping": "1_ups_ground",
//            "ordertotalqty": 6,
//            "ordergetqty": 1,
//            "orderpckng": "No"
//        }
//    }
//]
//
//product qty update thay tyare
//[
//    {
//        "success": true,
//        "change_product": false,
//        "data": {
//            "ordertotalqty": 6,
//            "ordergetqty": 0,
//            "order_pick_qty": 2,
//            "missing": 2,
//            "credit": "0",
//            "gather_qty": "0"
//        }
//    }
//]
//product pick thay jay tyare
//[
//    {
//        "success": true,
//        "proccess_finish": true
//    }
//]