package com.krunal.demo.stackexchange

import android.graphics.Color
import android.os.Bundle
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityStackExchangeBinding
import com.krunal.demo.stackexchange.exchange.ExchangeFragment
import com.krunal.demo.stackexchange.market.MarketFragment
import com.krunal.demo.stackexchange.wallet.WalletFragment
import com.krunal.demo.uicomponents.extentions.getThemeColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class StackExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStackExchangeBinding
    private val viewModel: StackExchangeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStackExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        setupUI()
        setupBottomNavigation()
    }

    private fun setupUI() {

        setupAppBar()

        binding.apply {
            fabExchange.setOnClickListener {
                bottomNavigation.selectedItemId = R.id.actionExchange
            }
        }
    }

    @androidx.annotation.OptIn(ExperimentalBadgeUtils::class)
    private fun setupAppBar() {
        setSupportActionBar(binding.toolbar)
        val badge = BadgeDrawable.create(this).apply {
            badgeTextColor = getThemeColor(com.google.android.material.R.attr.colorOnSecondary)
            backgroundColor = getThemeColor(com.google.android.material.R.attr.colorSecondary)
        }

        binding.notificationView.apply {
            clipToOutline = false
            bringToFront()
            viewTreeObserver.addOnGlobalLayoutListener {
                BadgeUtils.attachBadgeDrawable(
                    badge,
                    binding.notificationView
                )
            }
        }

        lifecycleScope.launch {
            viewModel.notificationCount.collectLatest {
                if (it == 0) {
                    badge.isVisible = false
                } else {
                    badge.number = it
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        changeFragment(MarketFragment())

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.actionMarket -> MarketFragment()
                R.id.actionExchange -> ExchangeFragment()
                else -> WalletFragment()
            }
            changeFragment(fragment)
            return@setOnItemSelectedListener true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}