package com.app.gastranetwork.data.preference.repository

enum class UserAccess(var id: Int,var access_name: String) {

    MARKETING_SALE(1, "marketing_sale"),
    TEKNISI(2, "teknisi"),
    CUSTOMER(3, "customer"),
    OWNER(4, "owner")

}