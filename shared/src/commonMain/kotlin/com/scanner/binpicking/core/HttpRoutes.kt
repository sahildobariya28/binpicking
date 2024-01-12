package com.scanner.binpicking.core

object HttpRoutes {

    private const val BASE_URL = "https://delta.phonelcdparts.com/"
    const val BAREAR_TOKEN = "nsrtv97lcraxdfru33glpf05z3u9z9ii"

    //data url
    const val LOGIN = "${BASE_URL}rest/V1/integration/admin/token"
    const val PICKER_ID = "${BASE_URL}rest/V1/swp_picktrack/getadminidbytoken/"
    const val SCAN_PRODUCT = "${BASE_URL}rest/V1/swp_picktrack/singleorderscan/"
    const val MISSING_PRODUCT = "${BASE_URL}rest/V1/swp_picktrack/singleordermissing/"
    const val SKIP_PRODUCT = "${BASE_URL}rest/V1/swp_picktrack/orderskip/"
    const val PREVIOUS_PRODUCT = "${BASE_URL}rest/V1/swp_picktrack/orderprevious/"


    const val CREATE_ORDER = "${BASE_URL}rest/V1/swp_picktrack/singleordercreate/"
    const val GET_ORDER_LIST = "${BASE_URL}rest/V1/swp_picktrack/orderlist/"
    const val GET_ORDER_ITEM_LIST = "${BASE_URL}rest/V1/swp_picktrack/listorderitem"
    const val CONTINUE_PICK = "${BASE_URL}rest/V1/swp_picktrack/OrderContinueEdit/"
    const val DELETE_ORDER = "${BASE_URL}rest/V1/swp_picktrack/orderdelete/"
    const val UPDATE_STATUS = "${BASE_URL}rest/V1/swp_picktrack/updateorder/"

}