package com.krunal.demo.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.krunal.demo.databinding.FragmentChattingBinding
import com.krunal.demo.recyclerview.adapters.ChatAdapter
import com.krunal.demo.recyclerview.models.Message
import com.krunal.demo.recyclerview.viewmodels.ChattingFragmentViewModel
import kotlinx.coroutines.launch

class ChattingFragment : Fragment() {

    private lateinit var binding: FragmentChattingBinding
    private val viewModel: ChattingFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChattingBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val chatAdapter = ChatAdapter()
        chatAdapter.submitList(Message.dummyData)

        binding.rvChat.adapter = chatAdapter
        binding.rvChat.setHasFixedSize(true)
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
        }
        binding.rvChat.addItemDecoration(object : ItemDecoration() {
            override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                val rect = Rect()
                parent.children.forEach { view ->

                    parent.getDecoratedBoundsWithMargins(view, rect)
                    val path = Path()
                    val tv = (view as ViewGroup).children.elementAt(1)
                    val rectF = RectF(rect.left + tv.left.toFloat(),rect.top.toFloat(), rect.right - tv.right.toFloat(), rect.bottom.toFloat())
                    path.apply {
                        moveTo(rectF.left + 40F, rectF.bottom)
                        lineTo(rectF.right, rectF.bottom)
                        lineTo(rectF.right, rectF.top)
                        lineTo(rectF.left + 60F, rectF.top)
                        lineTo(rectF.left + 40F, rectF.bottom)
                        close()
                    }

                    canvas.drawPath(path, Paint().apply {
                        color = Color.CYAN
                        style = Paint.Style.STROKE
                        strokeWidth = 5F
                    })
                }
            }

            override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                canvas.drawCircle(30f, 30f, 20f, Paint().apply {
                    color = Color.BLUE
                })
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.messageFlow.collect { message ->
                    chatAdapter.addMessage(message)
                }
            }
        }
    }
}