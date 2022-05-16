package com.xizz.viewpager.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tunjid.androidx.recyclerview.gridLayoutManager
import com.tunjid.androidx.recyclerview.listAdapterOf
import com.tunjid.androidx.recyclerview.viewbinding.binding
import com.tunjid.androidx.recyclerview.viewbinding.viewHolderFrom
import com.xizz.viewpager.R
import com.xizz.viewpager.databinding.FragmentNestedScrollingBinding
import com.xizz.viewpager.databinding.ViewholderHorizontalListBinding
import com.xizz.viewpager.databinding.ViewholderLetterBinding

class NestedScrollingFragment : Fragment(R.layout.fragment_nested_scrolling) {

    companion object {
        fun newInstance(): NestedScrollingFragment {
            return NestedScrollingFragment()
        }

        val letters: List<Char> = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q')
    }

    private lateinit var viewBinding: FragmentNestedScrollingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentNestedScrollingBinding.bind(view)
        viewBinding.recyclerView.apply {
            layoutManager = gridLayoutManager(2) { 2 }
            adapter = listAdapter()
        }
    }

    private fun listAdapter(viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()) = listAdapterOf(
        initialItems = listOf(letters, letters, letters, letters, letters, letters, letters, letters, letters, letters, letters, letters),
        viewHolderCreator = { viewGroup, _ ->
            viewGroup.viewHolderFrom(ViewholderHorizontalListBinding::inflate)
        },
        viewHolderBinder = { holder, item, _ ->
            holder.binding<ViewholderHorizontalListBinding>().apply {
                letters.adapter = listAdapter(item)
                letters.setRecycledViewPool(viewPool)
            }
        },
        viewTypeFunction = { it::class.hashCode() }
    )

    private fun listAdapter(letters: List<Char>) = listAdapterOf(
        initialItems = letters,
        viewHolderCreator = { viewGroup, _ ->
            viewGroup.viewHolderFrom(ViewholderLetterBinding::inflate)
        },
        viewHolderBinder = { holder, item, _ ->
            holder.binding<ViewholderLetterBinding>().apply {
                root.text = item.toString()
            }
        },
        viewTypeFunction = { it::class.hashCode() }
    )
}

