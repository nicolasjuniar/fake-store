package juniar.nicolas.fakestore.ui.main.product

import android.os.Bundle
import juniar.nicolas.fakestore.databinding.ActivityListCategoryBinding
import juniar.nicolas.fakestore.util.BaseViewBindingActivity

class ListCategoryActivity : BaseViewBindingActivity<ActivityListCategoryBinding>() {

    companion object {
        const val LIST_CATEGORY = "LIST_CATEGORY"
    }

    override fun getContentView() = ActivityListCategoryBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        val listCategory = intent.getStringArrayListExtra(LIST_CATEGORY)
    }

}