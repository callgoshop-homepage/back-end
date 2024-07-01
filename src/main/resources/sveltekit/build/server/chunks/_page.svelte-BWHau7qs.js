import { c as create_ssr_component, v as validate_component, b as compute_rest_props, a as subscribe, s as setContext, f as spread, i as escape_attribute_value, h as escape_object, d as add_attribute } from './ssr-DhdZ8Nh2.js';
import Page$2 from './_page.svelte-YRd7Tlb1.js';
import Page$1 from './_page.svelte-4X2BREW8.js';
import { B as Button } from './Button-DpbBnAHs.js';
import { F as Frame } from './Frame-NNL14bzx.js';
import { t as twMerge } from './bundle-mjs-DVkqfP1f.js';
import { w as writable } from './index-t0gNv4Rz.js';
import { i as instance } from './api-6cxZS4_H.js';
import './ChevronDownOutline-B5LNVizg.js';
import './Popper-CzsQEtaU.js';
import './Input-t7C8Dt9y.js';
import './Checkbox-DBU82s9B.js';
import './Modal-Cfl7ozBa.js';
import './client-BUusD8wq.js';
import './exports-BGi7-Rnc.js';
import 'axios';

const NavContainer = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let { fluid = false } = $$props;
  if ($$props.fluid === void 0 && $$bindings.fluid && fluid !== void 0) $$bindings.fluid(fluid);
  return `<div${add_attribute("class", twMerge("mx-auto flex flex-wrap justify-between items-center ", fluid ? "w-full" : "container", $$props.class), 0)}>${slots.default ? slots.default({}) : ``}</div> `;
});
const Navbar = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["fluid"]);
  let $hidden, $$unsubscribe_hidden;
  let { fluid = false } = $$props;
  let hidden = writable(true);
  $$unsubscribe_hidden = subscribe(hidden, (value) => $hidden = value);
  setContext("navHidden", hidden);
  let toggle = () => hidden.update((hidden2) => !hidden2);
  if ($$props.fluid === void 0 && $$bindings.fluid && fluid !== void 0) $$bindings.fluid(fluid);
  {
    {
      $$restProps.color = $$restProps.color ?? "navbar";
    }
  }
  $$unsubscribe_hidden();
  return `${validate_component(Frame, "Frame").$$render(
    $$result,
    Object.assign({}, { tag: "nav" }, $$restProps, {
      class: twMerge("px-2 sm:px-4 py-2.5 w-full", $$props.class)
    }),
    {},
    {
      default: () => {
        return `${validate_component(NavContainer, "NavContainer").$$render($$result, { fluid }, {}, {
          default: () => {
            return `${slots.default ? slots.default({ hidden: $hidden, toggle, NavContainer }) : ``}`;
          }
        })}`;
      }
    }
  )} `;
});
const NavBrand = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["href"]);
  let { href = "" } = $$props;
  if ($$props.href === void 0 && $$bindings.href && href !== void 0) $$bindings.href(href);
  return `<a${spread(
    [
      { href: escape_attribute_value(href) },
      escape_object($$restProps),
      {
        class: escape_attribute_value(twMerge("flex items-center", $$props.class))
      }
    ],
    {}
  )}>${slots.default ? slots.default({}) : ``}</a> `;
});
const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let user;
  if (typeof window !== "undefined") {
    const accessToken = localStorage.getItem("accessToken");
    let username = instance.post("/verify-token", {}, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`
      }
    }).catch(function(error) {
    });
    const getData = () => {
      username.then((username2) => {
        user = username2;
      });
    };
    getData();
  }
  return ` <div class="custom-navbar sticky top-0 z-40">${validate_component(Navbar, "Navbar").$$render($$result, {}, {}, {
    default: () => {
      return `${validate_component(NavBrand, "NavBrand").$$render(
        $$result,
        {
          "data-sveltekit-reload": true,
          href: "/",
          class: "nav-brand"
        },
        {},
        {
          default: () => {
            return `<img src="img/callgo_logo.png" class="logo me-3 h-6 sm:h-9" alt="callgo Logo"> <span class="brand-text self-center whitespace-nowrap text-xl font-semibold dark:text-white" data-svelte-h="svelte-1a4y58w">CallGo</span>`;
          }
        }
      )} ${validate_component(Page$1, "CategoryButton").$$render($$result, {}, {}, {})} <div class="flex items-center">${validate_component(Page$2, "ModalButton").$$render($$result, { user }, {}, {})}  ${user === "gysoft" ? `${validate_component(Button, "Button").$$render(
        $$result,
        {
          "data-sveltekit-reload": true,
          href: "/admin",
          style: "margin-right: 15px;",
          class: "border",
          color: "none"
        },
        {},
        {
          default: () => {
            return `관리자 페이지`;
          }
        }
      )} ${validate_component(Button, "Button").$$render(
        $$result,
        {
          "data-sveltekit-reload": true,
          href: "/productregistration",
          color: "alternative",
          class: "m-8 mx-auto"
        },
        {},
        {
          default: () => {
            return `제품 등록하기`;
          }
        }
      )}` : ``}</div>`;
    }
  })}</div>`;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-BWHau7qs.js.map
