/**
 * Created by maria on 13.02.2017.
 */
package eldis.react.mdl.components

import scalajs.js
import js.JSConverters._
import js.annotation.{ JSImport, ScalaJSDefined }
import eldis.react._
import eldis.react.mdl._

object Option {

  @js.native
  trait Props extends js.Any {
    val key: js.Any = js.native
    val value: js.Any = js.native
    val onClick: js.UndefOr[js.Any] = js.native
    val className: js.UndefOr[String] = js.native
  }

  object Props {
    def apply(
      value: js.Any,
      className: Option[String] = None,
      onClick: js.UndefOr[js.Any] = js.undefined
    ) =
      js.Dynamic.literal(
        key = value, // it's not a typo. Yep, key = value
        value = value,
        onClick = onClick,
        className = className.orUndefined
      ).asInstanceOf[Props]
  }

  @JSImport("react-mdl-extra", "Option")
  @js.native
  object Component extends JSComponent[Props]
  def apply(props: Props)(ch: ReactNode) = React.createElement(Component, props, ch)
}

trait RowGetters[R, ID] {
  def getId(row: R): ID
  def getDesc(row: R): String
  def toJsAny(id: ID): js.Any = id.asInstanceOf[js.Any]
  def fromJsAny(id: js.Any): ID = id.asInstanceOf[ID]
}

object Reference {

  object SelectField {

    @js.native
    trait Props extends CommonProps {
      val label: String = js.native
      val value: js.UndefOr[js.Any] = js.native
      val onChange: js.UndefOr[js.Function1[js.Any, Unit]] = js.native
    }

    object Props {
      def apply[ID](
        label: String,
        value: Option[js.Any] = None,
        onChange: Option[ID => Unit] = None,
        className: Seq[String] = Nil,
        key: Option[String] = None,
        style: Option[js.Object] = None
      ) =
        js.Dynamic.literal(
          label = label,
          value = value.orUndefined,
          onChange = onChange.orUndefined,
          className = fillClassAttr(className).orUndefined,
          key = key.orUndefined,
          style = style.orUndefined
        ).asInstanceOf[Props]
    }

    @JSImport("react-mdl-extra", "SelectField")
    @js.native
    object Component extends JSComponent[Props]
    def apply(props: Props)(ch: ReactNode*) = React.createElement(Component, props, ch: _*)
  }

  case class Props[R, ID](
    className: Seq[String] = Nil,
    key: Option[String] = None,
    style: Option[js.Object] = None,
    label: String,
    ref: Seq[R],
    value: Option[ID] = None,
    onChange: Option[ID => Unit] = None
  )

  type PropsImpl[R, ID] = (Props[R, ID], RowGetters[R, ID])

  @ScalaJSDefined
  class Reference[R, ID] extends Component[PropsImpl[R, ID]]("Reference") {

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
      val ch = p._1.ref.map(row => Option(Option.Props(value = p._2.toJsAny(p._2.getId(row))))(p._2.getDesc(row)))

      SelectField(
        SelectField.Props[ID](
          label = p._1.label,
          value = p._1.value.orElse(state.value).map(v => p._2.toJsAny(v)),
          onChange = Some((e: ID) => onChange(e)),
          className = p._1.className
        )
      )(ch: _*)
    }
  }

  def apply[R, ID](props: Props[R, ID])(implicit rg: RowGetters[R, ID]) =
    new Reference[R, ID]()((props, rg))
}
