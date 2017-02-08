/**
 * Created by maria on 06.02.2017.
 */
package eldis.react

import scala.scalajs.js

package object mdl {
  def fillClassAttr(s: Seq[String]): Option[String] = Option(s).filterNot(_.isEmpty).map(_.mkString(" "))

  @js.native
  trait ReactMDLCommonPropsJS extends js.Any {
    val className: js.UndefOr[String] = js.native
    val key: js.UndefOr[String] = js.native
    val style: js.UndefOr[js.Object] = js.native
  }
}

