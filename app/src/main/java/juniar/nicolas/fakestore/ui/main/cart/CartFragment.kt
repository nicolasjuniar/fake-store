package juniar.nicolas.fakestore.ui.main.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import juniar.nicolas.fakestore.data.model.ProductLocal
import juniar.nicolas.fakestore.databinding.FragmentCartBinding
import juniar.nicolas.fakestore.databinding.ViewholderProductBinding
import juniar.nicolas.fakestore.util.BaseViewBindingFragment
import juniar.nicolas.fakestore.util.GeneralRecyclerViewBindingAdapter
import juniar.nicolas.fakestore.util.onLoad
import juniar.nicolas.fakestore.util.orEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseViewBindingFragment<FragmentCartBinding>() {

    private val viewModel: CartViewModel by viewModel()

    private val productListAdapter by lazy {
        GeneralRecyclerViewBindingAdapter<ProductLocal, ViewholderProductBinding>(
            diffCallback = diffCallback,
            holderResBinding = {
                ViewholderProductBinding.inflate(LayoutInflater.from(it.context), it, false)
            },
            onBind = { product, binding, _ ->
                with(binding) {
                    ivProduct.onLoad(requireActivity(), product.image.orEmpty())
                    tvTitle.text = product.title
                    tvPrice.text = "$${product.price}"
                    rbProduct.rating = product.ratingRate?.toFloat().orEmpty()
                    tvRating.text = "${product.ratingRate}(${product.ratingCount})"
                }
            },
            itemListener = { value, index, _ ->

            }
        )
    }

    override fun getContentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        setupLayout()
        viewModel.getListCart()
    }

    private fun observeData() {
        with(viewModel) {
            observeListProduct().onChangeValue {
                productListAdapter.setData(it)
            }
        }
    }

    private fun setupLayout() {
        viewBinding.rvProduct.adapter = productListAdapter
        viewBinding.rvProduct.layoutManager = LinearLayoutManager(requireActivity())
    }
}