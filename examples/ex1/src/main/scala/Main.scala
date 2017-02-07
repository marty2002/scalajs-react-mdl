/**
 * Created by maria on 06.02.2017.
 */

package eldis.mdl.examples.ex1

import eldis.react.ReactNode

import scalajs.js
import eldis.react._
import vdom.prefix_<^._
import org.scalajs.dom
import eldis.react.mdl.components._

object Main extends js.JSApp {

  def render(): ReactNode = {
    <.div()(
      <.h4()("The Icon:"),
      Icon(Icon.Props(name = "save", key = Some("save_icon")))
    )
  }

  def main(): Unit = {
    ReactDOM.render(
      render(),
      dom.document.getElementById("root")
    )
  }

}