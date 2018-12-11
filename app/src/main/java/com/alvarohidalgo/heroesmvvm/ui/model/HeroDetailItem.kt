package com.alvarohidalgo.heroesmvvm.ui.model


sealed class HeroDetailItem {
    data class Description(val descriptionText: String) : HeroDetailItem()
    data class ComicList(val list: List<ComicVM>) : HeroDetailItem()
    data class StoryList(val list: ComicVM) : HeroDetailItem()
    data class EventList(val list: ComicVM) : HeroDetailItem()
    data class SerieList(val list: ComicVM) : HeroDetailItem()
}