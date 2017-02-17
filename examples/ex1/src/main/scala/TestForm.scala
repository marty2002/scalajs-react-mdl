/**
 * Created by kirill on 14.02.2017.
 */

package eldis.mdl.examples.ex1

import eldis.react._
import eldis.react.mdl.components._
import eldis.react.mdl.components.RowGetters
import eldis.react.vdom._
import eldis.react.vdom.prefix_<^._
import scalajs.js
import js.annotation.ScalaJSDefined

@ScalaJSDefined
class TestForm extends Component[Nothing]("TestForm") {

  sealed trait DialogType
  object DialogType {
    case object SimpleDialog extends DialogType
    case object SecondDialog extends DialogType
    case object DialogWithCancel extends DialogType
  }

  def setIsDialogOpen(t: Option[DialogType]) = setState(state.copy(dlgType = t))
  case class State(dlgType: Option[DialogType])
  def initialState: State = State(None)

  case class RefRow(id: Int, value: String)

  implicit val rg: RowGetters[RefRow, Int] = new RowGetters[RefRow, Int] {
    def getId(r: RefRow) = r.id
    def getDesc(r: RefRow) = r.value
    override def rowToJsAny(r: RefRow): js.Any = js.Dynamic.literal(
      id = r.id, value = r.value
    ).asInstanceOf[js.Any]
  }

  val rows = List(
    RefRow(1, "One"),
    RefRow(2, "Two"),
    RefRow(3, "Three"),
    RefRow(4, "Four"),
    RefRow(5, "Five"),
    RefRow(6, "Six"),
    RefRow(7, "Seven"),
    RefRow(8, "Eight"),
    RefRow(9, "Nine"),
    RefRow(10, "Ten")
  )

  def render() =
    <.div()(
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Checkbox:")),
        <.div(^.className := "margin")(
          Checkbox(
            Checkbox.Props(
              label = Some("This is a checkbox"),
              onChange = Some(e => println(e.target.value)),
              defaultChecked = Some(true)
            )
          )
        )
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Radio:")),
        <.div(^.className := "margin")(
          Radio(
            "Radio 1",
            "radio group 1",
            "value 0",
            Some(
              (e: ReactEventI) => {
                org.scalajs.dom.window.alert("value of radio 1 = \"" + e.target.value + "\"")
              }
            ),
            defaultChecked = Some(true)
          )
        ),
        <.div(^.className := "margin")(
          Radio(
            "Radio 2",
            "radio group 1",
            "value 1",
            Some(
              (e: ReactEventI) => {
                org.scalajs.dom.window.alert("value of radio 2 = \"" + e.target.value + "\"")
              }
            )
          )
        )
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Buttons:")),
        <.div(^.className := "margin")(FlatButton("Flat", onClick = Some(() => setIsDialogOpen(Some(DialogType.SimpleDialog))))),
        <.div(^.className := "margin")(RaisedButton("Raised", onClick = Some(() => setIsDialogOpen(Some(DialogType.SecondDialog))))),
        <.div(^.className := "margin")(ColoredButton("Colored", onClick = Some(() => setIsDialogOpen(Some(DialogType.DialogWithCancel))))),
        <.div(^.className := "margin")(AccentButton("Accent", onClick = Some(() => setIsDialogOpen(Some(DialogType.SimpleDialog))))),
        <.div(^.className := "margin")(FABButton("warning", onClick = Some(() => setIsDialogOpen(Some(DialogType.SimpleDialog))))),
        <.div(^.className := "margin")(FABButton("warning", onClick = Some(() => setIsDialogOpen(Some(DialogType.SimpleDialog))))),
        <.div(^.className := "margin")(IconButton("warning", onClick = Some(() => setIsDialogOpen(Some(DialogType.SimpleDialog)))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Text control:")),
        <.div(^.className := "colRight")(Text(Text.Props(label = "Enter text", defaultValue = Some("123"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Password:")),
        <.div(^.className := "colRight")(Password(Text.Props(label = "Enter password", defaultValue = Some("123"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Textarea:")),
        <.div(^.className := "colRight")(TextArea(Text.Props(label = "", rows = Some(3), defaultValue = Some("123"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Integer:")),
        <.div(^.className := "colRight")(Integer(Text.Props(label = "", defaultValue = Some("12"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Float:")),
        <.div(^.className := "colRight")(Float(Text.Props(label = "", defaultValue = Some("12.12"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Fraction:")),
        <.div(^.className := "colRight")(Fraction(Text.Props(label = "", defaultValue = Some("12/13"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Date:")),
        <.div(^.className := "colRight")(Date(Text.Props(label = "", defaultValue = Some("12.12.2012"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Time:")),
        <.div(^.className := "colRight")(Time(Text.Props(label = "", defaultValue = Some("12:12"), className = Seq("controlWidth"))))
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("AutoComplete")),
        <.div(^.className := "colRight")(
          AutoComplete(AutoComplete.Props[RefRow, Int](
            label = "Select value...",
            items = rows,
            dataIndex = "value",
            valueIndex = "id"
          ))
        )
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Reference")),
        <.div(^.className := "colRight")(
          Reference(Reference.Props[RefRow, Int](
            label = "Select value...",
            ref = rows
          ))
        )
      ),
      <.div(^.className := "form-row")(
        <.div(^.className := "col-7-em")(Label("Menu:")),
        <.div(^.className := "margin")(
          Menu(id = "test", iconName = "more_vert", valign = Some("top"), align = Some("left"))(
            MenuItem(label = "Some Action 1", onClick = Some(() => println("click1"))),
            MenuItem(label = "Some Action 2", disabled = Some(true), onClick = Some(() => println("click2")))
          )
        )
      ),
      {
        showDialog()
      }
    )

  def showDialog() =
    state.dlgType match {
      case Some(DialogType.SimpleDialog) =>
        Dialog(Some("Simple"), true, className = "width-30-em" :: Nil,
          Seq(
            RaisedButton("Some Button"),
            RaisedButton("CLOSE", onClick = Some(() => setState(state.copy(dlgType = None))))
          ))(Label("Simple Dialog"))

      case Some(DialogType.SecondDialog) =>
        Dialog(Some("fullWidth Buttons"), true, "width-30-em" :: Nil,
          Seq(
            RaisedButton("Some Button"),
            RaisedButton("CLOSE", onClick = Some(() => setState(state.copy(dlgType = None))))
          ),
          actionsProps = DialogActions.Props(fullWidth = Some(true)))(Label("Dialog with fullWidth Buttons"))

      case Some(DialogType.DialogWithCancel) =>
        Dialog(Some("With Cancel"), true, "width-30-em" :: Nil,
          Seq(
            RaisedButton("Some Button"),
            RaisedButton("CLOSE", onClick = Some(() => setState(state.copy(dlgType = None))))
          ),
          Some(() => setState(state.copy(dlgType = None))))(Label("Dialog with cancel action"))
      case _ => EmptyNode
    }

}
@ScalaJSDefined
object TestForm extends TestForm