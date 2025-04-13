package juniar.nicolas.fakestore.ui.detailproduct

import android.os.Bundle
import juniar.nicolas.fakestore.data.model.ProductModel
import juniar.nicolas.fakestore.databinding.ActivityDetailProductBinding
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.util.onLoad
import juniar.nicolas.fakestore.util.orEmpty
import juniar.nicolas.fakestore.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailProductActivity : BaseViewBindingActivity<ActivityDetailProductBinding>() {

    private val viewModel: DetailProductViewModel by viewModel()

    companion object {
        const val ID_PRODUCT = "ID_PRODUCT"
    }

    override fun getContentView() = ActivityDetailProductBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        val idProduct = intent.getIntExtra(ID_PRODUCT, -1)
        observeData()
        viewModel.getProductById(idProduct)
    }

    private fun observeData() {
        with(viewModel) {
            observeViewModel(viewModel)
            observeProduct().onChangeValue {
                setupLayout(it)
            }
        }
    }

    private fun setupLayout(productModel: ProductModel) {
        with(viewBinding) {
            ivProduct.onLoad(this@DetailProductActivity, productModel.image.orEmpty())
            tvName.text = productModel.title
            tvCategory.text = productModel.category
            tvPrice.text = "$${productModel.price}"
            rbProduct.rating = productModel.rating?.rate?.toFloat().orEmpty()
            tvRating.text = "${productModel.rating?.rate}(${productModel.rating?.count})"
            tvDescription.text = productModel.description
            btnAddToCart.setOnClickListener {
                viewModel.addToCart()
                showToast("Success Add To Cart")
                finish()
            }
        }
    }
}