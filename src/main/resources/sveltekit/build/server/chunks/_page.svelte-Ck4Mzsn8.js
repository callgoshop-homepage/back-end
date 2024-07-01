import { c as create_ssr_component, v as validate_component } from './ssr-DhdZ8Nh2.js';
import { S as Spinner } from './Spinner-E8_yzEDZ.js';
import './bundle-mjs-DVkqfP1f.js';

const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$settled;
  let $$rendered;
  let previous_head = $$result.head;
  do {
    $$settled = true;
    $$result.head = previous_head;
    $$rendered = `${`${validate_component(Spinner, "Spinner").$$render($$result, { class: "mx-auto", size: 8 }, {}, {})}`}`;
  } while (!$$settled);
  return $$rendered;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-Ck4Mzsn8.js.map
