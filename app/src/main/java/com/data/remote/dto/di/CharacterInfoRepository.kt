package com.data.remote.dto.di

import com.data.remote.NeopleApiService
import com.data.remote.NeoplelmageService
import com.data.remote.repository.AvatarRepositoryImpl
import com.data.remote.repository.CharacterEquipmentRepositorylmpl
import com.data.remote.repository.CharacterImageRepositoryImpl
import com.data.remote.repository.CharacterInfoRepositoryImpl
import com.data.remote.repository.CharacterSettingRepositoryImpl
import com.data.remote.repository.ItemRepositoryImpl
import com.data.remote.repository.MistAssimilationRepositoryImpl
import com.data.remote.repository.StatusRepositoryImpl
import com.domain.respository.AvatarRepository
import com.domain.respository.CharacterEquipmentRepository
import com.domain.respository.CharacterImageRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.domain.respository.ItemRepository
import com.domain.respository.MistAssimilationRepository
import com.domain.respository.StatusRepository
import com.domain.use_case.GetAvatarUseCase
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.domain.use_case.GetCharacterImageUseCase
import com.domain.use_case.GetCharacterInfoUseCase
import com.domain.use_case.GetCharacterSettingUseCase
import com.domain.use_case.GetItemDetailUseCase
import com.domain.use_case.GetMistAssimilationUseCase
import com.domain.use_case.GetStatusUseCase
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
    fun provideItemRepository(api : NeopleApiService): ItemRepository {
        return ItemRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideStatusRepository(api: NeopleApiService): StatusRepository {
        return StatusRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMistAssimilationRepository(api: NeopleApiService): MistAssimilationRepository {
        return MistAssimilationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAvatarRepository(api: NeopleApiService): AvatarRepository {
        return AvatarRepositoryImpl(api)
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
    fun provideGetItemDetailUseCase(repository: ItemRepository): GetItemDetailUseCase {
        return GetItemDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetStatusUseCase(repository: StatusRepository): GetStatusUseCase {
        return GetStatusUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMistAssimilationUseCase(repository: MistAssimilationRepository): GetMistAssimilationUseCase {
        return GetMistAssimilationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAvatarUseCase(repository: AvatarRepository): GetAvatarUseCase {
        return GetAvatarUseCase(repository)
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
