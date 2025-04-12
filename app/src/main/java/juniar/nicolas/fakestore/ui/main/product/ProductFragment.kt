package juniar.nicolas.fakestore.ui.main.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import juniar.nicolas.fakestore.R
import juniar.nicolas.fakestore.data.model.ProductModel
import juniar.nicolas.fakestore.databinding.FragmentProductBinding
import juniar.nicolas.fakestore.databinding.ViewholderProductBinding
import juniar.nicolas.fakestore.util.BaseViewBindingFragment
import juniar.nicolas.fakestore.util.GeneralRecyclerViewBindingAdapter
import juniar.nicolas.fakestore.util.onLoad
import juniar.nicolas.fakestore.util.orEmpty
import juniar.nicolas.fakestore.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : BaseViewBindingFragment<FragmentProductBinding>() {

    private val viewModel: ProductViewModel by viewModel()

    private val productListAdapter by lazy {
        GeneralRecyclerViewBindingAdapter<ProductModel, ViewholderProductBinding>(
            diffCallback = diffCallback,
            holderResBinding = {
                ViewholderProductBinding.inflate(LayoutInflater.from(it.context), it, false)
            },
            onBind = { product, binding, _ ->
                with(binding) {
                    ivProduct.onLoad(requireActivity(), product.image.orEmpty())
                    tvTitle.text = product.title
                    tvPrice.text = "$${product.price}"
                    rbProduct.rating = product.rating?.rate?.toFloat().orEmpty()
                    tvRating.text = "${product.rating?.rate}(${product.rating?.count})"
                }
            },
            itemListener = { product, _, _ ->
                viewModel.insertProduct(product.toProductLocal(1, 1))
                requireActivity().showToast("Success Add to Cart")
            }
        )
    }

    override fun getContentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        setupLayout()
        viewModel.getProducts()
        viewModel.getCategories()
    }

    private fun observeData() {
        with(viewModel) {
            observeViewModel(this)
            observeListProduct().onChangeValue {
                productListAdapter.setData(it)
            }
        }
    }

    private fun setupLayout() {
        viewBinding.rvProduct.adapter = productListAdapter
        viewBinding.rvProduct.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setupCategory(categoryName: String) {
        viewBinding.tvCategory.text = categoryName
        if (categoryName.equals("All", true)) {
            viewBinding.categoryContainer.setBackgroundResource(R.drawable.rounded_bg_stroke_purple_500)
            viewBinding.ivCategory.setImageResource(R.drawable.ic_category_24dp_purple)
        } else {
            viewBinding.categoryContainer.setBackgroundResource(R.drawable.rounded_bg_purple_500)
            viewBinding.ivCategory.setImageResource(R.drawable.ic_category_24dp_white)
        }
        viewModel.getProductsByCategory(categoryName)
    }
}