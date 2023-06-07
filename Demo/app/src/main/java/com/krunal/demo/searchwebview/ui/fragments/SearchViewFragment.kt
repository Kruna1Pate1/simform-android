package com.krunal.demo.searchwebview.ui.fragments

import android.app.SearchManager
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentSearchViewBinding
import com.krunal.demo.searchwebview.ui.adapters.PackageDetailAdapter
import com.krunal.demo.searchwebview.ui.viewmodels.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewFragment : Fragment() {

    private lateinit var binding: FragmentSearchViewBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var packageDetailAdapter: PackageDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchViewBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupSearchView()
        setupPackageDetails()

        lifecycleScope.launch {
            viewModel.query.collectLatest { query ->
                filterPackageDetails(query)
            }
        }
    }

    private fun setupSearchView() {
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.searchItemID)
        val cursorAdapter = SimpleCursorAdapter(
            requireContext(),
            R.layout.item_search_suggestion,
            null,
            from,
            to,
            SimpleCursorAdapter.NO_SELECTION
        )

        binding.searchView.apply {
            suggestionsAdapter = cursorAdapter

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let(viewModel::searchQuery)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val suggestions = viewModel.suggestions.value
                    val cursor =
                        MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                    newText?.let { text ->
                        suggestions.filter { it.contains(text, true) }
                            .forEachIndexed { index, suggestion ->
                                cursor.addRow(arrayOf(index, suggestion))
                            }
                    }
                    suggestionsAdapter.changeCursor(cursor)
                    newText?.let(viewModel::searchQuery)
                    return true
                }
            })

            setOnSuggestionListener(object : SearchView.OnSuggestionListener {
                override fun onSuggestionSelect(position: Int): Boolean {
                    return false
                }

                override fun onSuggestionClick(position: Int): Boolean {
                    val query = suggestionsAdapter.cursor.getString(1)
                    setQuery(query, false)
                    viewModel.searchQuery(query)
                    return true
                }
            })

            setOnCloseListener {
                viewModel.searchQuery(query.toString())
                true
            }
        }
    }

    private fun filterPackageDetails(query: String) {
        packageDetailAdapter.filter.filter(query)
    }

    private fun setupPackageDetails() {
        packageDetailAdapter = PackageDetailAdapter { packageDetails ->
            val intent =
                activity?.packageManager?.getLaunchIntentForPackage(packageDetails.packageName)

            if (intent == null) {
                Toast.makeText(
                    requireContext(),
                    "can't start ${packageDetails.name} \nno intent for launch",
                    Toast.LENGTH_SHORT
                ).show()
                return@PackageDetailAdapter
            }

            runCatching {
                startActivity(intent)
            }
        }

        binding.rvPackageDetail.apply {
            this.adapter = packageDetailAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.packageDetails.collectLatest { list ->
                    packageDetailAdapter.submitList(list)
                }
            }
        }
    }
}