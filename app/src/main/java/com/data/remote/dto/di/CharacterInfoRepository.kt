package com.data.remote.dto.di

import com.data.remote.NeoplelmageService
import com.data.remote.NeopleApiService
import com.data.remote.repository.CharacterEquipmentRepositorylmpl
import com.data.remote.repository.CharacterImageRepositoryImpl
import com.data.remote.repository.CharacterInfoRepositoryImpl
import com.data.remote.repository.CharacterSettingRepositoryImpl
import com.domain.respository.CharacterEquipmentRepository
import com.domain.respository.CharacterImageRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.domain.use_case.GetCharacterImageUseCase
import com.domain.use_case.GetCharacterInfoUseCase
import com.domain.use_case.GetCharacterSettingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCharacterInfoRepository(api : NeopleApiService): CharacterInfoRepository {
        return CharacterInfoRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCharacterSettingRepository(api : NeopleApiService): CharacterSettingRepository {
        return CharacterSettingRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCharacterImageRepository(api : NeoplelmageService): CharacterImageRepository {
        return CharacterImageRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCharacterEquipmentRepository(api : NeopleApiService): CharacterEquipmentRepository {
        return CharacterEquipmentRepositorylmpl(api)
    }


    @Provides
    @Singleton
    fun provideGetCharacterInfoUseCase(repository: CharacterInfoRepository): GetCharacterInfoUseCase {
        return GetCharacterInfoUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetCharacterSettingUseCase(repository: CharacterSettingRepository): GetCharacterSettingUseCase {
        return GetCharacterSettingUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetCharacterImageUseCase(repository: CharacterImageRepository): GetCharacterImageUseCase {
        return GetCharacterImageUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterEquipmentUseCase(repository: CharacterEquipmentRepository): GetCharacterEquipmentUseCase {
        return GetCharacterEquipmentUseCase(repository)
    }
}
