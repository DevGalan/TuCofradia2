package com.devgalan.tucofradia2.data.model.server

import com.devgalan.tucofradia2.data.model.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerProvider @Inject constructor() {
//    var servers: List<Server> = emptyList()
    var servers: List<Server> = listOf(
        Server(
            1,
            "Tu Cofradía 1",
            "TC1",
            "Tu Cofradía 1",
            1,
            6,
            10,
            User(1, "admin", "admin", null, null)),
        Server(
            2,
            "Tu Cofradía 2",
            "TC2",
            "Tu Cofradía 2",
            2,
            6,
            10,
            User(1, "admin", "admin", null, null)),
        Server(
            3,
            "Tu Cofradía 3",
            "TC3",
            "Tu Cofradía 3",
            3,
            6,
            10,
            User(1, "admin", "admin", null, null)),
        Server(
            4,
            "Tu Cofradía 4",
            "TC4",
            "Tu Cofradía 4",
            4,
            6,
            10,
            User(1, "admin", "admin", null, null)),
        Server(
            5,
            "Tu Cofradía 5",
            "TC5",
            "Tu Cofradía 5",
            5,
            6,
            10,
            User(1, "admin", "admin", null, null)),
        Server(
            6,
            "Tu Cofradía 6",
            "TC6",
            "Tu Cofradía 6",
            6,
            6,
            10,
            User(1, "admin", "admin", null, null)),
        Server(
            7,
            "Tu Cofradía 7",
            "TC7",
            "Tu Cofradía 7",
            7,
            6,
            10,
            User(1, "admin", "admin", null, null))
    )
}