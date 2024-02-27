package ru.unlim1x.myflowersclassic.presentation.fragments

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text
import ru.unlim1x.myflowersclassic.R
import ru.unlim1x.myflowersclassic.presentation.InventoryListener
import ru.unlim1x.myflowersclassic.presentation.adapters.RecyclerViewInventoryAdapter
import ru.unlim1x.myflowersclassic.presentation.adapters.RecyclerViewToolsAdapter
import ru.unlim1x.myflowersclassic.presentation.viewmodels.MyGardenFragmentViewModel
import ru.unlim1x.myflowersclassic.presentation.views.GardenScrollView
import kotlin.math.roundToInt


class MyGardenFragment : Fragment() {

    private val viewModel by viewModel<MyGardenFragmentViewModel>()

    private lateinit var tools_rv: RecyclerView
    private lateinit var inventory_rv: RecyclerView
    private lateinit var scrollView: GardenScrollView

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private var areaIsSet = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mygarden_fragment, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        scrollView = view.findViewById(R.id.scrollViewInGarden) //GardenScrollView

        bottomSheetBehavior =
            BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet_inventory_layout))

        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    BottomSheetBehavior.STATE_EXPANDED->{
                        if (!areaIsSet){
                            updateDropAreaHeight(this@MyGardenFragment.requireView(),bottomSheet.height )
                        }
                    }

                    BottomSheetBehavior.STATE_COLLAPSED->{
                        var can = false
                        if (!scrollView.canScrollVertically(1))
                            can = true
                        updateNestedScrollViewHeight(view, bottomSheetBehavior.peekHeight)
                        if (can)
                            scrollView.post{
                                scrollView.fullScroll(View.FOCUS_DOWN)
                            }
                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if(slideOffset>0)
                    updateNestedScrollViewHeight(view, (bottomSheet.height*slideOffset).roundToInt())
                Log.e("MGF", "slide offset: $slideOffset")
            }
        })

        tools_rv = view.findViewById<RecyclerView>(R.id.rv_inventory_tools)
        inventory_rv = view.findViewById<RecyclerView>(R.id.rv_inventory_flowers)



        val test_layout = view.findViewById<ConstraintLayout>(R.id.test_id_layout)
        test_layout.setOnDragListener(scrollView)

        viewModel.loadInventoryManual()
        viewModel.loadToolsManual()

        viewModel.listOfTools.observe(viewLifecycleOwner) {
            tools_rv.adapter = RecyclerViewToolsAdapter(it)
        }
        viewModel.listOfInventoryFlowers.observe(viewLifecycleOwner) {
            inventory_rv.adapter = RecyclerViewInventoryAdapter(it)
        }




        view.doOnLayout {
            updateNestedScrollViewHeight(view, bottomSheetBehavior.peekHeight)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateNestedScrollViewHeight(view: View, bottomSheetHeight:Int) {
        val nestedScrollView = view.findViewById<NestedScrollView>(R.id.scrollViewInGarden)

        val layoutParams: CoordinatorLayout.LayoutParams =
            nestedScrollView.layoutParams as CoordinatorLayout.LayoutParams

        layoutParams.height =view.height - bottomSheetHeight
            //requireContext().resources.displayMetrics.heightPixels - dpToPx(bottomSheetBehavior.peekHeight.toFloat())

        nestedScrollView.layoutParams = layoutParams
    }
    fun updateDropAreaHeight(view: View, bottomSheetHeight:Int) {
        val test_layout = view.findViewById<ConstraintLayout>(R.id.test_id_layout)


        val layoutParams: CoordinatorLayout.LayoutParams =
            test_layout.layoutParams as CoordinatorLayout.LayoutParams

        layoutParams.height =
            view.height - bottomSheetHeight - 10

        test_layout.layoutParams = layoutParams
    }

    fun convertPixelsToDp(px: Int): Int {
        return (px / (requireContext().resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    private fun dpToPx(dp: Float): Int {
        return (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )).roundToInt()
    }

}