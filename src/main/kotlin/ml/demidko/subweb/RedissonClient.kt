package ml.demidko.subweb

import org.redisson.Redisson.create
import org.redisson.config.Config

class RedissonClient(connection: String) :
  org.redisson.api.RedissonClient by create(Config().apply {
    useSingleServer().apply {
      val authorizationIdx = 9
      username =
        connection
          .substring(authorizationIdx)
          .substringBefore(':')
      password =
        connection
          .substring(authorizationIdx + username.length + 1)
          .substringBefore('@')
      address = connection
    }
  })