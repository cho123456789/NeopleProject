//package com.data.remote.repository
//
//import com.data.remote.NeopleApiService
//import com.data.remote.dto.CharacterInfoDto
//import com.data.remote.dto.EquipmentDto
//import com.data.remote.dto.di.Constants.API_KEY
//import com.domain.respository.CharacterRepository
//import okhttp3.ResponseBody
//import retrofit2.Response
//import javax.inject.Inject
//
//class NeopleRepositoryImpl @Inject constructor(
//    private val api : NeopleApiService,
//)  : CharacterRepository{
//
//    override suspend fun getCharacterImage(
//        serverId: String,
//        characterId: String,
//        zoom: Int
//    ): Response<ResponseBody> {
//        return api.getCharacterImage(serverId,characterId,zoom)
//    }
//
//    override suspend fun getCharacterId(
//        serverId: String,
//        characterId: String,
//        apiKey: String
//    ): Response<CharacterInfoDto> {
//        return api.getCharacterI(serverId,characterId,API_KEY)
//    }
//
//    override suspend fun getEquipment(
//        serverId: String,
//        characterId: String,
//        apiKey: String
//    ): Response<EquipmentDto> {
//        return api.getEquipment(serverId,characterId,API_KEY)
//    }
//}