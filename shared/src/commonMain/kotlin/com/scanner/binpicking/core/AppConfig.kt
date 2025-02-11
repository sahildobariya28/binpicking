package com.scanner.binpicking.core

import com.scanner.binpicking.applicationContext


object AppConfig {
    const val PRODUCTION_BASE_URL = "https://www.phonelcdparts.com/"
    const val DELTA_BASE_URL = "https://delta.phonelcdparts.com/"
    const val LOCAL_BASE_URL = "http://192.168.29.236/phonelcdparts/pub/"

    var currentBaseUrl: String = PRODUCTION_BASE_URL // Change this for different environments

    fun getPickerId(): String? = applicationContext?.getString("picker_id")
    fun getToken(): String = if (!applicationContext?.getString("login_token").isNullOrEmpty()) applicationContext?.getString("login_token")!! else ""
    fun isAdministrator(): Boolean =
        if (!applicationContext?.getString("picker_role").isNullOrEmpty()) {
            applicationContext?.getString("picker_role").equals("Administrators")
        } else {
            false
        }
}

sealed class Endpoint(val path: String) {
    data object Login : Endpoint("rest/V1/integration/admin/token")
    data object PickerId : Endpoint("rest/V1/swp_picktrack/getadminidbytoken/")
    data object ScanProduct : Endpoint("rest/V1/swp_picktrack/singleorderscan/")
    data object MissingProduct : Endpoint("rest/V1/swp_picktrack/singleordermissing/")
    data object SkipProduct : Endpoint("rest/V1/swp_picktrack/orderskip/")
    data object PreviousProduct : Endpoint("rest/V1/swp_picktrack/orderprevious/")
    data object LowQtyDataUpdate : Endpoint("rest/V1/swp_picktrack/stockrequest/")
    data object CreateOrder : Endpoint("rest/V1/swp_picktrack/singleordercreate/")
    data object GetOrderList : Endpoint("rest/V1/swp_picktrack/orderlist/")
    data object GetOrderItemList : Endpoint("rest/V1/swp_picktrack/listorderitem")
    data object ContinuePick : Endpoint("rest/V1/swp_picktrack/OrderContinueEdit/")
    data object DeleteOrder : Endpoint("rest/V1/swp_picktrack/orderdelete/")
    data object MoveToPending : Endpoint("rest/V1/swp_picktrack/updateorder/")
    data object LogError : Endpoint("rest/V1/swp_picktrack/logpick")

    fun getUrl(): String = AppConfig.currentBaseUrl + path
}


//Delta:
//username: swp-binpicking
//password: Sahil@123

//Delta: (Admin)
//user: BHAVINPLCD
//pass: bhavin@123


// TestAccount (Live)

//username: AJAY
//password: WadMtQzuG5dldAt

//username: JOHN
//password: Fcmbft99

//username: JENUL
//password: JENUL2022

//username: JENUL
//password: SoftAdminMage2022

//~~ FTP CREDENTIALS ~~
//host: gpc114-dev1.us-midwest-1.nxcli.net
//un: swp@dev.plcdparts.com
//pw: FloraSopCroneTine67 / PleaseHoaryHaulsRub96
//port: 21
//path: "/delta.phonelcdparts.com/html/var/log"


//crashlog (111)
//"${BUILD_DIR%/Build/*}/SourcePackages/checkouts/firebase-ios-sdk/Crashlytics/run"
//for install build only
//based on dependency analysis only
//show enviroment variable in build log

//crashlog (222)
//"${BUILD_DIR%/Build/*}/SourcePackages/checkouts/firebase-ios-sdk/Crashlytics/run"
//for install build only
//show enviroment variable in build log

//crashlog (333)
//"${PODS_ROOT}/FirebaseCrashlytics/run"
//for install build only
//based on dependency analysis only
//show enviroment variable in build log

//crashlog (444)
//"${PODS_ROOT}/FirebaseCrashlytics/run"
//for install build only
//show enviroment variable in build log

//crashlog (555)
//"${BUILD_DIR%/Build/*}/SourcePackages/checkouts/firebase-ios-sdk/Crashlytics/run"
//"${PODS_ROOT}/FirebaseCrashlytics/run"
//for install build only
//based on dependency analysis only
//show enviroment variable in build log
//
// crashlog (666)
//"${BUILD_DIR%/Build/*}/SourcePackages/checkouts/firebase-ios-sdk/Crashlytics/run"
//"${PODS_ROOT}/FirebaseCrashlytics/run"
//for install build only
//show enviroment variable in build log

//crashlog (777)
//"${PODS_ROOT}/FirebaseCrashlytics/run"
//based on dependency analysis only
//show enviroment variable in build log

//test order no
//613081