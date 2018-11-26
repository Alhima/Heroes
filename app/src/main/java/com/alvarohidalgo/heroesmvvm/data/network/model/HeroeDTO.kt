package com.alvarohidalgo.heroesmvvm.data.network.model

data class HeroeDTO(
    val comics: ComicsDTO,
    val description: String,
    val events: EventsDTO,
    val id: String,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesDTO,
    val stories: StoriesDTO,
    val thumbnail: ThumbnailDTO,
    val urls: List<UrlDTO>
)

data class SeriesDTO(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDTO>,
    val returned: String
)

data class UrlDTO(
    val type: String,
    val url: String
)

data class ComicsDTO(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDTO>,
    val returned: String
)

data class EventsDTO(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDTO>,
    val returned: String
)

data class StoriesDTO(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDTO>,
    val returned: String
)

data class ItemDTO(
    val name: String,
    val resourceURI: String,
    val type: String
)

data class ThumbnailDTO(
    val extension: String,
    val path: String
)