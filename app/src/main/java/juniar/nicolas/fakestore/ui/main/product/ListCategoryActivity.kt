package juniar.nicolas.fakestore.ui.main.product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import juniar.nicolas.fakestore.databinding.ActivityListCategoryBinding
import juniar.nicolas.fakestore.databinding.ViewholderCategoryBinding
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.util.GeneralRecyclerViewBindingAdapter

class ListCategoryActivity : BaseViewBindingActivity<ActivityListCategoryBinding>() {

    companion object {
        const val LIST_CATEGORY = "LIST_CATEGORY"
        const val CATEGORY_SELECTED = "CATEGORY_SELECTED"
    }

    private val categoryAdapter by lazy {
        GeneralRecyclerViewBindingAdapter<String, ViewholderCategoryBinding>(
            diffCallback = diffCallback,
            holderResBinding = {
                ViewholderCategoryBinding.inflate(LayoutInflater.from(it.context), it, false)
            },
            onBind = { value, binding, _ ->
                binding.tvCategory.text = value
            },
            itemListener = { value, _, _ ->
                setResult(RESULT_OK, Intent().apply {
                    putExtra(CATEGORY_SELECTED, value)
                })
                finish()
            }
        )
    }

    override fun getContentView() = ActivityListCategoryBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar?.title = "List Category"
        val listCategory = intent.getStringArrayListExtra(LIST_CATEGORY)
        viewBinding.rvCategory.adapter = categoryAdapter
        viewBinding.rvCategory.layoutManager = LinearLayoutManager(this)
        categoryAdapter.setData(listCategory?.toList().orEmpty())
    }

}