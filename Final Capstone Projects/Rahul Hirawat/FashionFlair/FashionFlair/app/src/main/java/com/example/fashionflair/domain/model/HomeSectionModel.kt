package com.example.fashionflair.domain.model


data class HomeSectionModel(
    val productList: List<ProductItemModel>? = null,
    val bannerText: String? = null,
    val sectionType: HomeSectionType
)

enum class HomeSectionType {
    OFFER_BANNER,
    PRODUCT_LIST,
    FOOTER
}