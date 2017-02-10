/**
 * Created by maria on 06.02.2017.
 */

package eldis.mdl.examples.ex1

import eldis.react.ReactNode

import scalajs.js
import eldis.react._
import org.scalajs.dom
import eldis.react.mdl.components._
import eldis.react.vdom.{ Style, attr, AttrValue }
import vdom.prefix_<^.<
import scala.scalajs.js.annotation.ScalaJSDefined

object Main extends js.JSApp {
  case class TestItem(id: Int, title: String, getItems: () => List[ReactDOMElement])

  @ScalaJSDefined
  class TestForm extends Component[Nothing]("TestForm") {
    case class State(curr: Int, items: List[TestItem])

    def createWelcome = List(
      <.div()("Select any item in a menu")
    )
    def createLabels = List(
      Label("This is label"),
      <.br()(),
      Label(Label.Props(label = "This is label with style", style = Some(Style(attr("backgroundColor") := AttrValue("red")).toJs)))
    )

    def createIcons = List(
      Icon(Icon.Props(name = "save", key = Some("save_icon")))
    )
    def createCheckboxs = List(
      Checkbox("This is checkbox")
    )
    def createButtons = List(
      Button("Button"),
      Button("Button", Button.Props(raised = Some(true)))
    )
    def createFlatButtons = List(
      FlatButton("Flat"),
      FlatButton("Flat", disabled = Some(true))
    )
    def createRaisedButtons = List(
      RaisedButton("Raised"),
      RaisedButton("Raised", disabled = Some(true))
    )
    def createColoredButtons = List(
      ColoredButton("Colored"),
      ColoredButton("Colored", disabled = Some(true))
    )
    def createAccentButtons = List(
      AccentButton("Accent"),
      AccentButton("Accent", disabled = Some(true))
    )
    def createFABButtons = List(
      FABButton("add"),
      FABButton("add", FABButton.Props(colored = Some(true), mini = Some(true)))
    )
    def createIconButtons = List(
      IconButton("mood"),
      IconButton(IconButton.Props(icon = Some("mood"), colored = Some(true)))
    )
    def createRadios = List(
      Radio(label = "Radio 1.1", value = "value 1-1", groupName = "radio group 1", defaultChecked = Some(true)),
      Radio(label = "Radio 1.2", value = "value 1-2", groupName = "radio group 1"),
      <.br()(), <.br()(),
      Radio(label = "Radio 2.1", value = "value 2-1", groupName = "radio group 2", defaultChecked = Some(true)),
      Radio(label = "Radio 2.2", value = "value 2-2", groupName = "radio group 2")
    )
    def createPasswords = List(
      Password("Enter the password")
    )
    def createTextAreas = List(
      TextArea(2),
      TextArea(Text.Props(label = "", defaultValue = Some("Это многострочное поле для ввода")))
    )
    def createDates = List(
      Date(),
      Date("", "21.12.2008", (s: String) => println(s)),
      Date(Text.Props(label = "", required = Some(true)))
    )
    def createTimes = List(
      Time(),
      Time("", "13:08"),
      Time(Text.Props(label = "", required = Some(true)))
    )
    def createIntegers = List(
      Integer(),
      Integer("10"),
      Integer(Text.Props(label = "", required = Some(true)))
    )
    def createFloats = List(
      Float(),
      Float("13.856"),
      Float(Text.Props(label = "", required = Some(true)))
    )
    def createFractions = List(
      Fraction(),
      Fraction("15 6/9"),
      Fraction(Text.Props(label = "", required = Some(true)))
    )

    def initialState: State = State(0, List(
      TestItem(0, "Welcome", () => createWelcome),
      TestItem(1, "Icon", () => createIcons),
      TestItem(2, "Checkbox", () => createCheckboxs),
      TestItem(3, "Button", () => createButtons),
      TestItem(4, "FlatButton", () => createFlatButtons),
      TestItem(5, "RaisedButton", () => createRaisedButtons),
      TestItem(6, "ColoredButton", () => createColoredButtons),
      TestItem(7, "AccentButton", () => createAccentButtons),
      TestItem(8, "FABButton", () => createFABButtons),
      TestItem(9, "IconButton", () => createIconButtons),
      TestItem(10, "Radio", () => createRadios),
      TestItem(11, "Password", () => createPasswords),
      TestItem(12, "TextArea", () => createTextAreas),
      TestItem(13, "Date", () => createDates),
      TestItem(14, "Time", () => createTimes),
      TestItem(15, "Integer", () => createIntegers),
      TestItem(16, "Float", () => createFloats),
      TestItem(17, "Fraction", () => createFractions),
      TestItem(18, "Labels", () => createLabels)
    ))

    override def render(): ReactNode = {
      <.div()(
        Card(
          Card.Props(
            shadow = Some(0),
            style = Some(js.Dynamic.literal(
              width = "700px",
              margin = "auto"
            ).asInstanceOf[js.Object])
          )
        )(
            CardTitle(
              CardTitle.Props(style = Some(Style(attr("backgroundColor") := AttrValue("ghostwhite")).toJs))
            )(state.items.filter(it => it.id == state.curr)(0).title),

            CardText()(<.div(attr("key") := AttrValue(state.curr))(state.items.filter(it => it.id == state.curr)(0).getItems(): _*)),

            CardActions(
              CardActions.Props(style = Some(Style(attr("backgroundColor") := AttrValue("ghostwhite")).toJs))
            )(state.items.map(it => FlatButton(it.title, Some(() => setState(state.copy(curr = it.id))))): _*)
          )
      )
    }
  }
  @ScalaJSDefined
  object TestForm extends TestForm

  def main(): Unit = {
    ReactDOM.render(
      TestForm(),
      dom.document.getElementById("root")
    )
  }
}