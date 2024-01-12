package com.scanner.binpicking.resoures.icons

import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(BtnMinus,BtnPlus, Error,Hidepassword, Interval, Logobiglight, Logosmall, Minus, Plus, Showpassword, BtnSkip, BtnPrevious, Scanner, SelectedDone, Dropdown, Filter, Keyboard, Signout, Copy)
    return __AllIcons!!
  }
