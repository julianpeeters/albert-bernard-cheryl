package com.julianpeeters.example.server

import cats.effect.IO
import org.http4s._
import org.http4s.testing.Http4sMatchers
import org.specs2.mutable.Specification

object ServiceSpec extends Specification with Http4sMatchers {
  "Service.api" >> {
    "/version has MediaType application/json" >> {
      val request = Request[IO](Method.GET, Uri.uri("/version"))
      val response = unsafeGetResponse(Service.api(null), request)
      response must haveMediaType(MediaType.`application/json`)
    }
  }

  def unsafeGetResponse(service: HttpService[IO],
                        request: Request[IO]): Response[IO] =
    service.run(request).cata(???, identity).unsafeRunSync()
}
