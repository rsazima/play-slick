package models
package object relationships{
  import play.api.db.slick.Config.driver.simple._

  // models depenencies
  import models.autojoin._
  import models.tables._

  // auto-join conditions
  implicit def autojoin1 = joinCondition(Computers,Devices)  (_.id === _.computerId)
  implicit def autojoin2 = joinCondition(Companies,Computers)(_.id === _.companyId)
  implicit def autojoin3 = joinCondition(Sites,InterfaceJoin[schema.HasSite])(_.id === _.siteId)

  implicit def autojoin4 = complexJoin(Sites,Computers){
    joinType => _.autoJoin(Devices,joinType).further(_,joinType)
  }
}