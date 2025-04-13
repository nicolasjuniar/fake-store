package juniar.nicolas.fakestore.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject

abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment() {

    protected val diffCallback: DiffCallback by inject()

    protected val progressBarDialog by lazy {
        ProgressBarDialog(requireActivity())
    }

    private var _viewBinding: VB? = null
    protected val viewBinding get() = _viewBinding!!

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = getContentView(inflater, container)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        observe(this@BaseViewBindingFragment) { data -> data?.let(action) }
    }

    protected fun observeViewModel(viewModel: BaseViewModel) {
        with(viewModel) {
            observeLoadingViewModel(this)
            observeMessageViewModel(this)
        }
    }


    open fun observeLoadingViewModel(viewModel: BaseViewModel) {
        viewModel.observeLoading().onChangeValue {
            progressBarDialog.isVisible(it)
        }
    }

    open fun observeMessageViewModel(viewModel: BaseViewModel) {
        viewModel.observeMessage().onChangeValue {
            requireActivity().showToast(it)
        }
    }

    protected fun createActivityResultLauncer(
        onActivityResult: (activityResult: ActivityResult) -> Unit,
    ) = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        onActivityResult.invoke(it)
    }

    protected inline fun <reified T : Activity> ActivityResultLauncher<Intent>.launchIntent(
        bundle: Bundle? = null,
    ) {
        val intent = Intent(requireActivity(), T::class.java)
        bundle?.let {
            intent.putExtras(bundle)
        }
        this.launch(intent)
    }
}