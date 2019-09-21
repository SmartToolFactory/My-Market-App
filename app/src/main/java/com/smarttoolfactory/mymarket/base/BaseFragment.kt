package com.smarttoolfactory.mymarket.base

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * BaseFragment to avoid writing data-binding code over again for each fragment.
 *
 * 🔥🔥 NOTE: Didn't add ViewModel as generic model since it breaks Composition
 * for Fragments with none or more than one ViewModels.
 *
 * Generic approach forces Fragments to have specified number of ViewModels if added as generic parameter
 *
 *
 * LifeCycle of Fragments
 *
 * * onAttach()
 * * onCreate()
 * * onCreateView()
 * * onViewCreated()
 * * onStart()
 * * onResume()
 * * onPause()
 * * onStop()
 * * onDestroyView() The Fragment returns layout from backStack -> onCreate() is called
 * * onDestroy()
 * * onDetach()
 */
abstract class BaseFragment<T : ViewDataBinding> : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected var dataBinding: T? = null

    /**
     * Point that contains width and height of the fragment.
     *
     */
    private val dimensions = Point()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Each fragment can have it's separate toolbar menu
        setHasOptionsMenu(true)

        dataBinding =
            DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false)

        // 🔥 This is required if LiveData is used for data-binding
        dataBinding!!.lifecycleOwner = this

        val rootView = dataBinding?.root

        // Get width and height of the fragment
        rootView?.post {
            dimensions.x = rootView.width
            dimensions.y = rootView.height
        }

        return rootView
    }

    /**
     * This method gets the layout id from the derived fragment to bind to that layout via data-binding
     */
    abstract fun getLayoutId(): Int

}