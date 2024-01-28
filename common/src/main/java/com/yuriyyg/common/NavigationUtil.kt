package com.yuriyyg.common

import android.net.Uri
import androidx.navigation.NavController

fun NavController.searchListToDetail(itemId: String){
    val uri = Uri.parse("az://search_detail/?itemId={$itemId}")
    this.navigate(uri)
}