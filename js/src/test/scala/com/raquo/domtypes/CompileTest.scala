package com.raquo.domtypes

import com.raquo.domtypes.generic.builders.canonical.CanonicalReflectedHtmlAttrBuilder.ReflectedAttr
import com.raquo.domtypes.generic.builders.canonical.{CanonicalEventPropBuilder, CanonicalHtmlAttrBuilder, CanonicalPropBuilder, CanonicalReflectedHtmlAttrBuilder, CanonicalSvgAttrBuilder}
import com.raquo.domtypes.generic.builders.{HtmlTagBuilder, StyleBuilders, SvgTagBuilder, Tag}
import com.raquo.domtypes.generic.defs.attrs.{AriaAttrs, HtmlAttrs, SvgAttrs}
import com.raquo.domtypes.generic.defs.complex.canonical.{CanonicalComplexHtmlKeys, CanonicalComplexSvgKeys}
import com.raquo.domtypes.generic.defs.props.Props
import com.raquo.domtypes.generic.defs.reflectedAttrs.ReflectedHtmlAttrs
import com.raquo.domtypes.generic.defs.styles.{Styles, Styles2}
import com.raquo.domtypes.generic.keys.{EventProp, HtmlAttr, Prop, Style, SvgAttr}
import com.raquo.domtypes.jsdom.defs.eventProps.{ClipboardEventProps, DocumentOnlyEventProps, ErrorEventProps, FormEventProps, HTMLElementEventProps, KeyboardEventProps, MediaEventProps, MiscellaneousEventProps, MouseEventProps, PointerEventProps, WindowOnlyEventProps}
import com.raquo.domtypes.jsdom.defs.tags.{DocumentTags, EmbedTags, FormTags, GroupingTags, MiscTags, SectionTags, SvgTags, TableTags, TextTags}
import org.scalajs.dom

/** We just want to make sure that this compiles. */
class CompileTest {

  trait SomeStyleSetter

  trait SomeStyleBuilders extends StyleBuilders[SomeStyleSetter] {

    override def buildDoubleStyleSetter(style: Style[Double], value: Double): SomeStyleSetter = ???

    override def buildIntStyleSetter(style: Style[Int], value: Int): SomeStyleSetter = ???

    override def buildStringStyleSetter(style: Style[_], value: String): SomeStyleSetter = ???
  }

  trait SomeTagBuilders extends HtmlTagBuilder[Tag, dom.Element] with SvgTagBuilder[Tag, dom.svg.Element] {

    override def htmlTag[Ref <: dom.Element](tagName: String, void: Boolean): Tag[Ref] = ???

    override def svgTag[Ref <: dom.svg.Element](tagName: String, void: Boolean): Tag[Ref] = ???
  }

  object Bundle
    extends CanonicalComplexHtmlKeys[ReflectedAttr, HtmlAttr, Prop]
    // Attrs
    with HtmlAttrs[HtmlAttr]
    // Event Props
    with ClipboardEventProps[EventProp]
    with ErrorEventProps[EventProp]
    with FormEventProps[EventProp]
    with KeyboardEventProps[EventProp]
    with MediaEventProps[EventProp]
    with MiscellaneousEventProps[EventProp]
    with MouseEventProps[EventProp]
    with PointerEventProps[EventProp]
    with DocumentOnlyEventProps[EventProp]
    with WindowOnlyEventProps[EventProp]
    // Props
    with Props[Prop]
    // Reflected Attrs
    with ReflectedHtmlAttrs[ReflectedAttr]
    // Styles
    with Styles[SomeStyleSetter]
    with Styles2[SomeStyleSetter]
    // Tags
    with DocumentTags[Tag]
    with EmbedTags[Tag]
    with FormTags[Tag]
    with GroupingTags[Tag]
    with MiscTags[Tag]
    with SectionTags[Tag]
    with TableTags[Tag]
    with TextTags[Tag]
    // Concrete Builders
    with CanonicalHtmlAttrBuilder
    with CanonicalReflectedHtmlAttrBuilder
    with CanonicalEventPropBuilder[dom.Event]
    with CanonicalPropBuilder
    with CanonicalSvgAttrBuilder // Not needed but want to ensure compatibility
    with SomeStyleBuilders
    with SomeTagBuilders {

    object aria
      extends AriaAttrs[HtmlAttr]
      with CanonicalHtmlAttrBuilder

    object svg
      extends SvgTags[Tag]
      with CanonicalComplexSvgKeys[SvgAttr]
      with SvgAttrs[SvgAttr]
      with CanonicalSvgAttrBuilder
      with SomeTagBuilders
  }

  object SpecificEventProps
    extends HTMLElementEventProps[EventProp]
    with CanonicalEventPropBuilder[dom.Event]
}
