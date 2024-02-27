package ru.unlim1x.myflowersclassic.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.unlim1x.myflowersclassic.R
import ru.unlim1x.myflowersclassic.app.AdapterListener
import ru.unlim1x.myflowersclassic.presentation.viewmodels.MainFragmentViewModel
import ru.unlim1x.myflowersclassic.presentation.adapters.RecyclerViewFlowersAdapter
import ru.unlim1x.myflowersclassic.presentation.adapters.RecyclerViewStoriesAdapter


class MainFragment : Fragment(), AdapterListener {
    private lateinit var navController: NavController
    private val viewModel by viewModel<MainFragmentViewModel>()
    private var elements_loaded_first_time = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment_container
        )
        val r1 = getView()?.findViewById<RecyclerView>(R.id.rv_achievements)
        val r2 = getView()?.findViewById<RecyclerView>(R.id.rv_flowers)
        val adapter1 = RecyclerViewStoriesAdapter()
        val adapter2 = RecyclerViewFlowersAdapter(
            viewModel.listOfFlowersLD.value,
            this
        )
        r1?.adapter = adapter1
        r2?.adapter = adapter2
        val scrollView = getView()?.findViewById<NestedScrollView>(R.id.main)

        if (viewModel.listOfFlowersLD.value?.size == null) {
            viewModel.loadSomeFlowers()
        }

        viewModel.listOfFlowersLD.observe(viewLifecycleOwner) {
            (r2?.adapter as RecyclerViewFlowersAdapter).setList(it)
            (r2?.adapter as RecyclerViewFlowersAdapter).notifyItemInserted(it.size)
        }



        scrollView?.viewTreeObserver?.addOnScrollChangedListener {
            if (scrollView.getChildAt(0).bottom
                == (scrollView.height + scrollView.scrollY)
            ) {
                if (viewModel.listOfFlowersLD.value?.size != null) {
                    viewModel.loadSomeFlowers()
                }
            }
        }

        val myGardenIV = view.findViewById<ImageView>(R.id.garden_image)
        myGardenIV.load(R.drawable.mygardenpic){
            crossfade(true)
            transformations(RoundedCornersTransformation(20f))
        }

        val myGardenButton = view.findViewById<LinearLayout>(R.id.my_Garden_Button)
        myGardenButton.setOnClickListener {
            navController.navigate(R.id.myGardenFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun allElementsLoaded() {
        val scrollView = view?.findViewById<NestedScrollView>(R.id.main)
        if (!elements_loaded_first_time)
            scrollView?.post {
                scrollView.smoothScrollTo(
                    scrollView.x.toInt(),
                    scrollView.scrollY + 70
                )
            }
        elements_loaded_first_time = false
    }
}