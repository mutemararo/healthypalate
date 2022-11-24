package com.example.healthypalate.ui.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import com.example.healthypalate.databinding.FragmentBottomNavDrawerBinding
import com.example.healthypalate.ui.adapters.BottomNavAdapter
import com.example.healthypalate.ui.models.NavMenuModelItem
import com.example.healthypalate.ui.models.NavModelList
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.materialstudies.reply.ui.nav.*
import kotlin.LazyThreadSafetyMode.NONE


class BottomNavDrawer :
    Fragment(),
    BottomNavAdapter.BottonNavListener{

    private var binding: FragmentBottomNavDrawerBinding? = null

    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(binding!!.backgroundContainer)
    }
    private val navigationListeners: MutableList<BottomNavAdapter.BottonNavListener> = mutableListOf()

    private val closeDrawerOnBackPressed = object : OnBackPressedCallback(false)
    {
        override fun handleOnBackPressed() {
            close()
        }
    }

    private val bottomSheetCallback: BottomNavigationDrawerCallback = BottomNavigationDrawerCallback()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, closeDrawerOnBackPressed)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = FragmentBottomNavDrawerBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            scrimView.setOnClickListener { close() }

            bottomSheetCallback.apply {
                //ScrimView transforms
                addOnSlideAction(AlphaSlideAction(scrimView))
                addOnStateChangedAction(VisibilityStateAction(scrimView))

                addOnStateChangedAction(ScrollToTopStateAction(navMenuList))

                addOnStateChangedAction(object : OnStateChangedAction{
                    override fun onStateChanged(sheet: View, newState: Int) {
                        closeDrawerOnBackPressed.isEnabled = newState != STATE_HIDDEN
                    }
                })
            }

            behavior.addBottomSheetCallback(bottomSheetCallback)
            behavior.state = STATE_HIDDEN
            val adapter = BottomNavAdapter(NavModelList.nav_menu_list, this@BottomNavDrawer)
            navMenuList.adapter = adapter

            NavModelList.navigationList.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
            NavModelList.setNavigationMenuItemChecked(1)
        }
    }

    fun toggle() {
        when {
            behavior.state == STATE_HIDDEN -> open()
            behavior.state == STATE_HIDDEN
                    || behavior.state == BottomSheetBehavior.STATE_HALF_EXPANDED
                    || behavior.state == BottomSheetBehavior.STATE_EXPANDED
                    || behavior.state == BottomSheetBehavior.STATE_COLLAPSED -> close()
        }
    }

    fun open() {
        behavior.state = STATE_HALF_EXPANDED
    }

    override fun onNavMenuItemClicked(item: NavMenuModelItem) {
        NavModelList.setNavigationMenuItemChecked(item.id)
        close()
        navigationListeners.forEach { it.onNavMenuItemClicked(item) }
    }


    fun addNavigationListener(listener: BottomNavAdapter.BottonNavListener){
        navigationListeners.add(listener)
    }

    fun close() {
        behavior.state = STATE_HIDDEN
    }

    fun addOnSlideAction(action: OnSlideAction){
        bottomSheetCallback.addOnSlideAction(action)
    }

    fun addOnStateChangedAction(action: OnStateChangedAction){
        bottomSheetCallback.addOnStateChangedAction(action)
    }

}