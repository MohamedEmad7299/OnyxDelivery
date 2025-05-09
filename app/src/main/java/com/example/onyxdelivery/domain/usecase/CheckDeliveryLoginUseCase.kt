package com.example.onyxdelivery.domain.usecase

import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import com.example.onyxdelivery.domain.repository.OnyxRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CheckDeliveryLoginUseCase @Inject constructor(
    private val repository: OnyxRepository
) {
    suspend operator fun invoke(request: LoginRequestDto): Resource<LoginResponseDto> {
        return try {
            val response = repository.checkDeliveryLogin(request)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Login failed: ${response.message()}")
            }
        } catch (e: IOException) {
            Resource.Error("Network error", e)
        } catch (e: HttpException) {
            Resource.Error("Server error: ${e.code()}", e)
        } catch (e: Exception) {
            Resource.Error("Unexpected login error", e)
        }
    }
}