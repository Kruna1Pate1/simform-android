package com.krunal.demo.uicomponents

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.textclassifier.TextLinks.TextLink
import android.view.textclassifier.TextLinks.TextLinkSpan
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentSpanBinding
import com.krunal.demo.uicomponents.extentions.addTextView

class SpanFragment : Fragment() {

    private lateinit var binding: FragmentSpanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpans()
    }

    private fun setupSpans() {
        // Normal foreground color span
        val textNormal = binding.tvColorSpan.text
        val spanNormal = textNormal.toSpannable().apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                8,
                11,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(Color.GREEN),
                16,
                21,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.tvColorSpan.text = spanNormal

        // Underlined and LineThrough
        val textUnderlineLineThrough = binding.tvUnderlineLineThroughSpan.text
        val spanUnderlineLineThrough = textUnderlineLineThrough.toSpannable().apply {
            setSpan(
                UnderlineSpan(),
                8,
                18,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                StrikethroughSpan(),
                23,
                34,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.tvUnderlineLineThroughSpan.text = spanUnderlineLineThrough

        // Clickable Link
        val textLink = binding.tvLink.text
        val spanLink = textLink.toSpannable().apply {
            setSpan(
                URLSpan("https://krunalpatel.me")
            )
        }
    }
}