/**
 * Created by maria on 06.02.2017.
 */
package eldis.react

import scala.scalajs.js

@js.native
trait ReactMDLCommonPropsJS extends js.Object {
  val className: js.UndefOr[String] = js.native
  val key: js.UndefOr[String] = js.native
  val style: js.UndefOr[js.Object] = js.native
}

trait ComponentCommonProps {
  val className: Seq[String]
  val key: Option[String]
  val style: Option[js.Object]
}

package object mdl {
  def fillClassAttr(s: Seq[String]): Option[String] = Option(s).filterNot(_.isEmpty).map(_.mkString(" "))
}

