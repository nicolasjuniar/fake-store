package juniar.nicolas.fakestore.ui.main.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import juniar.nicolas.fakestore.R
import juniar.nicolas.fakestore.data.model.ProductLocal
import juniar.nicolas.fakestore.databinding.FragmentCartBinding
import juniar.nicolas.fakestore.databinding.ViewholderProductBinding
import juniar.nicolas.fakestore.util.BaseViewBindingFragment
import juniar.nicolas.fakestore.util.GeneralRecyclerViewBindingAdapter
import juniar.nicolas.fakestore.util.onLoad
import juniar.nicolas.fakestore.util.orEmpty
import juniar.nicolas.fakestore.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseViewBindingFragment<FragmentCartBinding>() {

    companion object {
        const val DECREASE = 0
        const val INCREASE = 1
    }

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
                    quantityContainer.visible()
                    tvQuantity.text = product.quantity.toString()
                }
            },
            specificResViewId = listOf(R.id.iv_decrease, R.id.iv_increase),
            specificViewListener = { product, _, _, posId ->
                when (posId) {
                    DECREASE -> product.quantity--
                    INCREASE -> product.quantity++
                }
                refreshCart(product.quantity, product.id)
            }
        )
    }

    private val checkoutDialog by lazy {
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Do you want checkout these items?")
            setPositiveButton("Yes") { dialog, _ ->
                viewModel.checkout()
                dialog.dismiss()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        }.create()
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
            observeViewModel(this)
            observeListProduct().onChangeValue {
                productListAdapter.setData(it)
                refreshLayout()
            }
        }
    }

    private fun setupLayout() {
        with(viewBinding) {
            rvProduct.adapter = productListAdapter
            rvProduct.layoutManager = LinearLayoutManager(requireActivity())
            btnCheckout.setOnClickListener {
                checkoutDialog.show()
            }
        }
        refreshLayout()
    }

    private fun refreshLayout() {
        with(viewBinding) {
            btnCheckout.isVisible = productListAdapter.itemCount > 0
            emptyCart.isVisible = productListAdapter.itemCount == 0
            rvProduct.isVisible = productListAdapter.itemCount > 0
        }
    }

    private fun refreshCart(quantity: Int, id: Int) {
        viewModel.updateQuantity(quantity, id)
        productListAdapter.notifyDataSetChanged()
    }
}