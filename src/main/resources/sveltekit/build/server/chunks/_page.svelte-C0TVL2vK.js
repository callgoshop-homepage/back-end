import { c as create_ssr_component, a as subscribe, v as validate_component, e as escape, j as each, d as add_attribute } from './ssr-DhdZ8Nh2.js';
import { w as writable } from './index-t0gNv4Rz.js';
import { B as Button } from './Button-DpbBnAHs.js';
import { C as Checkbox } from './Checkbox-DBU82s9B.js';
import { L as Label, I as Input } from './Input-t7C8Dt9y.js';
import { M as Modal } from './Modal-Cfl7ozBa.js';
import { t as twMerge } from './bundle-mjs-DVkqfP1f.js';
import { T as Table, a as TableHead, b as TableHeadCell, c as TableBody, d as TableBodyRow, e as TableBodyCell } from './TableHeadCell-BXcxRgxb.js';
import './Frame-NNL14bzx.js';

const ImagePlaceholder = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let imgOnlyClass;
  let { divClass = "space-y-8 animate-pulse md:space-y-0 md:space-x-8 rtl:space-x-reverse md:flex md:items-center" } = $$props;
  let { imgHeight = "48" } = $$props;
  let { imgOnly = false } = $$props;
  if ($$props.divClass === void 0 && $$bindings.divClass && divClass !== void 0) $$bindings.divClass(divClass);
  if ($$props.imgHeight === void 0 && $$bindings.imgHeight && imgHeight !== void 0) $$bindings.imgHeight(imgHeight);
  if ($$props.imgOnly === void 0 && $$bindings.imgOnly && imgOnly !== void 0) $$bindings.imgOnly(imgOnly);
  imgOnlyClass = imgOnly ? "max-w-60" : "";
  return `<div role="status"${add_attribute("class", twMerge(divClass, $$props.class, imgOnlyClass), 0)}><div class="${"flex justify-center items-center w-full h-" + escape(imgHeight, true) + " bg-gray-300 rounded sm:w-96 " + escape(imgOnlyClass, true) + " dark:bg-gray-700"}"><svg width="48" height="48" class="text-gray-200" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" fill="currentColor" viewBox="0 0 640 512"><path d="M480 80C480 35.82 515.8 0 560 0C604.2 0 640 35.82 640 80C640 124.2 604.2 160 560 160C515.8 160 480 124.2 480 80zM0 456.1C0 445.6 2.964 435.3 8.551 426.4L225.3 81.01C231.9 70.42 243.5 64 256 64C268.5 64 280.1 70.42 286.8 81.01L412.7 281.7L460.9 202.7C464.1 196.1 472.2 192 480 192C487.8 192 495 196.1 499.1 202.7L631.1 419.1C636.9 428.6 640 439.7 640 450.9C640 484.6 612.6 512 578.9 512H55.91C25.03 512 .0006 486.1 .0006 456.1L0 456.1z"></path></svg></div> ${!imgOnly ? `<div class="w-full" data-svelte-h="svelte-1l75rvy"><div class="h-2.5 bg-gray-200 rounded-full dark:bg-gray-700 w-1/2 mb-4"></div> <div class="h-2 bg-gray-200 rounded-full dark:bg-gray-700 w-9/12 mb-2.5"></div> <div class="h-2 bg-gray-200 rounded-full dark:bg-gray-700 mb-2.5"></div> <div class="h-2 bg-gray-200 rounded-full dark:bg-gray-700 mb-2.5"></div> <div class="h-2 bg-gray-200 rounded-full dark:bg-gray-700 w-10/12 mb-2.5"></div> <div class="h-2 bg-gray-200 rounded-full dark:bg-gray-700 w-11/12 mb-2.5"></div> <div class="h-2 bg-gray-200 rounded-full dark:bg-gray-700 w-9/12"></div></div>` : ``} <span class="sr-only" data-svelte-h="svelte-1wtojot">Loading...</span></div> `;
});
function getImage(fileName) {
  return `/img/${fileName}`;
}
function formatNumber(value) {
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
const Page = create_ssr_component(($$result, $$props, $$bindings, slots) => {
  let $selectAll, $$unsubscribe_selectAll;
  let deleteModal = false;
  let alertModal = false;
  let orderModal = false;
  let alertModal2 = false;
  let selectAll = writable(false);
  $$unsubscribe_selectAll = subscribe(selectAll, (value) => $selectAll = value);
  let cartDatas = [];
  let userAddress = "";
  let detailAddress = "";
  let reciever;
  let details;
  let isFlex;
  let ml;
  let selectedTotalPrice = 0;
  let $$settled;
  let $$rendered;
  let previous_head = $$result.head;
  do {
    $$settled = true;
    $$result.head = previous_head;
    $$rendered = `<div class="p-8"><p class="mb-10 mt-20 font-sans text-3xl font-bold" style="border-bottom: 2px solid gray; padding-bottom: 30px;" data-svelte-h="svelte-18v8hvk">장바구니</p>  ${validate_component(Button, "Button").$$render($$result, { color: "alternative", class: "mb-4" }, {}, {
      default: () => {
        return `삭제`;
      }
    })} ${validate_component(Modal, "Modal").$$render(
      $$result,
      {
        size: "xs",
        autoclose: true,
        open: deleteModal
      },
      {
        open: ($$value) => {
          deleteModal = $$value;
          $$settled = false;
        }
      },
      {
        default: () => {
          return `<div class="text-center"><img src="/img/callgo_logo.png" class="mx-auto mb-4 w-1/6" alt="callgo Logo"> <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400" data-svelte-h="svelte-1qhpqyv">해당 상품을 삭제하시겠습니까?</h3> ${validate_component(Button, "Button").$$render($$result, { class: "modal_button me-2" }, {}, {
            default: () => {
              return `네`;
            }
          })} ${validate_component(Button, "Button").$$render($$result, { color: "alternative" }, {}, {
            default: () => {
              return `아니요`;
            }
          })}</div>`;
        }
      }
    )} ${validate_component(Modal, "Modal").$$render(
      $$result,
      {
        size: "xs",
        autoclose: true,
        open: alertModal
      },
      {
        open: ($$value) => {
          alertModal = $$value;
          $$settled = false;
        }
      },
      {
        default: () => {
          return `<div class="text-center"><img src="/img/callgo_logo.png" class="mx-auto mb-4 w-1/6" alt="callgo Logo"> <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400" data-svelte-h="svelte-1ontdgr">삭제할 상품을 선택해주세요</h3> ${validate_component(Button, "Button").$$render($$result, { color: "alternative" }, {}, {
            default: () => {
              return `확인`;
            }
          })}</div>`;
        }
      }
    )} ${validate_component(Button, "Button").$$render(
      $$result,
      {
        color: "none",
        style: "margin-right: 15px;",
        class: "border"
      },
      {},
      {
        default: () => {
          return `<p data-svelte-h="svelte-1439f5w">주문 하기</p>`;
        }
      }
    )} ${validate_component(Modal, "Modal").$$render(
      $$result,
      {
        title: "주문하기",
        class: "custom-modal max-w-md",
        open: orderModal
      },
      {
        open: ($$value) => {
          orderModal = $$value;
          $$settled = false;
        }
      },
      {
        default: () => {
          return `<form><div class="mb-6">${validate_component(Label, "Label").$$render($$result, { for: "reciever", class: "mb-2 mt-2" }, {}, {
            default: () => {
              return `수취인 명`;
            }
          })} <div class="input-group mb-2 flex">${validate_component(Input, "Input").$$render(
            $$result,
            {
              type: "text",
              id: "name",
              placeholder: "수취인 이름을 적어주세요.",
              required: true,
              value: reciever
            },
            {
              value: ($$value) => {
                reciever = $$value;
                $$settled = false;
              }
            },
            {}
          )}</div> ${validate_component(Label, "Label").$$render($$result, { for: "address", class: "mb-2" }, {}, {
            default: () => {
              return `주소`;
            }
          })} <div class="input-group flex">${validate_component(Input, "Input").$$render(
            $$result,
            {
              type: "text",
              id: "name",
              placeholder: "주소를 찾아주세요",
              required: true,
              disabled: "false",
              value: userAddress
            },
            {
              value: ($$value) => {
                userAddress = $$value;
                $$settled = false;
              }
            },
            {}
          )} ${validate_component(Button, "Button").$$render($$result, { size: "sm", class: "modal_button ml-2" }, {}, {
            default: () => {
              return `주소 찾기`;
            }
          })}</div> ${validate_component(Label, "Label").$$render($$result, { for: "address", class: "mb-2 mt-2" }, {}, {
            default: () => {
              return `상세 주소`;
            }
          })} <div class="input-group flex">${validate_component(Input, "Input").$$render(
            $$result,
            {
              type: "text",
              id: "name",
              placeholder: "상세주소를 입력해주세요.",
              required: true,
              value: detailAddress
            },
            {
              value: ($$value) => {
                detailAddress = $$value;
                $$settled = false;
              }
            },
            {}
          )}</div> ${``}</div> ${userAddress !== "" && detailAddress !== "" ? `<div class="text-center">${validate_component(Button, "Button").$$render($$result, { type: "submit", class: "modal_button" }, {}, {
            default: () => {
              return `확인`;
            }
          })}</div>` : `<h1 class="mt-4" data-svelte-h="svelte-nbbys1">주문을 완료하시려면 주소를 입력해주세요.</h1>`}</form>`;
        }
      }
    )}  ${validate_component(Modal, "Modal").$$render(
      $$result,
      {
        size: "xs",
        autoclose: true,
        open: alertModal2
      },
      {
        open: ($$value) => {
          alertModal2 = $$value;
          $$settled = false;
        }
      },
      {
        default: () => {
          return `<div class="text-center"><img src="/img/callgo_logo.png" class="mx-auto mb-4 w-1/6" alt="callgo Logo"> <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400" data-svelte-h="svelte-r504bs">주문할 상품을 선택해주세요</h3> ${validate_component(Button, "Button").$$render($$result, { color: "alternative" }, {}, {
            default: () => {
              return `확인`;
            }
          })}</div>`;
        }
      }
    )} <div class="text-xl mb-4 border p-2 font-bold" style="width: 300px; display: flex; justify-content: space-between;"><p data-svelte-h="svelte-i2dcvi">선택 상품 총가격 :</p> <p>${escape(formatNumber(selectedTotalPrice))} 원</p></div> ${validate_component(Table, "Table").$$render($$result, {}, {}, {
      default: () => {
        return `${validate_component(TableHead, "TableHead").$$render($$result, {}, {}, {
          default: () => {
            return `${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, { class: "flex" }, {}, {
              default: () => {
                return `${validate_component(Checkbox, "Checkbox").$$render(
                  $$result,
                  { class: "mr-2", checked: $selectAll },
                  {
                    checked: ($$value) => {
                      $selectAll = $$value;
                      $$settled = false;
                    }
                  },
                  {}
                )}
				상품명`;
              }
            })} ${validate_component(TableHeadCell, "TableHeadCell").$$render($$result, {}, {}, {
              default: () => {
                return `가격`;
              }
            })}`;
          }
        })} ${validate_component(TableBody, "TableBody").$$render($$result, { tableBodyClass: "divide-y" }, {}, {
          default: () => {
            return `${each(cartDatas, (item, index) => {
              return `${validate_component(TableBodyRow, "TableBodyRow").$$render($$result, {}, {}, {
                default: () => {
                  return ` ${validate_component(TableBodyCell, "TableBodyCell").$$render($$result, { class: "!p-4" }, {}, {
                    default: () => {
                      return `<div${add_attribute("class", isFlex, 0)}><div class="flex">${validate_component(Checkbox, "Checkbox").$$render(
                        $$result,
                        { class: "mr-2", checked: item.selected },
                        {
                          checked: ($$value) => {
                            item.selected = $$value;
                            $$settled = false;
                          }
                        },
                        {}
                      )} <img class="h-40 w-40"${add_attribute("src", getImage(item.board[0].storedFileName), 0)}></div> <div${add_attribute("class", ml, 0)}><div class="text-xl font-medium text-blue-600 hover:underline dark:text-red-500">${escape(item.productName)}</div> ${item.productOption !== null ? `<p class="text-gray-500">옵션 : ${escape(item.productOption.optionName)} + ${escape(item.productOption.optionPrice)} </p>` : ``} <div class="text-gray-500">${item.productOption !== null ? `개수 : ${escape(item.productOption.cartItems[0].count)} 개` : `개수 : ${escape(item.count)} 개`}</div> </div></div> `;
                    }
                  })}  ${validate_component(TableBodyCell, "TableBodyCell").$$render($$result, { class: "text-xl" }, {}, {
                    default: () => {
                      return `${item.productOption !== null ? `${escape(formatNumber(item.productPrice * item.count + item.productOption.cartItems[0].count * item.productOption.optionPrice))} 원` : `${escape(formatNumber(item.productPrice * item.count))} 원`} `;
                    }
                  })} `;
                }
              })} `;
            })}`;
          }
        })}`;
      }
    })} ${validate_component(Modal, "Modal").$$render(
      $$result,
      {
        title: details?.name,
        open: !!details,
        autoclose: true,
        outsideclose: true
      },
      {},
      {
        default: () => {
          return `${validate_component(ImagePlaceholder, "ImagePlaceholder").$$render($$result, {}, {}, {})}`;
        }
      }
    )}</div> `;
  } while (!$$settled);
  $$unsubscribe_selectAll();
  return $$rendered;
});

export { Page as default };
//# sourceMappingURL=_page.svelte-C0TVL2vK.js.map
