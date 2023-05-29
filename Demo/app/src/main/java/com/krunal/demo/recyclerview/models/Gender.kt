package com.krunal.demo.recyclerview.models

import java.util.Locale

enum class Gender {

    MALE, FEMALE, OTHER;

    val titleName: String =
        name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
