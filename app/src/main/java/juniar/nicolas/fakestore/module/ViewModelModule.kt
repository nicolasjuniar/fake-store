package juniar.nicolas.fakestore.module

import juniar.nicolas.fakestore.ui.login.LoginViewModel
import juniar.nicolas.fakestore.ui.main.cart.CartViewModel
import juniar.nicolas.fakestore.ui.main.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ProductViewModel(get(), get()) }
    viewModel { CartViewModel(get()) }
}