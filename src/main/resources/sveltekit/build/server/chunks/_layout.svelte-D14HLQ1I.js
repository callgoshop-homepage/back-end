import { c as create_ssr_component, v as validate_component } from './ssr-DhdZ8Nh2.js';
import Page from './_page.svelte-BWHau7qs.js';
import Page$1 from './_page.svelte-BjQWmyJ8.js';
import Page$2 from './_page.svelte-MGhIlK52.js';
import './_page.svelte-YRd7Tlb1.js';
import './Button-DpbBnAHs.js';
import './bundle-mjs-DVkqfP1f.js';
import './ChevronDownOutline-B5LNVizg.js';
import './Popper-CzsQEtaU.js';
import './Frame-NNL14bzx.js';
import './index-t0gNv4Rz.js';
import './Input-t7C8Dt9y.js';
import './Checkbox-DBU82s9B.js';
import './Modal-Cfl7ozBa.js';
import './_page.svelte-4X2BREW8.js';
import './client-BUusD8wq.js';
import './exports-BGi7-Rnc.js';
import './api-6cxZS4_H.js';
import 'axios';

const Layout = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  return `<div class="wrapper h-screen">${validate_component(Page, "Navbar").$$render($$result, {}, {}, {})} ${validate_component(Page$1, "Dial").$$render($$result, {}, {}, {})} <div class="content p-12">${slots.default ? slots.default({}) : ``}</div> ${validate_component(Page$2, "Footer").$$render($$result, {}, {}, {})}</div>`;
});

export { Layout as default };
//# sourceMappingURL=_layout.svelte-D14HLQ1I.js.map
