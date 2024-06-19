package com.devgalan.tucofradia2.data.model.server

import com.devgalan.tucofradia2.data.model.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerProvider @Inject constructor() {
    var servers: List<Server> = emptyList()
    var playingServer: Server? =
        Server(-1, "", "", "", -1, -1, -1, false, User(-1, "", "", null, null))
    var myServers: List<Server> = emptyList()
}