package juniar.nicolas.fakestore.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject

abstract class BaseViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val viewBinding: VB by lazy {
        getContentView()
    }

    protected val progressBarDialog by lazy {
        ProgressBarDialog(this)
    }

    protected val diffCallback: DiffCallback by inject()

    abstract fun getContentView(): VB

    abstract fun onViewReady(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        onViewReady(savedInstanceState)
    }

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        observe(this@BaseViewBindingActivity) { data -> data?.let(action) }
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
            showToast(it)
        }
    }
}