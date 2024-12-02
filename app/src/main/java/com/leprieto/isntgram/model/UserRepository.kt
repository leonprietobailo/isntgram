package com.leprieto.isntgram.model

class UserRepository {

    private val users = mutableListOf(
        User(
            id = "omegaisugly",
            name = "León Prieto",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In accumsan magna tellus, a imperdiet nisi posuere sit amet. Fusce cursus sodales odio sed sollicitudin. Cras ullamcorper vel est et sodales. Nulla pretium condimentum urna, quis ultricies ante accumsan eu. In feugiat at sem eu bibendum. In tincidunt urna accumsan tincidunt consectetur. Morbi porttitor mattis lectus malesuada viverra.",
            posts = 0,
            followers = 0,
            following = 0
        ),
        User(
            id = "omegaisnkd",
            name = "León 2",
            description = "Secondary account",
            posts = 1,
            followers = 1,
            following = 1
        ),
        User(
            id = "mcubix",
            name = "Axel",
            description = "Just an ape",
            posts = 1,
            followers = 1,
            following = 1
        )
    )

    fun getUsers(): List<User> = users

    fun addUser(user: User) {
        users.add(user)
    }
}