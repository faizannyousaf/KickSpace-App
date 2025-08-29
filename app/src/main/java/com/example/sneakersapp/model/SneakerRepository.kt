package com.example.sneakersapp.model

object SneakerRepository {

    fun FetchSneaker() : List<Sneaker> {
        return listOf(Sneaker("Air max 90", "","2020", listOf("good sneaker","worth the price")),
            Sneaker("New Balance 1906", "","2022", listOf("So comfy","worth the price")),
            Sneaker("Gel-Kayano", "","2023", listOf("good sneaker","worth the price"))
        )
    }
}