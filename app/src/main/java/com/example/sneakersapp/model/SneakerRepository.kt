package com.example.sneakersapp.model

object SneakerRepository {

    fun fetchSneakers() : List<Sneaker> {
        return listOf(Sneaker(1,"Air max 90", "It is from Nike", "","2020", listOf("good sneaker","worth the price")),
            Sneaker(2,"New Balance 1906", "It is from New Balace","","2022", listOf("So comfy","worth the price")),
            Sneaker(3,"Gel-Kayano", "It is from Asics","","2023", listOf("good sneaker","worth the price"))
        )
    }
}