package diplomna.savemyfood

import diplomna.savemyfood.authentication.SignUpViewModel
import diplomna.savemyfood.service.AuthService
import diplomna.savemyfood.service.AuthServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    factory<AuthService> { AuthServiceImpl() }

    viewModel { SignUpViewModel(get()) }
}