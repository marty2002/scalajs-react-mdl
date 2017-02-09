/**
 * Created by maria on 06.02.2017.
 */

package eldis.mdl.examples.ex1

import eldis.react.ReactNode

import scalajs.js
import eldis.react._
import org.scalajs.dom
import eldis.react.mdl.components._
import eldis.react.vdom.prefix_<^.<

object Main extends js.JSApp {

  def render(): ReactNode = {
    <.div()(
      <.h4()("The Icon:"),
      Icon(Icon.Props(name = "save", key = Some("save_icon"))),
      <.h4()("The checkbox: "),
      Checkbox("This is checkbox"),
      <.h4()("The buttons: "),
      Button("Button"), Button("Button", Button.Props(raised = Some(true))),
      <.h4()("The flat buttons: "),
      FlatButton("Flat"), FlatButton("Flat", disabled = Some(true)),
      <.h4()("The raised buttons: "),
      RaisedButton("Raised"), RaisedButton("Raised", disabled = Some(true)),
      <.h4()("The colored buttons: "),
      ColoredButton("Colored"), ColoredButton("Colored", disabled = Some(true)),
      <.h4()("The accent buttons: "),
      AccentButton("Accent"), AccentButton("Accent", disabled = Some(true)),
      <.h4()("The FABbuttons: "),
      FABButton("add"), FABButton("add", FABButton.Props(colored = Some(true), mini = Some(true))),
      <.h4()("The icon buttons: "),
      IconButton("mood"), IconButton(IconButton.Props(icon = Some("mood"), colored = Some(true))),
      <.h4()("The radio buttons: "),
      Radio(label = "Radio 1", value = "value 1", groupName = "radio group 1", defaultChecked = Some(true)),
      Radio(label = "Radio 2", value = "value 2", groupName = "radio group 1"),
      <.h4()("The password field: "),
      Password("Enter the password"),
      <.h4()("The textArea: "), TextArea(2), TextArea(Text.Props(label = "", defaultValue = Some("Это многострочное поле для ввода"))),
      <.h4()("The date field: "),
      Date(), Date("", "21.12.2008", (s: String) => println(s)), Date(Text.Props(label = "", required = Some(true))),
      <.h4()("The time field: "),
      Time(), Time("", "13:08"), Time(Text.Props(label = "", required = Some(true))),
      <.h4()("The integer field: "),
      Integer(), Integer("10"), Integer(Text.Props(label = "", required = Some(true))),
      <.h4()("The float field: "),
      Float(), Float("13.856"), Float(Text.Props(label = "", required = Some(true))),
      <.h4()("The fraction field: "),
      Fraction(), Fraction("15 6/9"), Fraction(Text.Props(label = "", required = Some(true)))
    )
  }

  def main(): Unit = {
    ReactDOM.render(
      render(),
      dom.document.getElementById("root")
    )
  }

}