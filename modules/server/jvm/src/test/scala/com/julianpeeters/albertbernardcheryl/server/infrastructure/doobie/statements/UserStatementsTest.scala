package com.julianpeeters.albertbernardcheryl.server.infrastructure.doobie.statements

import com.julianpeeters.albertbernardcheryl.server.db.DbSpecification
import com.julianpeeters.albertbernardcheryl.shared.domain.model.{User, Username}
import com.julianpeeters.albertbernardcheryl.shared.model.ArbitraryInstances._
import com.julianpeeters.albertbernardcheryl.shared.model.Id

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
