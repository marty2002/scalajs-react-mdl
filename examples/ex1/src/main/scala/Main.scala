/**
 * Created by maria on 06.02.2017.
 */

package eldis.mdl.examples.ex1

import scalajs.js
import eldis.react._
import org.scalajs.dom

object Main extends js.JSApp {
  def main(): Unit = {
    ReactDOM.render(
      TestForm(),
      dom.document.getElementsByClassName("testApp")(0)
    )
  }
}