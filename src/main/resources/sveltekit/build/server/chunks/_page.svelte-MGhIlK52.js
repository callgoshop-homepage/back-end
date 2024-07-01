import { c as create_ssr_component, v as validate_component, b as compute_rest_props, f as spread, h as escape_object, i as escape_attribute_value, d as add_attribute, e as escape } from './ssr-DhdZ8Nh2.js';
import { t as twMerge } from './bundle-mjs-DVkqfP1f.js';

const Footer = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["footerType"]);
  let { footerType = void 0 } = $$props;
  let footerClass = twMerge(footerType === "sitemap" && "bg-gray-800", footerType === "socialmedia" && "p-4 bg-white sm:p-6 dark:bg-gray-800", footerType === "logo" && "p-4 bg-white rounded-lg shadow md:px-6 md:py-8 dark:bg-gray-800", footerType === "default" && "p-4 bg-white rounded-lg shadow md:flex md:items-center md:justify-between md:p-6 dark:bg-gray-800", $$props.class);
  if ($$props.footerType === void 0 && $$bindings.footerType && footerType !== void 0) $$bindings.footerType(footerType);
  return `<footer${spread(
    [
      escape_object($$restProps),
      {
        class: escape_attribute_value(footerClass)
      }
    ],
    {}
  )}>${slots.default ? slots.default({}) : ``}</footer> `;
});
const FooterBrand = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["aClass", "spanClass", "imgClass", "href", "src", "alt", "name", "target"]);
  let { aClass = "flex items-center" } = $$props;
  let { spanClass = "self-center text-2xl font-semibold whitespace-nowrap dark:text-white" } = $$props;
  let { imgClass = "me-3 h-8" } = $$props;
  let { href = "" } = $$props;
  let { src = "" } = $$props;
  let { alt = "" } = $$props;
  let { name = "" } = $$props;
  let { target = void 0 } = $$props;
  let aCls = twMerge(aClass, $$props.classA);
  let spanCls = twMerge(spanClass, $$props.classSpan);
  let imgCls = twMerge(imgClass, $$props.classImg);
  if ($$props.aClass === void 0 && $$bindings.aClass && aClass !== void 0) $$bindings.aClass(aClass);
  if ($$props.spanClass === void 0 && $$bindings.spanClass && spanClass !== void 0) $$bindings.spanClass(spanClass);
  if ($$props.imgClass === void 0 && $$bindings.imgClass && imgClass !== void 0) $$bindings.imgClass(imgClass);
  if ($$props.href === void 0 && $$bindings.href && href !== void 0) $$bindings.href(href);
  if ($$props.src === void 0 && $$bindings.src && src !== void 0) $$bindings.src(src);
  if ($$props.alt === void 0 && $$bindings.alt && alt !== void 0) $$bindings.alt(alt);
  if ($$props.name === void 0 && $$bindings.name && name !== void 0) $$bindings.name(name);
  if ($$props.target === void 0 && $$bindings.target && target !== void 0) $$bindings.target(target);
  return `${href ? `<a${spread(
    [
      escape_object($$restProps),
      { href: escape_attribute_value(href) },
      { target: escape_attribute_value(target) },
      { class: escape_attribute_value(aCls) }
    ],
    {}
  )}><img${add_attribute("src", src, 0)}${add_attribute("class", imgCls, 0)}${add_attribute("alt", alt, 0)}> <span${add_attribute("class", spanCls, 0)}>${escape(name)}</span> ${slots.default ? slots.default({}) : ``}</a>` : `<img${spread(
    [
      escape_object($$restProps),
      { src: escape_attribute_value(src) },
      { class: escape_attribute_value(imgCls) },
      { alt: escape_attribute_value(alt) }
    ],
    {}
  )}>`} `;
});
const FooterCopyright = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["spanClass", "aClass", "year", "href", "by", "target", "copyrightMessage"]);
  let { spanClass = "block text-sm text-gray-500 sm:text-center dark:text-gray-400" } = $$props;
  let { aClass = "hover:underline" } = $$props;
  let { year = /* @__PURE__ */ (/* @__PURE__ */ new Date()).getFullYear() } = $$props;
  let { href = "" } = $$props;
  let { by = "" } = $$props;
  let { target = void 0 } = $$props;
  let { copyrightMessage = "All Rights Reserved." } = $$props;
  let spanCls = twMerge(spanClass, $$props.classSpan);
  let aCls = twMerge(aClass, $$props.classA);
  if ($$props.spanClass === void 0 && $$bindings.spanClass && spanClass !== void 0) $$bindings.spanClass(spanClass);
  if ($$props.aClass === void 0 && $$bindings.aClass && aClass !== void 0) $$bindings.aClass(aClass);
  if ($$props.year === void 0 && $$bindings.year && year !== void 0) $$bindings.year(year);
  if ($$props.href === void 0 && $$bindings.href && href !== void 0) $$bindings.href(href);
  if ($$props.by === void 0 && $$bindings.by && by !== void 0) $$bindings.by(by);
  if ($$props.target === void 0 && $$bindings.target && target !== void 0) $$bindings.target(target);
  if ($$props.copyrightMessage === void 0 && $$bindings.copyrightMessage && copyrightMessage !== void 0) $$bindings.copyrightMessage(copyrightMessage);
  return `<span${add_attribute("class", spanCls, 0)}>© ${escape(year)} ${href ? `<a${spread(
    [
      escape_object($$restProps),
      { href: escape_attribute_value(href) },
      { target: escape_attribute_value(target) },
      { class: escape_attribute_value(aCls) }
    ],
    {}
  )}>${escape(by)}</a>` : `<span class="ms-1">${escape(by)}</span>`} ${escape(copyrightMessage)}</span> `;
});
const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  return `${validate_component(Footer, "Footer").$$render(
    $$result,
    {
      footerType: "logo",
      class: "custom-shadow"
    },
    {},
    {
      default: () => {
        return `<div class="sm:flex sm:items-center sm:justify-center">${validate_component(FooterBrand, "FooterBrand").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/",
            src: "/img/callgo_logo.png",
            alt: "Flowbite Logo",
            name: "Callgo"
          },
          {},
          {}
        )}</div> <p class="mt-2 text-sm sm:flex sm:items-center sm:justify-center" data-svelte-h="svelte-zo3ml0">대표이사 김경수 l 사업자등록번호 118-86-02166</p> <p class="mt-2 text-sm sm:flex sm:items-center sm:justify-center" data-svelte-h="svelte-1dm0vaq">충청남도 천안시 서북구 불당25로 226 골든스퀘어Ⅱ 716호,717호</p> <hr class="my-6 border-gray-200 sm:mx-auto lg:my-6 dark:border-gray-700"> ${validate_component(FooterCopyright, "FooterCopyright").$$render(
          $$result,
          {
            by: "Copyright ⓒ 2022 Stella's Lab All Rights Reserved"
          },
          {},
          {}
        )}`;
      }
    }
  )}`;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-MGhIlK52.js.map
