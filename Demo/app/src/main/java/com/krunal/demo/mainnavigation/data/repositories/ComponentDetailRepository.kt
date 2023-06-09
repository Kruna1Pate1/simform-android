package com.krunal.demo.mainnavigation.data.repositories

import com.krunal.demo.appcomponents.ui.activities.FirstActivity
import com.krunal.demo.appcomponents.ui.activities.ImagePickerActivity
import com.krunal.demo.mainnavigation.data.models.ComponentDetail
import com.krunal.demo.navigation.ui.activities.TriviaGameActivity
import com.krunal.demo.recyclerview.ChattingFragment
import com.krunal.demo.recyclerview.YoutubeFragment
import com.krunal.demo.searchwebview.ui.activities.SearchWebActivity
import com.krunal.demo.stackexchange.StackExchangeActivity
import com.krunal.demo.uicomponents.AppBarFragment
import com.krunal.demo.uicomponents.ButtonFragment
import com.krunal.demo.uicomponents.CheckboxFragment
import com.krunal.demo.uicomponents.ChipFragment
import com.krunal.demo.uicomponents.CoordinatorLayoutFragment
import com.krunal.demo.uicomponents.CustomViewFragment
import com.krunal.demo.uicomponents.EditTextFragment
import com.krunal.demo.uicomponents.FabFragment
import com.krunal.demo.uicomponents.FrameLayoutFragment
import com.krunal.demo.uicomponents.LinearLayoutFragment
import com.krunal.demo.uicomponents.ListViewFragment
import com.krunal.demo.uicomponents.RadioFragment
import com.krunal.demo.uicomponents.RelativeLayoutFragment
import com.krunal.demo.uicomponents.SliderFragment
import com.krunal.demo.uicomponents.SnackBarFragment
import com.krunal.demo.uicomponents.SpanFragment
import com.krunal.demo.uicomponents.SpinnerFragment
import com.krunal.demo.uicomponents.TabLayoutFragment
import com.krunal.demo.uicomponents.ThemeFragment
import com.krunal.demo.uicomponents.ToastFragment
import com.krunal.demo.uicomponents.constraintLayouts.ChainBiasFragment
import com.krunal.demo.uicomponents.constraintLayouts.CircularFragment
import com.krunal.demo.uicomponents.constraintLayouts.GuidelineBarrierFragment
import com.krunal.demo.uicomponents.constraintLayouts.RelativeFragment
import com.krunal.demo.uicomponents.picker.DatePickerFragment
import com.krunal.demo.uicomponents.picker.TimePickerFragment
import com.krunal.demo.webservices.gsonokhttp.ui.fragments.UserListFragment
import com.krunal.demo.webservices.gsonokhttp.ui.fragments.UserRegisterFragment
import com.krunal.demo.webservices.withoutlibrary.ui.fragments.NewsListFragment

object ComponentDetailRepository {

    private val componentDetails: List<ComponentDetail> by lazy {
        listOf(
            ComponentDetail.FragmentComponent(
                "Change Theme", ThemeFragment::class.java
            ), ComponentDetail.NavigatorComponent(
                "UI Widgets", uiWidgets
            ), ComponentDetail.NavigatorComponent(
                "UI Layouts", layouts
            ), ComponentDetail.ActivityComponent(
                "Stock Exchange", StackExchangeActivity::class.java
            ), ComponentDetail.ActivityComponent(
                "Trivia Game", TriviaGameActivity::class.java
            ), ComponentDetail.NavigatorComponent(
                "Recycler View", recyclerView
            ), ComponentDetail.NavigatorComponent(
                "App Component", appComponents
            ), ComponentDetail.ActivityComponent(
                "Search and Web view", SearchWebActivity::class.java
            ), ComponentDetail.FragmentComponent(
                "News List", NewsListFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "User List", UserListFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Register User", UserRegisterFragment::class.java
            )
        )
    }

    private val uiWidgets: List<ComponentDetail> by lazy {
        listOf(
            ComponentDetail.FragmentComponent(
                "App Bar", AppBarFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Button", ButtonFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Check Box", CheckboxFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Chip", ChipFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Custom View", CustomViewFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Date Picker", DatePickerFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Time Picker", TimePickerFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Edit Text", EditTextFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Floating Action Button", FabFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "List View", ListViewFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Radio Button", RadioFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Slider", SliderFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Snack Bar", SnackBarFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Span Text", SpanFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Spinner", SpinnerFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Toast", ToastFragment::class.java
            )
        )
    }

    private val layouts: List<ComponentDetail> by lazy {
        listOf(
            ComponentDetail.FragmentComponent(
                "Coordinator Layout", CoordinatorLayoutFragment::class.java
            ), ComponentDetail.NavigatorComponent(
                "Constraint Layout", constraintLayouts
            ), ComponentDetail.FragmentComponent(
                "Frame Layout", FrameLayoutFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Linear Layout", LinearLayoutFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Relative Layout", RelativeLayoutFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Tab Layout", TabLayoutFragment::class.java
            )
        )
    }

    private val constraintLayouts: List<ComponentDetail> by lazy {
        listOf(
            ComponentDetail.FragmentComponent(
                "Chain Bias", ChainBiasFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Circular", CircularFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Guideline Barrier", GuidelineBarrierFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Relative", RelativeFragment::class.java
            )
        )
    }

    private val recyclerView: List<ComponentDetail> by lazy {
        listOf(
            ComponentDetail.FragmentComponent(
                "Youtube", YoutubeFragment::class.java
            ), ComponentDetail.FragmentComponent(
                "Chatting", ChattingFragment::class.java
            )
            // TODO: Add KT screens
        )
    }

    private val appComponents: List<ComponentDetail> by lazy {
        listOf(
            ComponentDetail.ActivityComponent(
                "Data Passing", FirstActivity::class.java
            ), ComponentDetail.ActivityComponent(
                "Image Picker", ImagePickerActivity::class.java
            )
        )
    }

    fun getHomeComponents(): List<ComponentDetail> = componentDetails
}