package io.udash.bootstrap
package navs

import io.udash._
import io.udash.bootstrap.UdashBootstrap.ComponentId
import org.scalajs.dom

import scalatags.JsDom.all._
import scalatags.JsDom.tags2

class UdashNavbar[ItemType, ElemType <: Property[ItemType]] private
                 (navbarStyle: BootstrapStyles.BootstrapClass, override val componentId: ComponentId)
                 (brand: dom.Element, nav: UdashNav[ItemType, ElemType])
  extends UdashBootstrapComponent {

  import BootstrapTags._

  private val collapseId = UdashBootstrap.newId()

  override lazy val render: dom.Element =
    tags2.nav(id := componentId, BootstrapStyles.Navigation.navbar, navbarStyle)(
      div(BootstrapStyles.containerFluid)(
        div(BootstrapStyles.Navigation.navbarHeader)(
          button(
            tpe := "button", dataToggle := "collapse", dataTarget := s"#$collapseId", aria.expanded := false,
            BootstrapStyles.Navigation.navbarToggle, BootstrapStyles.collapsed
          )(
            span(BootstrapStyles.Visibility.srOnly)("Toggle navigation"),
            span(BootstrapStyles.iconBar), span(BootstrapStyles.iconBar), span(BootstrapStyles.iconBar)
          ),
          brand
        ),
        div(id := collapseId, BootstrapStyles.Collapse.collapse, BootstrapStyles.Navigation.navbarCollapse)(nav.render)
      )
    ).render
}

object UdashNavbar {
  /**
    * Creates default responsive navigation bar. More: <a href="http://getbootstrap.com/components/#navbar">Bootstrap Docs</a>.
    *
    * @param brand Brand DOM element.
    * @param nav Navigation component
    * @param componentId Id of the root DOM node.
    * @tparam ItemType Single element type in `items`.
    * @tparam ElemType Type of the property containing every element in `items` sequence.
    * @return `UdashNavbar` component, call render to create DOM element.
    */
  def apply[ItemType, ElemType <: Property[ItemType]]
           (brand: dom.Element, nav: UdashNav[ItemType, ElemType], componentId: ComponentId = UdashBootstrap.newId()): UdashNavbar[ItemType, ElemType] =
    new UdashNavbar(BootstrapStyles.Navigation.navbarDefault, componentId)(brand, nav)

  /**
    * Creates responsive navigation bar with inverted colors. More: <a href="http://getbootstrap.com/components/#navbar">Bootstrap Docs</a>.
    *
    * @param brand Brand DOM element.
    * @param nav Navigation component
    * @param componentId Id of the root DOM node.
    * @tparam ItemType Single element type in `items`.
    * @tparam ElemType Type of the property containing every element in `items` sequence.
    * @return `UdashNavbar` component, call render to create DOM element.
    */
  def inverted[ItemType, ElemType <: Property[ItemType]]
              (brand: dom.Element, nav: UdashNav[ItemType, ElemType], componentId: ComponentId = UdashBootstrap.newId()): UdashNavbar[ItemType, ElemType] =
    new UdashNavbar(BootstrapStyles.Navigation.navbarInverse, componentId)(brand, nav)
}