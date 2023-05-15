package com.krunal.demo.uicomponents.cardscreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.google.android.material.navigation.NavigationBarView
import com.krunal.demo.R
import com.krunal.demo.UIComponentsActivity
import com.krunal.demo.databinding.CardLayoutBinding
import com.krunal.demo.databinding.FragmentCardBinding
import com.krunal.demo.uicomponents.dialogs.MyDatePickerDialog
import com.krunal.demo.uicomponents.extentions.dpFormat
import com.krunal.demo.uicomponents.models.CardDetail
import com.krunal.demo.uicomponents.sheets.OperationsBottomSheetFragment
import kotlinx.coroutines.launch

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val viewModel: CardFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupBottomBar()
        (activity as? UIComponentsActivity)?.supportActionBar?.apply {
            title = getString(R.string.my_cards)
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#2F7CEF")))
            elevation = 0f
            // TODO: Make title center
        }

        setupCardChips()

        setupCard()

        binding.cardOperations.btnDetails.setOnClickListener {
            OperationsBottomSheetFragment().show(childFragmentManager, null)
        }

        binding.cardOperations.btnCalendar.setOnClickListener {
            MyDatePickerDialog().show(childFragmentManager, null)
        }
    }

    private fun setupCardChips() {
        viewModel.dummyCardDetails.forEach { card ->
            (layoutInflater.inflate(R.layout.card_chip, null) as? Chip)?.apply {
                text = getString(R.string.card_name, card.type, card.number)
                isChecked = card.isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    viewModel.updateCardSelection(
                        card,
                        isChecked
                    )
                }
            }.also { cardChip ->
                binding.cgCard.addView(cardChip)
            }
        }
    }

    private fun setupCard() {
        addSelectedCards(viewModel.dummyCardDetails)
        lifecycleScope.launch {
            viewModel.selectedCards.collect { cardList ->
                binding.llCards.removeViews(1, binding.llCards.childCount - 1)
                addSelectedCards(cardList)
            }
        }
    }

    private fun addSelectedCards(cardList: List<CardDetail>) {
        cardList
            .filter { it.isSelected }
            .forEach { card ->
                val cardBinding = CardLayoutBinding.inflate(layoutInflater)
                cardBinding.card = card

                val layoutParams = LinearLayout.LayoutParams(
                    300.dpFormat(requireContext()),
                    200.dpFormat(requireContext()),
                ).apply {
                    marginEnd = 18.dpFormat(requireContext())
                }

                cardBinding.root.layoutParams = layoutParams
                binding.llCards.addView(cardBinding.root)
            }
    }

    private fun setupBottomBar() {
        binding.bottomNavigation.apply {
            selectedItemId = menu.getItem(1).itemId
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_SELECTED
        }
    }
}