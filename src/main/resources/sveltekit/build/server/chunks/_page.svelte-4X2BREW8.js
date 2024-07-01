import { c as create_ssr_component, v as validate_component } from './ssr-DhdZ8Nh2.js';
import { B as Button } from './Button-DpbBnAHs.js';
import './client-BUusD8wq.js';
import './bundle-mjs-DVkqfP1f.js';
import './exports-BGi7-Rnc.js';

const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  return `<div class="category_button top-0 z-10 flex flex-wrap items-center justify-center gap-3 py-2">${validate_component(Button, "Button").$$render(
    $$result,
    {
      "data-sveltekit-reload": true,
      href: "/maincategoryview?category=전체",
      pill: true,
      size: "lg",
      outline: true,
      color: "yellow"
    },
    {},
    {
      default: () => {
        return `전체`;
      }
    }
  )} ${validate_component(Button, "Button").$$render(
    $$result,
    {
      "data-sveltekit-reload": true,
      href: "/maincategoryview?category=의류",
      pill: true,
      size: "lg",
      outline: true,
      color: "yellow"
    },
    {},
    {
      default: () => {
        return `의류`;
      }
    }
  )} ${validate_component(Button, "Button").$$render(
    $$result,
    {
      "data-sveltekit-reload": true,
      href: "/maincategoryview?category=가방",
      pill: true,
      size: "lg",
      outline: true,
      color: "yellow"
    },
    {},
    {
      default: () => {
        return `가방`;
      }
    }
  )} ${validate_component(Button, "Button").$$render(
    $$result,
    {
      "data-sveltekit-reload": true,
      href: "/maincategoryview?category=바이크 용품",
      pill: true,
      size: "lg",
      outline: true,
      color: "yellow"
    },
    {},
    {
      default: () => {
        return `바이크 용품`;
      }
    }
  )} ${validate_component(Button, "Button").$$render(
    $$result,
    {
      "data-sveltekit-reload": true,
      href: "/maincategoryview?category=기타 용품",
      pill: true,
      size: "lg",
      outline: true,
      color: "yellow"
    },
    {},
    {
      default: () => {
        return `기타용품`;
      }
    }
  )} ${validate_component(Button, "Button").$$render(
    $$result,
    {
      "data-sveltekit-reload": true,
      href: "/maincategoryview?category=리스 문의",
      pill: true,
      size: "lg",
      outline: true,
      color: "yellow"
    },
    {},
    {
      default: () => {
        return `리스문의`;
      }
    }
  )}</div>`;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-4X2BREW8.js.map
