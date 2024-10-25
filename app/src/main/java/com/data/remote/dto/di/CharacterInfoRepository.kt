package com.data.remote.dto.di

import com.data.remote.NeoplelmageService
import com.data.remote.NeopleApiService
import com.data.remote.repository.AvatarRepositoryImpl
import com.data.remote.repository.BufferAvatarRepositoryImpl
import com.data.remote.repository.BufferCreatureRepositoryImpl
import com.data.remote.repository.BufferEquipmentRepositoryImpl
import com.data.remote.repository.CharacterEquipmentRepositorylmpl
import com.data.remote.repository.CharacterImageRepositoryImpl
import com.data.remote.repository.CharacterInfoRepositoryImpl
import com.data.remote.repository.CharacterSettingRepositoryImpl
import com.data.remote.repository.TalismanRepositoryImpl
import com.domain.respository.AvatarRepository
import com.domain.respository.BufferAvatarRepository
import com.domain.respository.BufferCreatureRepository
import com.domain.respository.BufferEquipmentRepository
import com.domain.respository.CharacterEquipmentRepository
import com.domain.respository.CharacterImageRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.domain.respository.TalismanRepository
import com.domain.use_case.GetAvatarUseCase
import com.domain.use_case.GetBufferAvatarUseCase
import com.domain.use_case.GetBufferCreatureUseCase
import com.domain.use_case.GetBufferEquipmentUseCase
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.domain.use_case.GetCharacterImageUseCase
import com.domain.use_case.GetCharacterInfoUseCase
import com.domain.use_case.GetCharacterSettingUseCase
import com.domain.use_case.GetTalismanUseCase
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
    fun provideTalismanRepository(api : NeopleApiService): TalismanRepository {
        return TalismanRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAvatarRepository(api : NeopleApiService): AvatarRepository {
        return AvatarRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideBufferCreatureRepository(api : NeopleApiService): BufferCreatureRepository {
        return BufferCreatureRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideBufferAvatarRepository(api : NeopleApiService): BufferAvatarRepository {
        return BufferAvatarRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideBufferEquipmentRepository(api : NeopleApiService): BufferEquipmentRepository {
        return BufferEquipmentRepositoryImpl(api)
    }

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
    fun provideGetTalismanUseCase(repository: TalismanRepository): GetTalismanUseCase {
        return GetTalismanUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAvatarUseCase(repository: AvatarRepository): GetAvatarUseCase {
        return GetAvatarUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCreatureEquipmentUseCase(repository: BufferCreatureRepository): GetBufferCreatureUseCase {
        return GetBufferCreatureUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideGetAvatarEquipmentUseCase(repository: BufferAvatarRepository): GetBufferAvatarUseCase {
        return GetBufferAvatarUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetBufferEquipmentUseCase(repository: BufferEquipmentRepository): GetBufferEquipmentUseCase {
        return GetBufferEquipmentUseCase(repository)
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
