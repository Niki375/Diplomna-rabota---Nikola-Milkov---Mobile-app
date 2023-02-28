package diplomna.savemyfood

import diplomna.savemyfood.authentication.LoginViewModel
import diplomna.savemyfood.authentication.SignUpBusinessViewModel
import diplomna.savemyfood.authentication.SignUpCustomerViewModel
import diplomna.savemyfood.service.AuthService
import diplomna.savemyfood.service.AuthServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    factory<AuthService> { AuthServiceImpl() }

    viewModel { SignUpCustomerViewModel(get()) }
    viewModel { SignUpBusinessViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}