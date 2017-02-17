/**
 * Created by maria on 17.02.2017.
 */
/**
 * Created by maria on 13.02.2017.
 */
package eldis.react.mdl.components

import scalajs.js
import js.JSConverters._
import js.annotation.{ JSImport, ScalaJSDefined }
import eldis.react._
import eldis.react.mdl._

object AutoComplete {
  object AutoCompleteField {
    @js.native
    trait Props extends CommonProps {
      val label: String = js.native
      val items: js.Array[js.Any]
      val dataIndex: String = js.native
      val valueIndex: String = js.native
      val value: js.UndefOr[js.Any] = js.native
      val onChange: js.UndefOr[js.Function1[js.Any, Unit]] = js.native
    }

    object Props {
      def apply[ID](
        label: String,
        items: js.Array[js.Any],
        dataIndex: String,
        valueIndex: String,
        value: Option[js.Any] = None,
        onChange: Option[ID => Unit] = None,
        className: Seq[String] = Nil,
        key: Option[String] = None,
        style: Option[js.Object] = None
      ) =
        js.Dynamic.literal(
          label = label,
          items = items,
          dataIndex = dataIndex,
          valueIndex = valueIndex,
          value = value.orUndefined,
          onChange = onChange.orUndefined,
          className = fillClassAttr(className).orUndefined,
          key = key.orUndefined,
          style = style.orUndefined
        ).asInstanceOf[Props]
    }

    @JSImport("react-mdl-extra", "AutoComplete")
    @js.native
    object Component extends JSComponent[Props]
    def apply(props: Props) = React.createElement(Component, props)
  }

  case class Props[R, ID](
    label: String,
    items: Seq[R], // the sequence of the rows
    valueIndex: String, // the key in row object to get the row id /the chosen value of the AutoComplete
    dataIndex: String, // the key in row object to get the row data
    className: Seq[String] = Nil,
    key: Option[String] = None,
    style: Option[js.Object] = None,
    value: Option[ID] = None,
    onChange: Option[ID => Unit] = None
  )

  type PropsImpl[R, ID] = (Props[R, ID], RowGetters[R, ID])

  @ScalaJSDefined
  class AutoComplete[R, ID] extends Component[PropsImpl[R, ID]]("RefAutoComplete1") {

    case class State(value: Option[ID])

    def initialState: State = State(None)

    def onChange(e: ID): Unit = {
      this.props match {
        case (props, _) => {
          setState(state.copy(value = Some(e)))
          this.props._1.onChange.map(h => h(e)).getOrElse(Unit)
        }
      }
    }

    def render() = {
      val p = this.props

      AutoCompleteField(
        AutoCompleteField.Props[ID](
          label = p._1.label,
          items = p._1.items.map(i => p._2.rowToJsAny(i)).toJSArray,
          dataIndex = p._1.dataIndex,
          valueIndex = p._1.valueIndex,
          value = p._1.value.orElse(state.value).map(v => p._2.idToJsAny(v)),
          onChange = Some((e: ID) => onChange(e)),
          className = p._1.className
        )
      )
    }
  }

  def apply[R, ID](props: Props[R, ID])(implicit rg: RowGetters[R, ID]) =
    new AutoComplete[R, ID]()((props, rg))
}

