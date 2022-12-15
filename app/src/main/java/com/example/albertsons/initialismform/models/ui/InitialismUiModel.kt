package com.example.albertsons.initialismform.models.ui

import com.example.albertsons.initialismform.models.domain.Var

sealed class InitialismUiModel {
    data class InitialismItem(val sf: String) : InitialismUiModel()
    data class InitialismSubItem(
        val lf: String,
        val since: Long,
        val vars: List<Var>
    ) : InitialismUiModel()

    enum class InitialismViewType { ITEM, SUB_ITEM }
}