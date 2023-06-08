package com.krunal.demo.mainnavigation.data.models

import android.app.Activity
import androidx.fragment.app.Fragment

sealed interface ComponentDetail {
    val name: String

    data class ActivityComponent(
        override val name: String, val clazz: Class<out Activity>
    ) : ComponentDetail

    data class FragmentComponent(
        override val name: String, val clazz: Class<out Fragment>
    ) : ComponentDetail

    data class NavigatorComponent(
        override val name: String, val components: List<ComponentDetail>
    ) : ComponentDetail
}