package com.example.healthypalate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.healthypalate.R
import com.example.healthypalate.databinding.ActivityMainBinding
import com.example.healthypalate.ui.adapters.BottomNavAdapter
import com.example.healthypalate.ui.diary.FragmentMealsDirections
import com.example.healthypalate.ui.home.FoodAdditivesDirections
import com.example.healthypalate.ui.home.HomeDirections
import com.example.healthypalate.ui.home.ProfileDirections
import com.example.healthypalate.ui.home.RecipesFragmentDirections
import com.example.healthypalate.ui.models.NavMenuModelItem
import com.example.healthypalate.ui.models.NavModelList
import com.example.healthypalate.ui.models.UniversalImageLoader
import com.example.healthypalate.ui.nav.BottomNavDrawer
import com.materialstudies.reply.ui.nav.AlphaSlideAction
import com.materialstudies.reply.ui.nav.HalfClockwiseRotateSlideAction
import com.materialstudies.reply.ui.nav.ShowHideFabStateAction
import com.nostra13.universalimageloader.core.ImageLoader

class MainActivity : AppCompatActivity(),
    Toolbar.OnMenuItemClickListener,
    NavController.OnDestinationChangedListener,
    BottomNavAdapter.BottonNavListener,
    androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bottomNavDrawer: BottomNavDrawer by lazy(LazyThreadSafetyMode.NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavDrawer
    }


    private lateinit var navController: NavController

    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.main_nav_host_frag)
            ?.childFragmentManager
            ?.fragments
            ?.first()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val hostFrag = supportFragmentManager.findFragmentById(R.id.main_nav_host_frag) as NavHostFragment
        navController = hostFrag.navController

        setUpBottomAppbarAndFab()
        initImageLoader()
    }

    fun initImageLoader(){
        val loader = UniversalImageLoader(this)
        ImageLoader.getInstance().init(loader.getConfig())
    }

    private fun setUpBottomAppbarAndFab() {
        binding.run {
            navController.addOnDestinationChangedListener(
                this@MainActivity
            )
        }

        binding.fabScan.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setOnClickListener {
                val intent = Intent(this@MainActivity, ScanActivity::class.java)
                startActivity(intent)
            }
        }

        bottomNavDrawer.apply {
            addOnSlideAction(HalfClockwiseRotateSlideAction(binding.bottomAppBarChevron))
            addOnSlideAction(AlphaSlideAction(binding.bottomAppBarTitle, true))
            addOnStateChangedAction(ShowHideFabStateAction(binding.fabScan))
            addNavigationListener(this@MainActivity)
        }

        binding.bottomAppBar.apply {
            setNavigationOnClickListener {
                bottomNavDrawer.toggle()
            }
            setOnMenuItemClickListener(this@MainActivity)
        }
        binding.bottomAppBarContentContainer.setOnClickListener {
            bottomNavDrawer.toggle()
        }
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id){
            R.id.home2 ->{

            }
            R.id.recipesFragment ->{

            }
            R.id.foodAdditives ->{

            }
            R.id.fragmentMeals ->{

            }
        }
    }

    private fun navigateToHome() {
        val dir = HomeDirections.actionGlobalHome2()
        navController.navigate(dir)
    }
    override fun onNavMenuItemClicked(item: NavMenuModelItem) {
        when(item.id){
            NavModelList.HOME_ID -> {
                navigateToHome()
                Toast.makeText(this, "id Home", Toast.LENGTH_SHORT).show()
            }
            NavModelList.FOODS_ID -> {
                navigateToIngredients()
                Toast.makeText(this, "id Foods", Toast.LENGTH_SHORT).show()
            }
            NavModelList.MEALS_ID -> {
                navigateToMeals()
                Toast.makeText(this, "id Meals", Toast.LENGTH_SHORT).show()
            }
            NavModelList.RECIPES_ID -> {
                navigateToRecipes()
                Toast.makeText(this, "id Recipes", Toast.LENGTH_SHORT).show()
            }
            NavModelList.PROFILE_ID ->{
                navigateToProfile()
            }
            NavModelList.NOTES_ID ->{
                navigateToNotes()
            }
        }
    }

    private fun navigateToProfile() {
        val direction = ProfileDirections.actionGlobalProfile()
        navController.navigate(direction)
    }

    private fun navigateToRecipes() {
        val direction = RecipesFragmentDirections.actionGlobalRecipesFragment()
        navController.navigate(direction)
    }

    private fun navigateToMeals() {
        val directions = HomeDirections.actionHome2ToFragmentMeals()
        navController.navigate(directions)
    }

    private fun navigateToIngredients() {
        val dir = FoodAdditivesDirections.actionGlobalFoodAdditives()
        navController.navigate(dir)

    }

    private fun navigateToNotes() {
        val dir = HomeDirections.actionHome2ToNotesList2()
        navController.navigate(dir)
    }



}