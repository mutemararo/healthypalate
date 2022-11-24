package com.example.healthypalate.ui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.example.healthypalate.R

data class NavMenuModelItem(
    val id: Int,
    @DrawableRes val nav_icon: Int,
    @StringRes val nav_title: Int,
    val checked: Boolean
)

object NavModelList{
    val PROFILE_ID: Int = 4
    val MEALS_ID: Int = 3
    val FOODS_ID: Int = 2
    val RECIPES_ID: Int = 1
    val HOME_ID: Int = 0
    val NOTES_ID: Int = 5
    val nav_menu_list = mutableListOf<NavMenuModelItem>(
        NavMenuModelItem(
            id = PROFILE_ID,
            nav_icon = R.drawable.icon_me,
            nav_title = R.string.nav_profile,
            checked = false
        ),

        NavMenuModelItem(
            id = HOME_ID,
            nav_icon = R.drawable.icon_home,
            nav_title = R.string.nav_home,
            checked = false
        ),

        NavMenuModelItem(
            id = RECIPES_ID,
            nav_icon = R.drawable.icon_recipes,
            nav_title = R.string.nav_recipes,
            checked = false
        ),
        NavMenuModelItem(
            id = FOODS_ID,
            nav_icon = R.drawable.icon_ingredients_list,
            nav_title = R.string.nav_ingredients,
            checked = false
        ),
        NavMenuModelItem(
            id = MEALS_ID,
            nav_icon = R.drawable.icon_meals,
            nav_title = R.string.nav_meals,
            checked = false
        ),
        NavMenuModelItem(id = NOTES_ID, nav_icon = R.drawable.note_icon, nav_title = R.string.notes, checked = false)
    )


    private val _navigationList : MutableLiveData<List<NavMenuModelItem>> = MutableLiveData()
    val navigationList: LiveData<List<NavMenuModelItem>>
        get() = _navigationList

    init {
        updateList()
    }

    fun setNavigationMenuItemChecked(id: Int): Boolean{
        var updated = false
        nav_menu_list.forEachIndexed { index, navMenuModel ->
            val shouldcheck = navMenuModel.id == id
            if (navMenuModel.checked != shouldcheck){
                nav_menu_list[index] = navMenuModel.copy(checked = shouldcheck)
                updated = true
            }
        }
        if (updated) updateList()

        return updated
    }


    fun updateList(){
        val newList = nav_menu_list
        _navigationList.value = newList
    }

    object NavItemDiff: DiffUtil.ItemCallback<NavMenuModelItem>() {
        override fun areItemsTheSame(
            oldItem: NavMenuModelItem,
            newItem: NavMenuModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NavMenuModelItem,
            newItem: NavMenuModelItem
        ): Boolean {
            return oldItem.nav_icon == newItem
                .nav_icon &&
                    oldItem.nav_title == newItem.nav_title &&
                    oldItem.checked == newItem.checked
        }

    }
}