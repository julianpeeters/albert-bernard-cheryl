package com.julianpeeters.example.server.infrastructure.doobie.statements

import com.julianpeeters.example.server.db.DbSpecification
import com.julianpeeters.example.shared.domain.model.{User, Username}
import com.julianpeeters.example.shared.model.ArbitraryInstances._
import com.julianpeeters.example.shared.model.Id

class UserStatementsTest extends DbSpecification {
  "create" >>
    check(UserStatements.create(sampleOf[User]))

  "deleteAll" >>
    check(UserStatements.deleteAll)

  "findById" >>
    check(UserStatements.findById(sampleOf[Id[User]]))

  "findByName" >>
    check(UserStatements.findByName(sampleOf[Username]))
}
