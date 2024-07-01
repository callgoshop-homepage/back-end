import { c as create_ssr_component, b as compute_rest_props, k as createEventDispatcher, d as add_attribute, e as escape, v as validate_component, l as compute_slots, g as getContext, f as spread, i as escape_attribute_value, h as escape_object } from './ssr-DhdZ8Nh2.js';
import { t as twMerge } from './bundle-mjs-DVkqfP1f.js';
import { F as Frame } from './Frame-NNL14bzx.js';

const ToolbarButton = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["color", "name", "ariaLabel", "size", "href"]);
  const background = getContext("background");
  let { color = "default" } = $$props;
  let { name = void 0 } = $$props;
  let { ariaLabel = void 0 } = $$props;
  let { size = "md" } = $$props;
  let { href = void 0 } = $$props;
  const colors = {
    dark: "text-gray-500 hover:text-gray-900 hover:bg-gray-200 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-600",
    gray: "text-gray-500 focus:ring-gray-400 hover:bg-gray-200 dark:hover:bg-gray-800 dark:hover:text-gray-300",
    red: "text-red-500 focus:ring-red-400 hover:bg-red-200 dark:hover:bg-red-800 dark:hover:text-red-300",
    yellow: "text-yellow-500 focus:ring-yellow-400 hover:bg-yellow-200 dark:hover:bg-yellow-800 dark:hover:text-yellow-300",
    green: "text-green-500 focus:ring-green-400 hover:bg-green-200 dark:hover:bg-green-800 dark:hover:text-green-300",
    indigo: "text-indigo-500 focus:ring-indigo-400 hover:bg-indigo-200 dark:hover:bg-indigo-800 dark:hover:text-indigo-300",
    purple: "text-purple-500 focus:ring-purple-400 hover:bg-purple-200 dark:hover:bg-purple-800 dark:hover:text-purple-300",
    pink: "text-pink-500 focus:ring-pink-400 hover:bg-pink-200 dark:hover:bg-pink-800 dark:hover:text-pink-300",
    blue: "text-blue-500 focus:ring-blue-400 hover:bg-blue-200 dark:hover:bg-blue-800 dark:hover:text-blue-300",
    primary: "text-primary-500 focus:ring-primary-400 hover:bg-primary-200 dark:hover:bg-primary-800 dark:hover:text-primary-300",
    default: "focus:ring-gray-400 hover:bg-gray-100"
  };
  const sizing = {
    xs: "m-0.5 rounded-sm focus:ring-1 p-0.5",
    sm: "m-0.5 rounded focus:ring-1 p-0.5",
    md: "m-0.5 rounded-lg focus:ring-2 p-1.5",
    lg: "m-0.5 rounded-lg focus:ring-2 p-2.5"
  };
  let buttonClass;
  const svgSizes = {
    xs: "w-3 h-3",
    sm: "w-3.5 h-3.5",
    md: "w-5 h-5",
    lg: "w-5 h-5"
  };
  if ($$props.color === void 0 && $$bindings.color && color !== void 0) $$bindings.color(color);
  if ($$props.name === void 0 && $$bindings.name && name !== void 0) $$bindings.name(name);
  if ($$props.ariaLabel === void 0 && $$bindings.ariaLabel && ariaLabel !== void 0) $$bindings.ariaLabel(ariaLabel);
  if ($$props.size === void 0 && $$bindings.size && size !== void 0) $$bindings.size(size);
  if ($$props.href === void 0 && $$bindings.href && href !== void 0) $$bindings.href(href);
  buttonClass = twMerge(
    "focus:outline-none whitespace-normal",
    sizing[size],
    colors[color],
    color === "default" && (background ? "dark:hover:bg-gray-600" : "dark:hover:bg-gray-700"),
    $$props.class
  );
  return `${href ? `<a${spread(
    [
      { href: escape_attribute_value(href) },
      escape_object($$restProps),
      {
        class: escape_attribute_value(buttonClass)
      },
      {
        "aria-label": escape_attribute_value(ariaLabel ?? name)
      }
    ],
    {}
  )}>${name ? `<span class="sr-only">${escape(name)}</span>` : ``} ${slots.default ? slots.default({ svgSize: svgSizes[size] }) : ``}</a>` : `<button${spread(
    [
      { type: "button" },
      escape_object($$restProps),
      {
        class: escape_attribute_value(buttonClass)
      },
      {
        "aria-label": escape_attribute_value(ariaLabel ?? name)
      }
    ],
    {}
  )}>${name ? `<span class="sr-only">${escape(name)}</span>` : ``} ${slots.default ? slots.default({ svgSize: svgSizes[size] }) : ``}</button>`} `;
});
const CloseButton = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, ["name"]);
  let { name = "Close" } = $$props;
  if ($$props.name === void 0 && $$bindings.name && name !== void 0) $$bindings.name(name);
  return `${validate_component(ToolbarButton, "ToolbarButton").$$render($$result, Object.assign({}, { name }, $$restProps, { class: twMerge("ms-auto", $$props.class) }), {}, {
    default: ({ svgSize }) => {
      return `<svg${add_attribute("class", svgSize, 0)} fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>`;
    }
  })} `;
});
const Modal = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let backdropCls;
  let dialogCls;
  let frameCls;
  let headerCls;
  let bodyCls;
  let footerCls;
  let $$restProps = compute_rest_props($$props, [
    "open",
    "title",
    "size",
    "color",
    "placement",
    "autoclose",
    "outsideclose",
    "dismissable",
    "backdropClass",
    "classBackdrop",
    "dialogClass",
    "classDialog",
    "defaultClass",
    "headerClass",
    "classHeader",
    "bodyClass",
    "classBody",
    "footerClass",
    "classFooter"
  ]);
  let $$slots = compute_slots(slots);
  let { open = false } = $$props;
  let { title = "" } = $$props;
  let { size = "md" } = $$props;
  let { color = "default" } = $$props;
  let { placement = "center" } = $$props;
  let { autoclose = false } = $$props;
  let { outsideclose = false } = $$props;
  let { dismissable = true } = $$props;
  let { backdropClass = "fixed inset-0 z-40 bg-gray-900 bg-opacity-50 dark:bg-opacity-80" } = $$props;
  let { classBackdrop = void 0 } = $$props;
  let { dialogClass = "fixed top-0 start-0 end-0 h-modal md:inset-0 md:h-full z-50 w-full p-4 flex" } = $$props;
  let { classDialog = void 0 } = $$props;
  let { defaultClass = "relative flex flex-col mx-auto" } = $$props;
  let { headerClass = "flex justify-between items-center p-4 md:p-5 rounded-t-lg" } = $$props;
  let { classHeader = void 0 } = $$props;
  let { bodyClass = "p-4 md:p-5 space-y-4 flex-1 overflow-y-auto overscroll-contain" } = $$props;
  let { classBody = void 0 } = $$props;
  let { footerClass = "flex items-center p-4 md:p-5 space-x-3 rtl:space-x-reverse rounded-b-lg" } = $$props;
  let { classFooter = void 0 } = $$props;
  const dispatch = createEventDispatcher();
  const getPlacementClasses = (placement2) => {
    switch (placement2) {
      case "top-left":
        return ["justify-start", "items-start"];
      case "top-center":
        return ["justify-center", "items-start"];
      case "top-right":
        return ["justify-end", "items-start"];
      case "center-left":
        return ["justify-start", "items-center"];
      case "center":
        return ["justify-center", "items-center"];
      case "center-right":
        return ["justify-end", "items-center"];
      case "bottom-left":
        return ["justify-start", "items-end"];
      case "bottom-center":
        return ["justify-center", "items-end"];
      case "bottom-right":
        return ["justify-end", "items-end"];
      default:
        return ["justify-center", "items-center"];
    }
  };
  const sizes = {
    xs: "max-w-md",
    sm: "max-w-lg",
    md: "max-w-2xl",
    lg: "max-w-4xl",
    xl: "max-w-7xl"
  };
  if ($$props.open === void 0 && $$bindings.open && open !== void 0) $$bindings.open(open);
  if ($$props.title === void 0 && $$bindings.title && title !== void 0) $$bindings.title(title);
  if ($$props.size === void 0 && $$bindings.size && size !== void 0) $$bindings.size(size);
  if ($$props.color === void 0 && $$bindings.color && color !== void 0) $$bindings.color(color);
  if ($$props.placement === void 0 && $$bindings.placement && placement !== void 0) $$bindings.placement(placement);
  if ($$props.autoclose === void 0 && $$bindings.autoclose && autoclose !== void 0) $$bindings.autoclose(autoclose);
  if ($$props.outsideclose === void 0 && $$bindings.outsideclose && outsideclose !== void 0) $$bindings.outsideclose(outsideclose);
  if ($$props.dismissable === void 0 && $$bindings.dismissable && dismissable !== void 0) $$bindings.dismissable(dismissable);
  if ($$props.backdropClass === void 0 && $$bindings.backdropClass && backdropClass !== void 0) $$bindings.backdropClass(backdropClass);
  if ($$props.classBackdrop === void 0 && $$bindings.classBackdrop && classBackdrop !== void 0) $$bindings.classBackdrop(classBackdrop);
  if ($$props.dialogClass === void 0 && $$bindings.dialogClass && dialogClass !== void 0) $$bindings.dialogClass(dialogClass);
  if ($$props.classDialog === void 0 && $$bindings.classDialog && classDialog !== void 0) $$bindings.classDialog(classDialog);
  if ($$props.defaultClass === void 0 && $$bindings.defaultClass && defaultClass !== void 0) $$bindings.defaultClass(defaultClass);
  if ($$props.headerClass === void 0 && $$bindings.headerClass && headerClass !== void 0) $$bindings.headerClass(headerClass);
  if ($$props.classHeader === void 0 && $$bindings.classHeader && classHeader !== void 0) $$bindings.classHeader(classHeader);
  if ($$props.bodyClass === void 0 && $$bindings.bodyClass && bodyClass !== void 0) $$bindings.bodyClass(bodyClass);
  if ($$props.classBody === void 0 && $$bindings.classBody && classBody !== void 0) $$bindings.classBody(classBody);
  if ($$props.footerClass === void 0 && $$bindings.footerClass && footerClass !== void 0) $$bindings.footerClass(footerClass);
  if ($$props.classFooter === void 0 && $$bindings.classFooter && classFooter !== void 0) $$bindings.classFooter(classFooter);
  {
    dispatch(open ? "open" : "close");
  }
  backdropCls = twMerge(backdropClass, classBackdrop);
  dialogCls = twMerge(dialogClass, classDialog, getPlacementClasses(placement));
  frameCls = twMerge(defaultClass, "w-full divide-y", $$props.class);
  headerCls = twMerge(headerClass, classHeader);
  bodyCls = twMerge(bodyClass, classBody);
  footerCls = twMerge(footerClass, classFooter);
  return `${open ? ` <div${add_attribute("class", backdropCls, 0)}></div>   <div${add_attribute("class", dialogCls, 0)} tabindex="-1" aria-modal="true" role="dialog"><div class="${"flex relative " + escape(sizes[size], true) + " w-full max-h-full"}"> ${validate_component(Frame, "Frame").$$render($$result, Object.assign({}, { rounded: true }, { shadow: true }, $$restProps, { class: frameCls }, { color }), {}, {
    default: () => {
      return ` ${$$slots.header || title ? `${validate_component(Frame, "Frame").$$render($$result, { class: headerCls, color }, {}, {
        default: () => {
          return `${slots.header ? slots.header({}) : ` <h3 class="${"text-xl font-semibold " + escape(
            color === "default" ? "" : "text-gray-900 dark:text-white",
            true
          ) + " p-0"}">${escape(title)}</h3> `} ${dismissable ? `${validate_component(CloseButton, "CloseButton").$$render($$result, { name: "Close modal", color }, {}, {})}` : ``}`;
        }
      })}` : ``}  <div${add_attribute("class", bodyCls, 0)} role="document">${dismissable && !$$slots.header && !title ? `${validate_component(CloseButton, "CloseButton").$$render(
        $$result,
        {
          name: "Close modal",
          class: "absolute top-3 end-2.5",
          color
        },
        {},
        {}
      )}` : ``} ${slots.default ? slots.default({}) : ``}</div>  ${$$slots.footer ? `${validate_component(Frame, "Frame").$$render($$result, { class: footerCls, color }, {}, {
        default: () => {
          return `${slots.footer ? slots.footer({}) : ``}`;
        }
      })}` : ``}`;
    }
  })}</div></div>` : ``} `;
});

export { Modal as M };
//# sourceMappingURL=Modal-Cfl7ozBa.js.map
