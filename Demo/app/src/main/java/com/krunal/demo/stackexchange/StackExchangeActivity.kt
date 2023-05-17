package com.krunal.demo.stackexchange

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityStackExchangeBinding
import com.krunal.demo.stackexchange.exchange.ExchangeFragment
import com.krunal.demo.stackexchange.market.MarketFragment
import com.krunal.demo.stackexchange.wallet.WalletFragment

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
        binding.apply {
            fabExchange.setOnClickListener {
                bottomNavigation.selectedItemId = R.id.actionExchange
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