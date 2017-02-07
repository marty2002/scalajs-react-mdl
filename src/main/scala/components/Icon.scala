/**
 * Created by maria on 06.02.2017.
 */
package eldis.react.mdl.components

import scalajs.js
import js.annotation.JSImport
import eldis.react._
import eldis.react.mdl._

object Icon {
  import js.JSConverters._

  object Impl {
    @js.native
    trait Props extends ReactMDLCommonPropsJS {
      val name: js.UndefOr[String] = js.native
    }

    object Props {
      def apply(
        className: Seq[String] = Nil,
        key: Option[String] = None,
        style: Option[js.Object] = None,
        name: String
      ) =
        js.Dynamic.literal(
          className = fillClassAttr(className).orUndefined,
          key = key.orUndefined,
          style = style.orUndefined,
          name = name
        ).asInstanceOf[Props]
    }

    @JSImport("react-mdl", "Icon")
    @js.native
    object Component extends JSComponent[Props]
    def apply(p: Props) = React.createElement(Component, p)
  }

  case class Props(
    name: String,
    className: Seq[String] = Nil,
    key: Option[String] = None,
    style: Option[js.Object] = None
  ) extends ComponentCommonProps

  def apply(props: Props) =
    Impl(
      Impl.Props(
        className = props.className,
        key = props.key,
        style = props.style,
        name = props.name
      )
    )
}

