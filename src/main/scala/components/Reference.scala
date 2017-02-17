/**
 * Created by maria on 13.02.2017.
 */
package eldis.react.mdl.components

import scalajs.js
import js.JSConverters._
import js.annotation.{ JSImport, ScalaJSDefined }
import eldis.react._
import eldis.react.mdl._

object Reference {
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

  object SelectField {

    @js.native
    trait Props extends CommonProps {
      val label: String = js.native
      val value: js.UndefOr[js.Any] = js.native
      val onChange: js.UndefOr[js.Function1[js.Any, Unit]] = js.native
    }

    object Props {
      def apply(
        label: String,
        value: Option[js.Any] = None,
        onChange: Option[Int => Unit] = None,
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

  trait RowGetters[R] {
    def id(row: R): Int
    def desc(row: R): String
  }

  case class Props[R](
    className: Seq[String] = Nil,
    key: Option[String] = None,
    style: Option[js.Object] = None,
    label: String,
    ref: Seq[R],
    value: Option[Int] = None,
    onChange: Option[Int => Unit] = None
  )

  type PropsImpl[R] = (Props[R], RowGetters[R])

  @ScalaJSDefined
  class Reference[R] extends Component[PropsImpl[R]]("Reference") {

    case class State(value: Option[Int])

    def initialState: State = State(None)

    def onChange(e: Int): Unit = {
      this.props match {
        case (props, _) => {
          setState(state.copy(value = Some(e)))
          this.props._1.onChange.map(h => h(e)).getOrElse(Unit)
        }
      }
    }

    def render() = {
      val p = this.props
      val ch = p._1.ref.map(row => Option(Option.Props(value = p._2.id(row)))(p._2.desc(row)))

      SelectField(
        SelectField.Props(
          label = p._1.label,
          value = p._1.value.orElse(state.value).map(v => v),
          onChange = Some((e: Int) => onChange(e)),
          className = p._1.className
        )
      )(ch: _*)
    }
  }

  def apply[R](props: Props[R])(implicit rg: RowGetters[R]) =
    new Reference[R]()((props, rg))
}
