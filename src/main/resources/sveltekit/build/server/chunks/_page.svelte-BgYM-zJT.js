import { c as create_ssr_component, v as validate_component, j as each, e as escape, d as add_attribute } from './ssr-DhdZ8Nh2.js';
import { B as Button } from './Button-DpbBnAHs.js';
import { C as ChevronDownOutline, D as Dropdown, a as DropdownItem } from './ChevronDownOutline-B5LNVizg.js';
import { L as Label, I as Input } from './Input-t7C8Dt9y.js';
import { F as Fileupload, L as Listgroup, a as ListgroupItem } from './Listgroup-CwAsol2V.js';
import './bundle-mjs-DVkqfP1f.js';
import './Popper-CzsQEtaU.js';
import './Frame-NNL14bzx.js';
import './index-t0gNv4Rz.js';

const Page_1 = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let productPrefixes = ["의류", "가방", "바이크 용품", "기타 용품", "리스 문의"];
  let selectedPrefix = "전체";
  let files = [];
  let detailFiles = [];
  let productName = "";
  let price = "";
  let options = [];
  let product = { price: "", productName: "" };
  let $$settled;
  let $$rendered;
  let previous_head = $$result.head;
  do {
    $$settled = true;
    $$result.head = previous_head;
    $$rendered = `<div style="max-width: 600px; margin: 0 auto;"><p class="mb-10 mt-20 font-sans text-3xl font-bold" style="border-bottom: 2px solid gray; padding-bottom: 30px;">${`제품 수정`}</p> <form enctype="multipart/form-data">${validate_component(Label, "Label").$$render($$result, { class: "pb-2", for: "multiple_files1" }, {}, {
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
    })} ${validate_component(Label, "Label").$$render(
      $$result,
      {
        class: "mt-10 pb-2",
        for: "multiple_detailFiles"
      },
      {},
      {
        default: () => {
          return `상세 사진 업로드`;
        }
      }
    )} ${validate_component(Fileupload, "Fileupload").$$render(
      $$result,
      {
        type: "file",
        id: "multiple_detailFiles",
        multiple: true
      },
      {},
      {}
    )} ${validate_component(Listgroup, "Listgroup").$$render($$result, { class: "mt-2" }, {}, {
      default: () => {
        return `${detailFiles.length ? each(detailFiles, (file, index) => {
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
    })} ${validate_component(Label, "Label").$$render($$result, { class: "mt-10 space-y-2" }, {}, {
      default: () => {
        return `<span data-svelte-h="svelte-ot21km">상품 타입</span> <div class="relative">${validate_component(Button, "Button").$$render(
          $$result,
          {
            class: "w-1/2 whitespace-nowrap rounded-md",
            style: "background-color: #222b33;"
          },
          {},
          {
            default: () => {
              return `${escape(selectedPrefix)} ${validate_component(ChevronDownOutline, "ChevronDownOutline").$$render($$result, { class: "ms-2.5 h-2.5 w-2.5" }, {}, {})}`;
            }
          }
        )} ${validate_component(Dropdown, "Dropdown").$$render($$result, { classContainer: "w-full" }, {}, {
          default: () => {
            return `${each(productPrefixes, (prefix) => {
              return `${validate_component(DropdownItem, "DropdownItem").$$render(
                $$result,
                {
                  class: "inline-flex items-center " + (selectedPrefix === prefix ? "underline" : "")
                },
                {},
                {
                  default: () => {
                    return `${escape(prefix)} `;
                  }
                }
              )}`;
            })}`;
          }
        })}</div>`;
      }
    })} ${validate_component(Label, "Label").$$render(
      $$result,
      {
        class: "mt-6 space-y-2",
        style: "margin-bottom:10px;"
      },
      {},
      {
        default: () => {
          return `<span data-svelte-h="svelte-1to5ws3">옵션 추가</span> <div class="relative">${validate_component(Button, "Button").$$render($$result, { class: "rounded-button", color: "dark" }, {}, {
            default: () => {
              return `+`;
            }
          })}</div>`;
        }
      }
    )} ${each(options, (option, index) => {
      return `<div style="margin-bottom:10px;">${`<input style="border-radius: 5px;" type="text"${add_attribute("placeholder", option.optionName == null ? option.optionName : "옵션 이름", 0)}${add_attribute("value", option.optionName, 0)}> <input style="border-radius: 5px;" type="text"${add_attribute("placeholder", option.optionPrice == null ? option.Price : "옵션 가격", 0)}${add_attribute("value", option.optionPrice, 0)}>`} ${validate_component(Button, "Button").$$render($$result, { color: "yellow" }, {}, {
        default: () => {
          return `삭제`;
        }
      })} </div>`;
    })} ${validate_component(Label, "Label").$$render($$result, { class: "mt-6 space-y-2" }, {}, {
      default: () => {
        return `<span data-svelte-h="svelte-1spbthy">상품명</span> ${`${validate_component(Input, "Input").$$render(
          $$result,
          {
            type: "text",
            placeholder: product.productName,
            size: "md",
            value: productName
          },
          {
            value: ($$value) => {
              productName = $$value;
              $$settled = false;
            }
          },
          {}
        )}`}`;
      }
    })} ${validate_component(Label, "Label").$$render($$result, { class: "mt-6 space-y-2" }, {}, {
      default: () => {
        return `<span data-svelte-h="svelte-1yl6uqd">가격</span> ${`${validate_component(Input, "Input").$$render(
          $$result,
          {
            type: "number",
            placeholder: product.price,
            size: "md",
            value: price
          },
          {
            value: ($$value) => {
              price = $$value;
              $$settled = false;
            }
          },
          {}
        )}`}`;
      }
    })} ${`${validate_component(Button, "Button").$$render(
      $$result,
      {
        type: "submit",
        color: "alternative",
        class: "mb-20 mt-8"
      },
      {},
      {
        default: () => {
          return `제품 수정하기`;
        }
      }
    )}`}</form></div>`;
  } while (!$$settled);
  return $$rendered;
});

export { Page_1 as default };
//# sourceMappingURL=_page.svelte-BgYM-zJT.js.map
