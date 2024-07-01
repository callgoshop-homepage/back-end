import { c as create_ssr_component, v as validate_component, j as each, e as escape, d as add_attribute, b as compute_rest_props, k as createEventDispatcher, s as setContext, f as spread, h as escape_object, i as escape_attribute_value, l as compute_slots, g as getContext, a as subscribe } from './ssr-DhdZ8Nh2.js';
import { t as twMerge } from './bundle-mjs-DVkqfP1f.js';
import { B as Button } from './Button-DpbBnAHs.js';
import { w as writable } from './index-t0gNv4Rz.js';
import { L as Label } from './Input-t7C8Dt9y.js';
import { F as Fileupload, L as Listgroup, a as ListgroupItem } from './Listgroup-CwAsol2V.js';
import { M as Modal } from './Modal-Cfl7ozBa.js';
import { T as Table, a as TableHead, b as TableHeadCell, c as TableBody, d as TableBodyRow, e as TableBodyCell } from './TableHeadCell-BXcxRgxb.js';
import './Frame-NNL14bzx.js';

const Indicator = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$slots = compute_slots(slots);
  let { color = "gray" } = $$props;
  let { rounded = false } = $$props;
  let { size = "md" } = $$props;
  let { border = false } = $$props;
  let { placement = void 0 } = $$props;
  let { offset = true } = $$props;
  const colors = {
    gray: "bg-gray-200",
    dark: "bg-gray-900 dark:bg-gray-700",
    blue: "bg-blue-600",
    orange: "bg-orange-600",
    green: "bg-green-500",
    red: "bg-red-500",
    purple: "bg-purple-500",
    indigo: "bg-indigo-500",
    yellow: "bg-yellow-300",
    teal: "bg-teal-500",
    none: ""
  };
  const sizes = {
    xs: "w-2 h-2",
    sm: "w-2.5 h-2.5",
    md: "w-3 h-3",
    lg: "w-3.5 h-3.5",
    xl: "w-6 h-6"
  };
  const placements = {
    // top
    "top-left": "top-0 start-0",
    "top-center": "top-0 start-1/2 -translate-x-1/2 rtl:translate-x-1/2 rtl:translate-x-1/2",
    "top-right": "top-0 end-0",
    // center
    "center-left": "top-1/2 -translate-y-1/2 start-0",
    center: "top-1/2 -translate-y-1/2 start-1/2 -translate-x-1/2 rtl:translate-x-1/2 rtl:translate-x-1/2",
    "center-right": "top-1/2 -translate-y-1/2 end-0",
    // bottom
    "bottom-left": "bottom-0 start-0",
    "bottom-center": "bottom-0 start-1/2 -translate-x-1/2 rtl:translate-x-1/2 rtl:translate-x-1/2",
    "bottom-right": "bottom-0 end-0"
  };
  const offsets = {
    // top
    "top-left": "-translate-x-1/3 rtl:translate-x-1/3 -translate-y-1/3",
    "top-center": "-translate-y-1/3",
    "top-right": "translate-x-1/3 rtl:-translate-x-1/3 -translate-y-1/3",
    // center
    "center-left": "-translate-x-1/3 rtl:translate-x-1/3",
    center: "",
    "center-right": "translate-x-1/3 rtl:-translate-x-1/3",
    // bottom
    "bottom-left": "-translate-x-1/3 rtl:translate-x-1/3 translate-y-1/3",
    "bottom-center": "translate-y-1/3",
    "bottom-right": "translate-x-1/3 rtl:-translate-x-1/3 translate-y-1/3"
  };
  let dotClass;
  if ($$props.color === void 0 && $$bindings.color && color !== void 0) $$bindings.color(color);
  if ($$props.rounded === void 0 && $$bindings.rounded && rounded !== void 0) $$bindings.rounded(rounded);
  if ($$props.size === void 0 && $$bindings.size && size !== void 0) $$bindings.size(size);
  if ($$props.border === void 0 && $$bindings.border && border !== void 0) $$bindings.border(border);
  if ($$props.placement === void 0 && $$bindings.placement && placement !== void 0) $$bindings.placement(placement);
  if ($$props.offset === void 0 && $$bindings.offset && offset !== void 0) $$bindings.offset(offset);
  dotClass = twMerge("flex-shrink-0", rounded ? "rounded" : "rounded-full", border && "border-2 border-white dark:border-gray-800", sizes[size], colors[color], $$slots.default && "inline-flex items-center justify-center", placement && "absolute " + placements[placement], placement && offset && offsets[placement], $$props.class);
  return `<div${add_attribute("class", dotClass, 0)}>${slots.default ? slots.default({}) : ``}</div> `;
});
const ControlButton = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let { forward } = $$props;
  let { name } = $$props;
  let buttonCls;
  if ($$props.forward === void 0 && $$bindings.forward && forward !== void 0) $$bindings.forward(forward);
  if ($$props.name === void 0 && $$bindings.name && name !== void 0) $$bindings.name(name);
  buttonCls = twMerge("flex absolute top-0 z-30 justify-center items-center px-4 h-full group focus:outline-none text-white dark:text-gray-300", forward ? "end-0" : "start-0", $$props.class);
  return `<button type="button"${add_attribute("class", buttonCls, 0)}>${slots.default ? slots.default({}) : ` <span class="inline-flex justify-center items-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">${forward ? `<svg aria-hidden="true" class="w-5 h-5 sm:w-6 sm:h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path></svg>` : `<svg aria-hidden="true" class="w-5 h-5 sm:w-6 sm:h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path></svg>`} <span class="sr-only">${escape(name)}</span></span> `}</button> `;
});
const canChangeSlide = ({ lastSlideChange, slideDuration, slideDurationRatio = 1 }) => {
  if (lastSlideChange && (/* @__PURE__ */ new Date()).getTime() - lastSlideChange.getTime() < slideDuration * slideDurationRatio) {
    console.warn("Can't change slide yet, too soon");
    return false;
  }
  return true;
};
const Controls = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $state, $$unsubscribe_state;
  const state = getContext("state");
  $$unsubscribe_state = subscribe(state, (value) => $state = value);
  const { update } = state;
  function changeSlide(forward) {
    if (!canChangeSlide({
      lastSlideChange: $state.lastSlideChange,
      slideDuration: $state.slideDuration,
      slideDurationRatio: 0.75
    })) {
      return;
    }
    if (forward) {
      update((_state) => {
        _state.forward = true;
        _state.index = _state.index >= _state.images.length - 1 ? 0 : _state.index + 1;
        _state.lastSlideChange = /* @__PURE__ */ new Date();
        return { ..._state };
      });
    } else {
      update((_state) => {
        _state.forward = false;
        _state.index = _state.index <= 0 ? _state.images.length - 1 : _state.index - 1;
        _state.lastSlideChange = /* @__PURE__ */ new Date();
        return { ..._state };
      });
    }
  }
  $$unsubscribe_state();
  return ` ${slots.default ? slots.default({ ControlButton, changeSlide }) : ` ${validate_component(ControlButton, "ControlButton").$$render(
    $$result,
    {
      name: "Previous",
      forward: false,
      class: twMerge($$props.class)
    },
    {},
    {}
  )} ${validate_component(ControlButton, "ControlButton").$$render(
    $$result,
    {
      name: "Next",
      forward: true,
      class: twMerge($$props.class)
    },
    {},
    {}
  )} `}`;
});
const Indicators = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $state, $$unsubscribe_state;
  let { activeClass = "opacity-100" } = $$props;
  let { inactiveClass = "opacity-60" } = $$props;
  const state = getContext("state");
  $$unsubscribe_state = subscribe(state, (value) => $state = value);
  if ($$props.activeClass === void 0 && $$bindings.activeClass && activeClass !== void 0) $$bindings.activeClass(activeClass);
  if ($$props.inactiveClass === void 0 && $$bindings.inactiveClass && inactiveClass !== void 0) $$bindings.inactiveClass(inactiveClass);
  $$unsubscribe_state();
  return `<div${add_attribute("class", twMerge("flex absolute bottom-5 start-1/2 z-30 space-x-3 rtl:space-x-reverse -translate-x-1/2 rtl:translate-x-1/2", $$props.class), 0)}>${each($state.images, (_, idx) => {
    let selected = $state.index === idx;
    return ` <button>${slots.default ? slots.default({ Indicator, selected, index: idx }) : ` ${validate_component(Indicator, "Indicator").$$render(
      $$result,
      {
        class: twMerge("bg-gray-100 hover:bg-gray-300", selected ? activeClass : inactiveClass)
      },
      {},
      {}
    )} `} </button>`;
  })}</div> `;
});
const Slide = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let imgClass;
  let $$restProps = compute_rest_props($$props, ["image", "transition"]);
  let $state, $$unsubscribe_state;
  const state = getContext("state");
  $$unsubscribe_state = subscribe(state, (value) => $state = value);
  let { image } = $$props;
  let { transition = null } = $$props;
  if ($$props.image === void 0 && $$bindings.image && image !== void 0) $$bindings.image(image);
  if ($$props.transition === void 0 && $$bindings.transition && transition !== void 0) $$bindings.transition(transition);
  ({
    x: $state.forward ? "100%" : "-100%",
    opacity: 1,
    width: "100%",
    height: "100%",
    duration: $state.slideDuration
  });
  ({
    x: $state.forward ? "-100%" : "100%",
    opacity: 0.9,
    width: "100%",
    height: "100%",
    duration: $state.slideDuration
  });
  imgClass = twMerge("absolute block !w-full h-full object-cover", $$props.class);
  $$unsubscribe_state();
  return `${transition ? `<img${spread(
    [
      { alt: "..." },
      escape_object(image),
      escape_object($$restProps),
      { class: escape_attribute_value(imgClass) }
    ],
    {}
  )}>` : `<img${spread(
    [
      { alt: "..." },
      escape_object(image),
      escape_object($$restProps),
      { class: escape_attribute_value(imgClass) }
    ],
    {}
  )}>`} `;
});
let divClass = "grid overflow-hidden relative rounded-lg h-56 sm:h-64 xl:h-80 2xl:h-96";
const Carousel = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $$restProps = compute_rest_props($$props, [
    "images",
    "index",
    "slideDuration",
    "transition",
    "duration",
    "ariaLabel",
    "disableSwipe",
    "imgClass"
  ]);
  let { images } = $$props;
  let { index = 0 } = $$props;
  let { slideDuration = 1e3 } = $$props;
  let { transition = null } = $$props;
  let { duration = 0 } = $$props;
  let { ariaLabel = "Draggable Carousel" } = $$props;
  let { disableSwipe = false } = $$props;
  let { imgClass = "" } = $$props;
  const dispatch = createEventDispatcher();
  const { set, subscribe: subscribe2, update } = writable({
    images,
    index,
    forward: true,
    slideDuration,
    lastSlideChange: /* @__PURE__ */ new Date()
  });
  const state = {
    set: (_state) => set({
      index: _state.index,
      images: _state.images,
      lastSlideChange: /* @__PURE__ */ new Date(),
      slideDuration,
      forward
    }),
    subscribe: subscribe2,
    update
  };
  let forward = true;
  setContext("state", state);
  subscribe2((_state) => {
    index = _state.index;
    forward = _state.forward;
    dispatch("change", images[index]);
  });
  let carouselDiv;
  if ($$props.images === void 0 && $$bindings.images && images !== void 0) $$bindings.images(images);
  if ($$props.index === void 0 && $$bindings.index && index !== void 0) $$bindings.index(index);
  if ($$props.slideDuration === void 0 && $$bindings.slideDuration && slideDuration !== void 0) $$bindings.slideDuration(slideDuration);
  if ($$props.transition === void 0 && $$bindings.transition && transition !== void 0) $$bindings.transition(transition);
  if ($$props.duration === void 0 && $$bindings.duration && duration !== void 0) $$bindings.duration(duration);
  if ($$props.ariaLabel === void 0 && $$bindings.ariaLabel && ariaLabel !== void 0) $$bindings.ariaLabel(ariaLabel);
  if ($$props.disableSwipe === void 0 && $$bindings.disableSwipe && disableSwipe !== void 0) $$bindings.disableSwipe(disableSwipe);
  if ($$props.imgClass === void 0 && $$bindings.imgClass && imgClass !== void 0) $$bindings.imgClass(imgClass);
  return ` ${$$result.head += `<!-- HEAD_svelte-h5oyes_START -->${images.length > 0 ? `${each(images, (image) => {
    return `<link rel="preload"${add_attribute("href", image.src, 0)} as="image">`;
  })}` : ``}<!-- HEAD_svelte-h5oyes_END -->`, ""}   <div class="relative" role="button"${add_attribute("aria-label", ariaLabel, 0)} tabindex="0"${add_attribute("this", carouselDiv, 0)}><div${spread(
    [
      escape_object($$restProps),
      {
        class: escape_attribute_value(twMerge(
          divClass,
          "transition-transform",
          $$props.class
        ))
      }
    ],
    {}
  )}>${slots.slide ? slots.slide({ Slide, index }) : ` ${validate_component(Slide, "Slide").$$render(
    $$result,
    {
      image: images[index],
      class: imgClass,
      transition
    },
    {},
    {}
  )} `}</div> ${slots.default ? slots.default({ index, Controls, Indicators }) : ``}</div> `;
});
function getImage(fileName) {
  return `/img/${fileName}`;
}
const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let suggestProducts = [];
  let images = [];
  let openModal = false;
  let openMainModal = false;
  let files = [];
  let $$settled;
  let $$rendered;
  let previous_head = $$result.head;
  do {
    $$settled = true;
    $$result.head = previous_head;
    $$rendered = ` <div class="max-w-6xl space-y-4" style="margin: auto; ">${validate_component(Carousel, "Carousel").$$render(
      $$result,
      {
        images,
        imgClass: "object-contain rounded-sm",
        class: "mainCarousel min-h-[320px] rounded-md "
      },
      {},
      {
        default: ({ Indicators: Indicators2, Controls: Controls2 }) => {
          return `${validate_component(Indicators2, "Indicators").$$render($$result, { class: "rounded-md p-2" }, {}, {
            default: ({ selected }) => {
              return `${validate_component(Indicator, "Indicator").$$render(
                $$result,
                {
                  color: selected ? "yellow" : "gray",
                  class: (selected ? "opacity-100" : "opacity-80") + " "
                },
                {},
                {}
              )}`;
            }
          })} ${validate_component(Controls2, "Controls").$$render(
            $$result,
            {
              class: "items-center pt-4 text-yellow-400 dark:text-green-400"
            },
            {},
            {}
          )}`;
        }
      }
    )} ${``}</div> <form enctype="multipart/form-data">${validate_component(Modal, "Modal").$$render(
      $$result,
      { title: "메인 사진 등록", open: openMainModal },
      {
        open: ($$value) => {
          openMainModal = $$value;
          $$settled = false;
        }
      },
      {
        footer: () => {
          return `${validate_component(Button, "Button").$$render(
            $$result,
            {
              type: "submit",
              "data-sveltekit-reload": true,
              color: "yellow",
              style: "margin-right: 15px;",
              class: "mb-2 text-base text-white"
            },
            {},
            {
              default: () => {
                return `<p data-svelte-h="svelte-10whwj6">등록 하기</p>`;
              }
            }
          )} `;
        },
        default: () => {
          return `${validate_component(Label, "Label").$$render($$result, { class: "pb-2", for: "multiple_files1" }, {}, {
            default: () => {
              return `메인 사진 업로드`;
            }
          })} ${validate_component(Fileupload, "Fileupload").$$render(
            $$result,
            {
              type: "file",
              id: "multiple_files1",
              multiple: true
            },
            {},
            {}
          )} ${validate_component(Listgroup, "Listgroup").$$render($$result, { class: "my-2" }, {}, {
            default: () => {
              return `${files.length ? each(files, (file, index) => {
                return `<div class="flex items-center justify-between"><div class="m-4">${escape(file.name)}</div> ${validate_component(Button, "Button").$$render(
                  $$result,
                  {
                    class: "m-4",
                    size: "xs",
                    color: "yellow"
                  },
                  {},
                  {
                    default: () => {
                      return `X
					`;
                    }
                  }
                )} </div>`;
              }) : `${validate_component(ListgroupItem, "ListgroupItem").$$render($$result, {}, {}, {
                default: () => {
                  return `No files`;
                }
              })}`}`;
            }
          })}`;
        }
      }
    )}</form>  <div class="text-center" style="margin: auto; "><div class="mb-40"></div>   ${``} ${validate_component(Modal, "Modal").$$render(
      $$result,
      {
        title: "상품 리스트",
        autoclose: true,
        open: openModal
      },
      {
        open: ($$value) => {
          openModal = $$value;
          $$settled = false;
        }
      },
      {
        footer: () => {
          return `${validate_component(Button, "Button").$$render(
            $$result,
            {
              "data-sveltekit-reload": true,
              color: "yellow",
              style: "margin-right: 15px;",
              class: "mb-2 text-base text-white"
            },
            {},
            {
              default: () => {
                return `<p data-svelte-h="svelte-10whwj6">등록 하기</p>`;
              }
            }
          )} `;
        },
        default: () => {
          return `<p data-svelte-h="svelte-1cqxipf">최대 4개 까지 선택 가능합니다.</p> ${validate_component(Table, "Table").$$render($$result, {}, {}, {
            default: () => {
              return `${validate_component(TableHead, "TableHead").$$render($$result, {}, {}, {
                default: () => {
                  return `${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, { class: "!p-4" }, {}, {})} ${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, {}, {}, {
                    default: () => {
                      return `상품 사진`;
                    }
                  })} ${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, {}, {}, {
                    default: () => {
                      return `상품 명`;
                    }
                  })} ${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, {}, {}, {
                    default: () => {
                      return `상품 가격`;
                    }
                  })} ${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, {}, {}, {
                    default: () => {
                      return `카테고리`;
                    }
                  })}`;
                }
              })} ${validate_component(TableBody, "TableBody").$$render($$result, { tableBodyClass: "divide-y" }, {}, {
                default: () => {
                  return `${`${validate_component(TableBodyRow, "TableBodyRow").$$render($$result, {}, {}, {
                    default: () => {
                      return `${validate_component(TableBodyCell, "TableBodyCell").$$render($$result, { colspan: "4", class: "text-center" }, {}, {
                        default: () => {
                          return `Loading...`;
                        }
                      })}`;
                    }
                  })}`}`;
                }
              })}`;
            }
          })}`;
        }
      }
    )} <h3 class="mb-2 text-5xl font-bold" data-svelte-h="svelte-3l2hpg">추천 상품</h3> <p class="text-base" data-svelte-h="svelte-1a9u43h">콜고 굿즈의 신상품을 확인해보세요.</p> <div class="grid-container">${suggestProducts || suggestProducts !== null ? `${each(suggestProducts, (product) => {
      return `  <button class="grid-item">${product.boards && product.boards.length > 0 ? ` <img class="responsive-image"${add_attribute("src", getImage(product.boards[0].storedFileName), 0)}>` : ``} </button>`;
    })}` : ``}</div></div>`;
  } while (!$$settled);
  return $$rendered;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-CAsCG_v-.js.map
