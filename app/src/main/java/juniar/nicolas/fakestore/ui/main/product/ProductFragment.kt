package juniar.nicolas.fakestore.ui.main.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import juniar.nicolas.fakestore.R
import juniar.nicolas.fakestore.data.model.ProductModel
import juniar.nicolas.fakestore.databinding.FragmentProductBinding
import juniar.nicolas.fakestore.databinding.ViewholderProductBinding
import juniar.nicolas.fakestore.ui.detailproduct.DetailProductActivity
import juniar.nicolas.fakestore.ui.detailproduct.DetailProductActivity.Companion.ID_PRODUCT
import juniar.nicolas.fakestore.ui.main.product.ListCategoryActivity.Companion.CATEGORY_SELECTED
import juniar.nicolas.fakestore.ui.main.product.ListCategoryActivity.Companion.LIST_CATEGORY
import juniar.nicolas.fakestore.util.BaseViewBindingFragment
import juniar.nicolas.fakestore.util.GeneralRecyclerViewBindingAdapter
import juniar.nicolas.fakestore.util.gone
import juniar.nicolas.fakestore.util.onLoad
import juniar.nicolas.fakestore.util.openActivity
import juniar.nicolas.fakestore.util.orEmpty
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
                    quantityContainer.gone()
                }
            },
            itemListener = { product, _, _ ->
                requireActivity().openActivity<DetailProductActivity>(
                    bundleOf(ID_PRODUCT to product.id)
                )
            }
        )
    }

    private val selectCategoryCallback = createActivityResultLauncer {
        it.data?.getStringExtra(CATEGORY_SELECTED)?.let { value ->
            setupCategory(value)
        }
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
        with(viewBinding) {
            rvProduct.adapter = productListAdapter
            rvProduct.layoutManager = LinearLayoutManager(requireActivity())
            categoryContainer.setOnClickListener {
                viewModel.observeListCategory().value?.let {
                    val listCategory = it + "All"
                    selectCategoryCallback.launchIntent<ListCategoryActivity>(
                        bundleOf(
                            LIST_CATEGORY to listCategory
                        )
                    )
                }
            }
        }
    }

    private fun setupCategory(categoryName: String) {
        with(viewBinding) {
            tvCategory.text = categoryName
            if (categoryName.equals("All", true)) {
                categoryContainer.setBackgroundResource(R.drawable.rounded_bg_stroke_purple_500)
                ivCategory.setImageResource(R.drawable.ic_category_24dp_purple)
                tvCategory.setTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.purple_500
                    )
                )
                viewModel.getProducts()
            } else {
                categoryContainer.setBackgroundResource(R.drawable.rounded_bg_purple_500)
                ivCategory.setImageResource(R.drawable.ic_category_24dp_white)
                tvCategory.setTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.white
                    )
                )
                viewModel.getProductsByCategory(categoryName)
            }
        }
    }
}