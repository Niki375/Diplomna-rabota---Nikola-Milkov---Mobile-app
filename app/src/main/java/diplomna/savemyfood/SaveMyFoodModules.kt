package diplomna.savemyfood

import diplomna.savemyfood.authentication.ForgotPasswordViewModel
import diplomna.savemyfood.authentication.LoginViewModel
import diplomna.savemyfood.authentication.SignUpBusinessViewModel
import diplomna.savemyfood.authentication.SignUpCustomerViewModel
import diplomna.savemyfood.business.BusinessHomeViewModel
import diplomna.savemyfood.business.BusinessProfileViewModel
import diplomna.savemyfood.customer.CustomerCartViewModel
import diplomna.savemyfood.customer.CustomerHomeViewModel
import diplomna.savemyfood.customer.CustomerMapViewModel
import diplomna.savemyfood.customer.CustomerProfileViewModel
import diplomna.savemyfood.service.AuthService
import diplomna.savemyfood.service.AuthServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    factory<AuthService> { AuthServiceImpl() }

    viewModel<SignUpCustomerViewModel>()
    viewModel<SignUpBusinessViewModel>()
    viewModel<LoginViewModel>()
    viewModel<BusinessHomeViewModel>()
    viewModel<CustomerHomeViewModel>()
    viewModel<CustomerCartViewModel>()
    viewModel<CustomerProfileViewModel>()
    viewModel<BusinessProfileViewModel>()
    viewModel<CustomerMapViewModel>()
    viewModel<ForgotPasswordViewModel>()
}