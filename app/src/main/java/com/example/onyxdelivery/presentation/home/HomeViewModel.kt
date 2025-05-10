package com.example.onyxdelivery.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.dto.GetBillsValue
import com.example.onyxdelivery.data.model.dto.GetDeliveryBillsItemsRequestDto
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.domain.usecase.CacheDeliveryItemsUseCase
import com.example.onyxdelivery.domain.usecase.GetCachedDeliveryItemsUseCase
import com.example.onyxdelivery.domain.usecase.GetDeliveryBillsItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDeliveryBillsItemsUseCase: GetDeliveryBillsItemsUseCase,
    private val cacheDeliveryItemsUseCase: CacheDeliveryItemsUseCase,
    private val getCachedDeliveryItemsUseCase: GetCachedDeliveryItemsUseCase
) : ViewModel() {

    private val _billsState = MutableStateFlow<Resource<List<DeliveryItemEntity>>>(Resource.InitialState)
    val billsState: StateFlow<Resource<List<DeliveryItemEntity>>> = _billsState

    private val _screenState = MutableStateFlow(
        HomeState(
            selectedTab = "New"
        )
    )

    val screenState = _screenState.asStateFlow()

    fun loadBills(deliveryNo: String) {

        viewModelScope.launch {

            _billsState.value = Resource.Loading

            val request = GetDeliveryBillsItemsRequestDto(

                Value = GetBillsValue(
                    P_LANG_NO = "1",
                    P_DLVRY_NO = deliveryNo,
                    P_BILL_SRL = "",
                    P_PRCSSD_FLG = ""
                )
            )

            val remoteResult = getDeliveryBillsItemsUseCase(request)

            if (remoteResult is Resource.Success) {
                val deliveryBills = remoteResult.data.Data.DeliveryBills
                cacheDeliveryItemsUseCase(deliveryBills)
            }

            val cacheResult = getCachedDeliveryItemsUseCase()
            _billsState.value = cacheResult
        }
    }

    fun onChangeTab(tab: String) {
        _screenState.value = screenState.value.copy(selectedTab = tab)
    }
}


