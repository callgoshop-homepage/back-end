import { c as create_ssr_component, v as validate_component, b as compute_rest_props, s as setContext, d as add_attribute, e as escape, g as getContext } from './ssr-DhdZ8Nh2.js';
import { t as twMerge } from './bundle-mjs-DVkqfP1f.js';
import { B as Button } from './Button-DpbBnAHs.js';
import { P as Popper } from './Popper-CzsQEtaU.js';
import './Frame-NNL14bzx.js';

const GradientButton = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["color", "shadow"]);
  const group = getContext("group");
  let { color = "blue" } = $$props;
  let { shadow = false } = $$props;
  const gradientClasses = {
    blue: "text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-blue-300 dark:focus:ring-blue-800 ",
    green: "text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-green-300 dark:focus:ring-green-800",
    cyan: "text-white bg-gradient-to-r from-cyan-400 via-cyan-500 to-cyan-600 hover:bg-gradient-to-br focus:ring-cyan-300 dark:focus:ring-cyan-800",
    teal: "text-white bg-gradient-to-r from-teal-400 via-teal-500 to-teal-600 hover:bg-gradient-to-br focus:ring-teal-300 dark:focus:ring-teal-800",
    lime: "text-gray-900 bg-gradient-to-r from-lime-200 via-lime-400 to-lime-500 hover:bg-gradient-to-br focus:ring-lime-300 dark:focus:ring-lime-800",
    red: "text-white bg-gradient-to-r from-red-400 via-red-500 to-red-600 hover:bg-gradient-to-br focus:ring-red-300 dark:focus:ring-red-800",
    pink: "text-white bg-gradient-to-r from-pink-400 via-pink-500 to-pink-600 hover:bg-gradient-to-br focus:ring-pink-300 dark:focus:ring-pink-800",
    purple: "text-white bg-gradient-to-r from-purple-500 via-purple-600 to-purple-700 hover:bg-gradient-to-br focus:ring-purple-300 dark:focus:ring-purple-800",
    purpleToBlue: "text-white bg-gradient-to-br from-purple-600 to-blue-500 hover:bg-gradient-to-bl focus:ring-blue-300 dark:focus:ring-blue-800",
    cyanToBlue: "text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-cyan-300 dark:focus:ring-cyan-800",
    greenToBlue: "text-white bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-green-200 dark:focus:ring-green-800",
    purpleToPink: "text-white bg-gradient-to-r from-purple-500 to-pink-500 hover:bg-gradient-to-l focus:ring-purple-200 dark:focus:ring-purple-800",
    pinkToOrange: "text-white bg-gradient-to-br from-pink-500 to-orange-400 hover:bg-gradient-to-bl focus:ring-pink-200 dark:focus:ring-pink-800",
    tealToLime: "text-gray-900 bg-gradient-to-r from-teal-200 to-lime-200 hover:bg-gradient-to-l focus:ring-lime-200 dark:focus:ring-teal-700",
    redToYellow: "text-gray-900 bg-gradient-to-r from-red-200 via-red-300 to-yellow-200 hover:bg-gradient-to-bl focus:ring-red-100 dark:focus:ring-red-400"
  };
  const coloredShadowClasses = {
    blue: "shadow-blue-500/50 dark:shadow-blue-800/80",
    green: "shadow-green-500/50 dark:shadow-green-800/80",
    cyan: "shadow-cyan-500/50 dark:shadow-cyan-800/80",
    teal: "shadow-teal-500/50 dark:shadow-teal-800/80 ",
    lime: "shadow-lime-500/50 dark:shadow-lime-800/80",
    red: "shadow-red-500/50 dark:shadow-red-800/80 ",
    pink: "shadow-pink-500/50 dark:shadow-pink-800/80",
    purple: "shadow-purple-500/50 dark:shadow-purple-800/80",
    purpleToBlue: "shadow-blue-500/50 dark:shadow-blue-800/80",
    cyanToBlue: "shadow-cyan-500/50 dark:shadow-cyan-800/80",
    greenToBlue: "shadow-green-500/50 dark:shadow-green-800/80",
    purpleToPink: "shadow-purple-500/50 dark:shadow-purple-800/80",
    pinkToOrange: "shadow-pink-500/50 dark:shadow-pink-800/80",
    tealToLime: "shadow-lime-500/50 dark:shadow-teal-800/80",
    redToYellow: "shadow-red-500/50 dark:shadow-red-800/80"
  };
  let gradientOutlineClass;
  let divClass;
  if ($$props.color === void 0 && $$bindings.color && color !== void 0) $$bindings.color(color);
  if ($$props.shadow === void 0 && $$bindings.shadow && shadow !== void 0) $$bindings.shadow(shadow);
  gradientOutlineClass = twMerge(
    "inline-flex items-center justify-center w-full  !border-0",
    $$props.pill || "!rounded-md",
    "bg-white !text-gray-900 dark:bg-gray-900 dark:!text-white",
    // this is limitation - no transparency
    "hover:bg-transparent hover:!text-inherit",
    "transition-all duration-75 ease-in group-hover:!bg-opacity-0 group-hover:!text-inherit"
  );
  divClass = twMerge(
    $$props.outline && "p-0.5",
    gradientClasses[color],
    shadow && "shadow-lg",
    shadow && coloredShadowClasses[color],
    group ? $$props.pill && "first:rounded-s-full last:rounded-e-full" || "first:rounded-s-lg last:rounded-e-lg" : $$props.pill && "rounded-full" || "rounded-lg",
    $$props.class
  );
  return `${$$props.outline ? `<div${add_attribute("class", divClass, 0)}> ${validate_component(Button, "Button").$$render($$result, Object.assign({}, $$restProps, { color: "none" }, { class: gradientOutlineClass }), {}, {
    default: () => {
      return `${slots.default ? slots.default({}) : ``}`;
    }
  })}</div>` : `${validate_component(Button, "Button").$$render($$result, Object.assign({}, $$restProps, { color: "none" }, { class: divClass }), {}, {
    default: () => {
      return `${slots.default ? slots.default({}) : ``}`;
    }
  })}`} `;
});
let n = Date.now();
function generateId() {
  return (++n).toString(36);
}
const SpeedDial = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, [
    "defaultClass",
    "popperDefaultClass",
    "placement",
    "pill",
    "tooltip",
    "trigger",
    "textOutside",
    "id",
    "name",
    "gradient",
    "open"
  ]);
  let { defaultClass = "fixed end-6 bottom-6" } = $$props;
  let { popperDefaultClass = "flex items-center mb-4 gap-2" } = $$props;
  let { placement = "top" } = $$props;
  let { pill = true } = $$props;
  let { tooltip = "left" } = $$props;
  let { trigger = "hover" } = $$props;
  let { textOutside = false } = $$props;
  let { id = generateId() } = $$props;
  let { name = "Open actions menu" } = $$props;
  let { gradient = false } = $$props;
  let { open = false } = $$props;
  setContext("speed-dial", { pill, tooltip, textOutside });
  let divClass;
  let poperClass;
  if ($$props.defaultClass === void 0 && $$bindings.defaultClass && defaultClass !== void 0) $$bindings.defaultClass(defaultClass);
  if ($$props.popperDefaultClass === void 0 && $$bindings.popperDefaultClass && popperDefaultClass !== void 0) $$bindings.popperDefaultClass(popperDefaultClass);
  if ($$props.placement === void 0 && $$bindings.placement && placement !== void 0) $$bindings.placement(placement);
  if ($$props.pill === void 0 && $$bindings.pill && pill !== void 0) $$bindings.pill(pill);
  if ($$props.tooltip === void 0 && $$bindings.tooltip && tooltip !== void 0) $$bindings.tooltip(tooltip);
  if ($$props.trigger === void 0 && $$bindings.trigger && trigger !== void 0) $$bindings.trigger(trigger);
  if ($$props.textOutside === void 0 && $$bindings.textOutside && textOutside !== void 0) $$bindings.textOutside(textOutside);
  if ($$props.id === void 0 && $$bindings.id && id !== void 0) $$bindings.id(id);
  if ($$props.name === void 0 && $$bindings.name && name !== void 0) $$bindings.name(name);
  if ($$props.gradient === void 0 && $$bindings.gradient && gradient !== void 0) $$bindings.gradient(gradient);
  if ($$props.open === void 0 && $$bindings.open && open !== void 0) $$bindings.open(open);
  let $$settled;
  let $$rendered;
  let previous_head = $$result.head;
  do {
    $$settled = true;
    $$result.head = previous_head;
    divClass = twMerge(defaultClass, "group", $$props.class);
    poperClass = twMerge(popperDefaultClass, ["top", "bottom"].includes(placement.split("-")[0]) && "flex-col");
    $$rendered = `<div${add_attribute("class", divClass, 0)}>${slots.button ? slots.button({}) : ` ${gradient ? `${validate_component(GradientButton, "GradientButton").$$render($$result, Object.assign({}, { pill }, { name }, { "aria-controls": id }, { "aria-expanded": open }, $$restProps, { class: "!p-3" }), {}, {
      default: () => {
        return `${slots.icon ? slots.icon({}) : ` <svg aria-hidden="true" class="w-8 h-8 transition-transform group-hover:rotate-45" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path></svg> `} <span class="sr-only">${escape(name)}</span>`;
      }
    })}` : `${validate_component(Button, "Button").$$render($$result, Object.assign({}, { pill }, { name }, { "aria-controls": id }, { "aria-expanded": open }, $$restProps, { class: "!p-3" }), {}, {
      default: () => {
        return `${slots.icon ? slots.icon({}) : ` <svg aria-hidden="true" class="w-8 h-8 transition-transform group-hover:rotate-45" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path></svg> `} <span class="sr-only">${escape(name)}</span>`;
      }
    })}`} `} ${validate_component(Popper, "Popper").$$render(
      $$result,
      {
        id,
        trigger,
        arrow: false,
        color: "none",
        activeContent: true,
        placement,
        class: poperClass,
        open
      },
      {
        open: ($$value) => {
          open = $$value;
          $$settled = false;
        }
      },
      {
        default: () => {
          return `${slots.default ? slots.default({}) : ``}`;
        }
      }
    )}</div> `;
  } while (!$$settled);
  return $$rendered;
});
const Tooltip = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["type", "defaultClass"]);
  let { type = "dark" } = $$props;
  let { defaultClass = "py-2 px-3 text-sm font-medium" } = $$props;
  const types = {
    dark: "bg-gray-900 text-white dark:bg-gray-700",
    light: "border-gray-200 bg-white text-gray-900",
    auto: " bg-white text-gray-900 dark:bg-gray-700 dark:text-white border-gray-200 dark:border-gray-700",
    custom: ""
  };
  let toolTipClass;
  if ($$props.type === void 0 && $$bindings.type && type !== void 0) $$bindings.type(type);
  if ($$props.defaultClass === void 0 && $$bindings.defaultClass && defaultClass !== void 0) $$bindings.defaultClass(defaultClass);
  {
    {
      if ($$restProps.color) type = "custom";
      else $$restProps.color = "none";
      if (["light", "auto"].includes(type)) $$restProps.border = true;
      toolTipClass = twMerge("tooltip", defaultClass, types[type], $$props.class);
    }
  }
  return `${validate_component(Popper, "Popper").$$render($$result, Object.assign({}, { rounded: true }, { shadow: true }, $$restProps, { class: toolTipClass }), {}, {
    default: () => {
      return `${slots.default ? slots.default({}) : ``}`;
    }
  })} `;
});
const SpeedDialButton = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, [
    "btnDefaultClass",
    "name",
    "tooltip",
    "pill",
    "textOutside",
    "textOutsideClass",
    "textDefaultClass"
  ]);
  const context = getContext("speed-dial");
  let { btnDefaultClass = "w-[52px] h-[52px] shadow-sm !p-2" } = $$props;
  let { name = "" } = $$props;
  let { tooltip = context.tooltip } = $$props;
  let { pill = context.pill } = $$props;
  let { textOutside = context.textOutside } = $$props;
  let { textOutsideClass = "block absolute -start-14 top-1/2 mb-px text-sm font-medium -translate-y-1/2" } = $$props;
  let { textDefaultClass = "block mb-px text-xs font-medium" } = $$props;
  let btnClass;
  if ($$props.btnDefaultClass === void 0 && $$bindings.btnDefaultClass && btnDefaultClass !== void 0) $$bindings.btnDefaultClass(btnDefaultClass);
  if ($$props.name === void 0 && $$bindings.name && name !== void 0) $$bindings.name(name);
  if ($$props.tooltip === void 0 && $$bindings.tooltip && tooltip !== void 0) $$bindings.tooltip(tooltip);
  if ($$props.pill === void 0 && $$bindings.pill && pill !== void 0) $$bindings.pill(pill);
  if ($$props.textOutside === void 0 && $$bindings.textOutside && textOutside !== void 0) $$bindings.textOutside(textOutside);
  if ($$props.textOutsideClass === void 0 && $$bindings.textOutsideClass && textOutsideClass !== void 0) $$bindings.textOutsideClass(textOutsideClass);
  if ($$props.textDefaultClass === void 0 && $$bindings.textDefaultClass && textDefaultClass !== void 0) $$bindings.textDefaultClass(textDefaultClass);
  btnClass = twMerge(btnDefaultClass, tooltip === "none" && "flex-col", textOutside && "relative", $$props.class);
  return `${validate_component(Button, "Button").$$render($$result, Object.assign({}, { pill }, { outline: true }, { color: "light" }, $$restProps, { class: btnClass }), {}, {
    default: () => {
      return `${slots.default ? slots.default({}) : ``} ${tooltip !== "none" ? `<span class="sr-only">${escape(name)}</span>` : `${textOutside ? `<span${add_attribute("class", textOutsideClass, 0)}>${escape(name)}</span>` : `<span${add_attribute("class", textDefaultClass, 0)}>${escape(name)}</span>`}`}`;
    }
  })} ${tooltip !== "none" ? `${validate_component(Tooltip, "Tooltip").$$render($$result, { placement: tooltip, style: "dark" }, {}, {
    default: () => {
      return `${escape(name)}`;
    }
  })}` : ``} `;
});
const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  return `${validate_component(SpeedDial, "SpeedDial").$$render(
    $$result,
    {
      defaultClass: "fixed end-10 bottom-10 z-40",
      tooltip: "none",
      style: "background-color: #ea9e2f;"
    },
    {},
    {
      default: () => {
        return `${validate_component(SpeedDialButton, "SpeedDialButton").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/maincategoryview?category=전체",
            pill: true,
            size: "sm",
            outline: true,
            color: "yellow",
            class: "bg-white text-xs"
          },
          {},
          {
            default: () => {
              return `전체`;
            }
          }
        )} ${validate_component(SpeedDialButton, "SpeedDialButton").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/maincategoryview?category=의류",
            pill: true,
            size: "sm",
            outline: true,
            color: "yellow",
            class: "bg-white text-xs"
          },
          {},
          {
            default: () => {
              return `의류`;
            }
          }
        )} ${validate_component(SpeedDialButton, "SpeedDialButton").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/maincategoryview?category=가방",
            pill: true,
            size: "sm",
            outline: true,
            color: "yellow",
            class: "bg-white text-xs"
          },
          {},
          {
            default: () => {
              return `가방`;
            }
          }
        )} ${validate_component(SpeedDialButton, "SpeedDialButton").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/maincategoryview?category=바이크 용품",
            pill: true,
            size: "sm",
            outline: true,
            color: "yellow",
            class: "bg-white text-xs"
          },
          {},
          {
            default: () => {
              return `바이크 용품`;
            }
          }
        )} ${validate_component(SpeedDialButton, "SpeedDialButton").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/maincategoryview?category=기타 용품",
            pill: true,
            size: "sm",
            outline: true,
            color: "yellow",
            class: "bg-white text-xs"
          },
          {},
          {
            default: () => {
              return `기타용품`;
            }
          }
        )} ${validate_component(SpeedDialButton, "SpeedDialButton").$$render(
          $$result,
          {
            "data-sveltekit-reload": true,
            href: "/maincategoryview?category=리스문의",
            pill: true,
            size: "sm",
            outline: true,
            color: "yellow",
            class: "bg-white text-xs"
          },
          {},
          {
            default: () => {
              return `리스문의`;
            }
          }
        )}`;
      }
    }
  )}`;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-BjQWmyJ8.js.map
