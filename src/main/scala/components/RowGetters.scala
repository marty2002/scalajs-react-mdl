package eldis.react.mdl.components

import scala.scalajs.js

trait RowGetters[R, ID] {
  def getId(row: R): ID
  def getDesc(row: R): String
  def toJsAny(id: ID): js.Any = id.asInstanceOf[js.Any]
  def fromJsAny(id: js.Any): ID = id.asInstanceOf[ID]
}

