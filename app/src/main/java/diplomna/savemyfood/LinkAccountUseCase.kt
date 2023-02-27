//package diplomna.savemyfood
//
//import diplomna.savemyfood.service.AuthService
//
//class LinkAccountUseCase(private val authService: AuthService) {
//    suspend operator fun invoke(email: String, password: String, onResult: (Throwable?) -> Unit) =
//        authService.linkAccount(email, password, onResult)
//}