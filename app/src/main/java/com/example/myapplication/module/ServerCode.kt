package com.example.myapplication.module

@Suppress("unused")
enum class ServerCode(
    val ServerCode: String?,
    val label: String
) {
    Default(null, "서버 선택"),
    cain("01", "카인"),
    diregie("02", "디레지에"),
    prey("03", "프레이"),
    casillas("04", "카시야스"),
    hilder("05", "힐더"),
    anton("06", "안톤"),
    bakal("07", "바칼")
}