package diplomna.savemyfood.business

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.launch

class BusinessHomeViewModel : ViewModel() {
    private val authService = AuthServiceImpl()

    private val _boxes = MutableLiveData<List<Box>?>()
    val boxes: MutableLiveData<List<Box>?>
        get() = _boxes

    fun loadBoxes() {
        viewModelScope.launch {
            val boxes = authService.getBoxData()
            _boxes.value = boxes
        }
    }
}
