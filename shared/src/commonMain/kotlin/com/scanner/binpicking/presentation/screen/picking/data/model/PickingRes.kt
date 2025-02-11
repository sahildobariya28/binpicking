package com.scanner.binpicking.presentation.screen.picking.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PickingModelWrapper(
    @SerialName("success")
    var isSuccess: Boolean,
    @SerialName("change_product")
    var changeProduct: Boolean = false,
    @SerialName("proccess_finish")
    var proccessFinish: Boolean = false,
    @SerialName("data")
    var data: Data = Data(),
)

@Serializable
data class Data(
    @SerialName("credit")
    var credit: Int = 0,
    @SerialName("entity_id")
    var entityId: String = "",
    @SerialName("gather_qty")
    var gatherQty: Int = 0,
    @SerialName("image_url")
    var imageUrl: String = "",
    @SerialName("location")
    var location: String = "",
    @SerialName("main_ref_entity_id")
    var mainRefEntityId: String = "",
    @SerialName("missing")
    var missing: Int = 0,
    @SerialName("order_number")
    var orderNumber: String = "",
    @SerialName("ordergetqty")
    var ordergetqty: Int = 0,
    @SerialName("ordertotalqty")
    var ordertotalqty: Int = 0,
    @SerialName("qty")
    var qty: String = "",
    @SerialName("ref_entity_id")
    var refEntityId: String = "",
    @SerialName("shipping")
    var shipping: String = "",
    @SerialName("sku")
    var sku: String = "",
    @SerialName("total_qty")
    var totalQty: Int = 0,
)


//@Serializable
//data class Data(
//    @SerialName("color_code")
//    val colorCode: String = "",
//    @SerialName("created_at")
//    val createdAt: String = "",
//    @SerialName("credit")
//    var credit: Int = 0,
//    @SerialName("entity_id")
//    val entityId: String = "",
//    @SerialName("gather_qty")
//    var gatherQty: Int = 0,
//    @SerialName("image_url")
//    val imageUrl: String = "",
//    @SerialName("in_missing")
//    val inMissing: String = "",
//    @SerialName("location")
//    val location: String = "",
//    @SerialName("main_ref_entity_id")
//    val mainRefEntityId: String = "",
//    @SerialName("missing")
//    var missing: Int = 0,
//    @SerialName("missing_picker")
//    val missingPicker: String = "",
//    @SerialName("order_number")
//    val orderNumber: String = "",
//    @SerialName("ordergetqty")
//    var ordergetqty: Int = 0,
//    @SerialName("order_pick_qty")
//    val orderPickQty: Int = 0,
//    @SerialName("orderpckng")
//    val orderpckng: String = "No",
//    @SerialName("ordertotalqty")
//    var ordertotalqty: Int = 0,
//    @SerialName("picker")
//    val picker: String = "",
//    @SerialName("picking")
//    val picking: String = "",
//    @SerialName("pickup_status")
//    val pickupStatus: String = "",
//    @SerialName("product_name")
//    val productName: String = "",
//    @SerialName("qty")
//    var qty: String = "",
//    @SerialName("reason")
//    val reason: String = "",
//    @SerialName("ref_entity_id")
//    val refEntityId: String = "",
//    @SerialName("scan_type")
//    val scanType: String = "",
//    @SerialName("shipping")
//    val shipping: String = "",
//    @SerialName("sku")
//    val sku: String = "",
//    @SerialName("total_missing")
//    val totalMissing: String = "",
//    @SerialName("total_qty")
//    val totalQty: Int = 0,
//    @SerialName("updated_at")
//    val updatedAt: String = ""
//)
