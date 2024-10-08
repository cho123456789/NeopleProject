










### 패키지 구조
```
📦 Kotlin / Java
├─ Common
├─ 
│  ├─ Constants : API_KEY
│  └─ Resource :  네트워크 응답 처리
├─ Dat
│  ├─ remote 
│  ├─ NeoplelmageService
│  ├─ NeopleApiService
│  │  └─ di
│  │     ├─ AppModule 
│  │     └─ CharacterInfoRepository : 
│  ├─ CharacterImgDt
│  ├─ CharacterInfoDto
│  └─ EquipmentDto 
├─ domain
│  ├─ usecase
│  │  ├─ GetCharacterEquipmentUseCase
│  │  ├─ GetCharacterImageUseCase
│  │  ├─ GetCharacterInfoUseCase
│  │  └─ GetCharacterSettingUseCase
│  ├─ respository
│  │  ├─ CharacterEquipmentRepository
│  │  ├─ CharacterImageRepository
│  │  ├─ CharacterInfoRepository
│  │  └─ CharacterSettingRepository
│  └─ model 
│     ├─ characterDto
│     └─ ImageDto
├─ myapplication
│  ├─ MainActivity
MyApplication
│  ├─ viewmodel
│  │  ├─ CharacterEquipmentViewModel
│  │  ├─ CharacterImageViewModel
│  │  ├─ CharacterInfoViewModel
│  │  └─ CharacterSettingViewModel
│  ├─ ui
│  │  ├─ CharacterSettingScreen
│  │  ├─ EquipmentScreen
│  │  ├─ MainScreen
│  │  └─ CharacterSearchScreen
│  ├─ network
│  │  └─ CharacterResponse
│  └─ module
│     └─ ServerCode
└─ repository
   ├─ CharacterEquipmentRepositorylmpl
   ├─ CharacterImageRepositoryImpl
   ├─ CharacterInfoRepositoryImpl
   └─ CharacterSettingRepositoryImpl
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)
