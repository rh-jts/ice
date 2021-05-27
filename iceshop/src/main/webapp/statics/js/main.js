// ---------- クラス定義 ----------

// アイスの情報を保持するクラス
class Products {
  constructor() {
    this.products = [];
    this.extras = [0, 0];
    this.subtotal = 0;
    this.total = 0;
    this.bread = [];
    this.icenum_names = ["シングル", "ダブル", "トリプル"];
    this.container_names = ["コーン", "カップ", "ワッフル"];
    this.size_names = ["キッズ", "スモール", "レギュラー", "キング"];
    this.flavor_names = [
      ["vanilla", "バニラ"],
      ["chocolate", "チョコレート"],
      ["caramer", "キャラメルリボン"],
      ["raisin", "ラムレーズン"],
      ["lemon", "レモンシャーベット"],
      ["cookie", "クッキー＆クリーム"],
      ["banana", "バナナ＆ストロベリー"],
      ["macha", "抹茶"],
      ["chocochip", "チョップチョコレート"],
      ["nuts", "ナッツトゥユー"],
      ["rocky", "ロッキーロード"],
      ["strawberry", "ストロベリー"],
    ];
    this.current_ice = {
      num: 0,
      flavor_size: [
        [null, null],
        [null, null],
        [null, null],
      ],
      container: null,
    };
    this.selecting = 1; // 何個目のアイスを選択中か
    this.current_position = 0;
  }

  addProduct = (p) => {
    this.products.push(p);
  };

  getOrderdAmount = () => {
    let total_orderd_amount = 0;
    this.products.forEach(v => {
      if (v.is_active === true) {
        total_orderd_amount += v.quantity;
      }
    })
    return total_orderd_amount;
  }

  redrawTotals = () => {
    this.subtotal = 0;
    this.total = 0;

    Object.values(this.products).forEach((v) => {
      this.subtotal += v.is_active ? v.price * v.quantity : 0;
    });

    this.total = Math.round(
      (parseFloat(this.extras[1] - this.extras[0]) / 100.0 + 1) * this.subtotal
    );

    document.getElementById("subtotal").innerText = `￥${this.subtotal}`;
    document.getElementById("discount").innerText = `${this.extras[0]}%`;
    document.getElementById("tax").innerText = `${this.extras[1]}%`;
    document.getElementById("total").innerText = `￥${this.total}`;
  };

  changeProductQuantity = (product_num, is_plus) => {
    if (!is_plus && this.products[product_num].quantity <= 1) return;
    let amount = this.getOrderdAmount();
    if (amount >= 10 && is_plus) return;
    this.products[product_num].quantity += is_plus ? 1 : -1;

    document.getElementById(`qua_${product_num}`).innerText =
      this.products[product_num].quantity;
  };

  addCurrentIce = (is_flavor, num) => {
    if (is_flavor) this.current_ice.flavor_size[this.selecting - 1][0] = num;
    else this.current_ice.flavor_size[this.selecting - 1][1] = num;
  };

  setCurrentIceNull = () => {
    this.current_ice.flavor_size = [
      [null, null],
      [null, null],
      [null, null],
    ];
  };

  clearCurrentIce = () => {
    this.current_ice.num = 0;
    this.current_ice.flavor_size = [
      [null, null],
      [null, null],
      [null, null],
    ];
    this.current_ice.container = null;
  };

  getPrice = (size, icenum, double_combine) => {
    let ice_price = 0;

    if (icenum == 1) {
      if (size == 0) ice_price = 280;
      else if (size == 2) ice_price = 380;
      else if (size == 3) ice_price = 520;
    } else if (icenum == 2) {
      if (double_combine == 0) ice_price = 520;
      else if (double_combine == 1) ice_price = 720;
      else ice_price = 620;
    } else if (icenum == 3) ice_price = 580;

    return ice_price;
  };

  // アイス情報を送信用JSONに整形する
  formatProducts = () => {
    let formatted_products = {
      ices: [],
      tax_discounts: this.extras,
      total: this.total,
    };
    Object.values(this.products).forEach((v, i) => {
      if (v.is_active == true) {
        formatted_products.ices[i] = v;
      }
    });

    const submit_json = JSON.stringify(formatted_products);

    return submit_json;
  };

  // Ajaxでアイス情報を送信する
  submitIceDatas = () => {
    const url = "http://localhost:8080/iceshop/RecieveIceDatas";
    const submit_body_json = this.formatProducts();

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: submit_body_json,
    })
      .then((response) => {
        if (!respons.ok) {
          throw new Error(`${res.status} ${res.statusText}`);
        }
        return response.json();
      })
      .then((json) => {
        console.log(json);
      })
      .catch((e) => {
        console.error(e);
      });

      sessionStorage.clear();
      sessionStorage.setItem("ice_data", submit_body_json);
  };
}

let count = 0;
let ice = new Products();

// 行を管理・操作するクラス
class order {
  constructor(icenum, container, quantity, ices, price) {
    if (1 <= icenum && icenum <= 3) this.icenum = icenum;
    if (0 <= container && container <= 2) this.container = container;
    this.quantity = quantity;
    this.ices = ices;
    this.price = price;
    if (container == 2) this.price += 40;
    this.insertRow();
  }

  // 注文表に行を挿入する
  insertRow = () => {
    let order_number = count;
    let sizes_text = "";
    let flavors_text = "";

    for (let i = 1; i <= this.icenum; i++) {
      if (i >= 2) {
        sizes_text += "<br>";
        flavors_text += "<br>";
      }
      sizes_text += ice.size_names[this.ices[i - 1][1]];
      flavors_text += ice.flavor_names[this.ices[i - 1][0]][1];
    }

    let html_string =
      '<tr id="row_' +
      order_number +
      '"><td><button id="del_' +
      order_number +
      '" class="btn btn-outline-danger btn-sm"><i class="bi bi-trash"></i></button></td><td>' +
      ice.icenum_names[this.icenum - 1] +
      "</td><td>" +
      ice.container_names[this.container] +
      '</td><td id="qua_' +
      order_number +
      '">1</td><td><button id="pls_' +
      order_number +
      '" type="button" class="btn btn-outline-primary btn-sm"><i class="bi bi-plus"></i></button><button id="min_' +
      order_number +
      '" type="button" class="btn btn-outline-primary btn-sm"><i class="bi bi-dash"></i></button></td><td>' +
      sizes_text +
      "</td><td>" +
      flavors_text +
      "</td><td>￥" +
      this.price +
      "</td></tr>";

    let row = document.getElementById("rows");
    row.insertAdjacentHTML("beforeend", html_string);

    // ボタンが押されたときの機能を設定する
    document
      .getElementById(`pls_${order_number}`)
      .addEventListener("click", () => {
        ice.changeProductQuantity(order_number, true);
        ice.redrawTotals();
        toggleButtonActive();
      });
    document
      .getElementById(`min_${order_number}`)
      .addEventListener("click", () => {
        ice.changeProductQuantity(order_number, false);
        ice.redrawTotals();
        toggleButtonActive();
      });
    document
      .getElementById(`del_${order_number}`)
      .addEventListener("click", () => {
        document.getElementById(`row_${order_number}`).remove();
        ice.products[order_number].is_active = false;
        console.log("order_number : " + order_number);
        ice.redrawTotals();
      });

    let product = {
      product_num: order_number,
      icenum: this.icenum,
      container: this.container,
      quantity: this.quantity,
      ice: this.ices,
      price: this.price,
      is_active: true,
    };

    ice.addProduct(product);
    ice.redrawTotals();

    count += 1;
  };
}

// ---------- 初期設定 ----------

// 割引税率ボタン設定
const off = [10, 20, 31];
const tax = [8, 10];

off.forEach((a) => {
  document.getElementById(`${a}_off`).addEventListener("click", () => {

      ice.extras[0] = a;
      ice.redrawTotals();

  });
});
tax.forEach((a) => {
  document.getElementById(`${a}_tax`).addEventListener("click", () => {
    ice.extras[1] = a;
    ice.redrawTotals();
  });
});
document.getElementById("clear_extras").addEventListener("click", () => {
  ice.extras[0] = 0;
  ice.extras[1] = 0;
  ice.redrawTotals();
});

// カップ指定ボタン設定
ice.container_names.forEach((a, i) => {
  document.getElementById(`cup_${i + 1}`).addEventListener("click", () => {
    ice.current_ice.container = i;
    redrawBread(3);
  });
});

// アイス個数ボタン設定
setModalButtons = (icenum) => {
  // 既存要素クリア
  document.getElementById("md_flavors").remove();
  document.getElementById("md_sizes").remove();
  document.getElementById("md_harfline").remove();
  ice.setCurrentIceNull();

  // モーダルタイトル設定
  document.getElementById("md_icenum").innerText =
    "種類：" + ice.icenum_names[icenum - 1];

  // フレーバーボタン描画
  const modal_body_id = document.getElementById("md_body");
  modal_body_id.insertAdjacentHTML(
    "beforeend",
    '<div id="md_flavors"><p>フレーバー選択</p><div class="row" id="md_r1"></div><div class="row" id="md_r2"></div></div>'
  );

  ice.flavor_names.forEach((a, i) => {
    let sizes = [];
    let modal_button_html =
      '<div class="col-2" id="md_btn_' +
      a[0] +
      '"><a class="btn btn-sq btn-outline-danger d-flex align-items-center justify-content-center">' +
      a[1] +
      "</a></div>";

    if (i <= 5) {
      document
        .getElementById("md_r1")
        .insertAdjacentHTML("beforeend", modal_button_html);
    } else {
      document
        .getElementById("md_r2")
        .insertAdjacentHTML("beforeend", modal_button_html);
    }

    // フレーバーボタン押下設定
    document.getElementById(`md_btn_${a[0]}`).addEventListener("click", () => {
      ice.addCurrentIce(true, i);
      redrawModalNextButton();
      redrawModalItems();
    });
  });

  // 水平線描画
  document
    .getElementById("md_flavors")
    .insertAdjacentHTML("afterend", '<hr id="md_harfline"/>');

  // サイズボタン描画
  modal_body_id.insertAdjacentHTML(
    "beforeend",
    '<div id="md_sizes"><div class="sizeB text-center" id="md_size"></div></div>'
  );

  if (icenum <= 2) {
    switch (icenum) {
      case 1:
        sizes = [
          ["kids_button", "キッズ"],
          ["regular_button", "レギュラー"],
          ["king_button", "キング"],
        ];
        break;
      case 2:
        sizes = [
          ["small_button", "スモール"],
          ["regular_button", "レギュラー"],
        ];
        break;
    }

    let modal_size_html = "";

    sizes.forEach((a, i) => {
      modal_size_html +=
        '<button type="button" id="' +
        a[0] +
        '" class="btn btn-outline-primary rounded-circle p-0">' +
        a[1] +
        "</button>";
    });

    document
      .getElementById("md_size")
      .insertAdjacentHTML("beforeend", modal_size_html);
    document
      .getElementById("md_sizes")
      .insertAdjacentHTML("afterbegin", "<p>サイズ選択</p>");

    // サイズボタン押下設定
    switch (icenum) {
      case 1:
        document.getElementById("kids_button").addEventListener("click", () => {
          ice.addCurrentIce(false, 0);
          redrawModalNextButton();
          redrawModalItems();
        });
        document
          .getElementById("regular_button")
          .addEventListener("click", () => {
            ice.addCurrentIce(false, 2);
            redrawModalNextButton();
            redrawModalItems();
          });
        document.getElementById("king_button").addEventListener("click", () => {
          ice.addCurrentIce(false, 3);
          redrawModalNextButton();
          redrawModalItems();
        });
        break;
      case 2:
        document
          .getElementById("small_button")
          .addEventListener("click", () => {
            ice.addCurrentIce(false, 1);
            redrawModalNextButton();
            redrawModalItems();
          });
        document
          .getElementById("regular_button")
          .addEventListener("click", () => {
            ice.addCurrentIce(false, 2);
            redrawModalNextButton();
            redrawModalItems();
          });
        break;
    }
  }
};

// モーダルフッターボタン
const md_nb = document.getElementById("md_nextbtn");
md_nb.addEventListener("click", () => {
  if (ice.current_ice.num <= ice.selecting) {
    $("#exampleModal").modal("hide");
    redrawBread(2);
  }
  ice.selecting++;
  redrawModalNextButton();
  document.getElementById("md_title_flavor").innerText = " ";
  document.getElementById("md_title_size").innerText = " ";
});

// モーダルフッターの次へ・確定ボタン描画
redrawModalNextButton = () => {
  const md_footer = document.getElementById("md-footer");
  // モーダルの次へ完了判定
  if (
    ice.selecting < 4 &&
    (ice.current_ice.flavor_size[ice.selecting - 1][0] === null ||
      ice.current_ice.flavor_size[ice.selecting - 1][1] === null)
  ) {
    md_nb.setAttribute("disabled", true);
  } else {
    md_nb.removeAttribute("disabled");
  }

  if (ice.current_ice.num <= ice.selecting) {
    md_nb.innerText = "完了";
  } else {
    md_nb.innerText = "次へ";
  }
};

// アイス個数ボタン設定
for (let i = 1; i <= 3; i++) {
  document.getElementById(`icenum_${i}`).addEventListener(
    "click",
    (event) => {
      const btn = event.currentTarget;

      if (btn.getAttribute("aria-disabled") === "true") {
        return false;
      } else {
        ice.setCurrentIceNull();
        setModalButtons(i);
        ice.current_ice.num = i;
        ice.selecting = 1;
        if (i == 3) {
          ice.current_ice.flavor_size.forEach((a) => {
            a[1] = 1;
          });
        }
        redrawModalNextButton();

        // パンくずの現在位置を1に指定
        redrawBread(1);
      }
    },
    false
  );
}

// モーダルのアイス情報描画
redrawModalItems = () => {
  const md_title_flavor = document.getElementById("md_title_flavor");
  const md_title_size = document.getElementById("md_title_size");
  let f = ice.flavor_names[ice.current_ice.flavor_size[ice.selecting - 1][0]];
  let s = ice.size_names[ice.current_ice.flavor_size[ice.selecting - 1][1]];

  md_title_flavor.innerText = f[1] == null ? null : f[1];
  md_title_size.innerText = s == null ? null : s;
};

// パンくず再描画
redrawBread = (position) => {
  document.getElementById("bread").remove();
  document
    .getElementById("bread_body")
    .insertAdjacentHTML("beforeend", '<ol id="bread" class="breadcrumb"></ol>');
  let bread_id = document.getElementById("bread");

  if (position >= 1) {
    let icenum_name = ice.icenum_names[ice.current_ice.num - 1];
    bread_id.insertAdjacentHTML(
      "beforeend",
      '<li class="breadcrumb-item">  ' + icenum_name + "</li>"
    );
  }
  if (position >= 2) {
    let ice_fs = ice.current_ice.flavor_size;
    let ice_text = "";

    for (let i = 1; i <= ice.current_ice.num; i++) {
      ice_text +=
        ice.flavor_names[ice_fs[i - 1][0]][1] +
        "（" +
        ice.size_names[ice_fs[i - 1][1]] +
        "）";
    }
    bread_id.insertAdjacentHTML(
      "beforeend",
      '<li class="breadcrumb-item">  ' + ice_text + "</li>"
    );
  }
  if (position >= 3) {
    let container_name = ice.container_names[ice.current_ice.container];
    bread_id.insertAdjacentHTML(
      "beforeend",
      '<li class="breadcrumb-item">  ' + container_name + "</li>"
    );
  }

  ice.current_position = position;
  toggleButtonActive();
};

// ボタン有効・無効化
toggleButtonActive = () => {
  let position = ice.current_position;
  let num_buttons = [];
  let cup_buttons = [];
  let pay_buttons = [];
  let add_button = document.getElementById("btn_add");

  for (let i = 1; i <= 3; i++) {
    num_buttons[i] = document.getElementById(`icenum_${i}`);
    cup_buttons[i] = document.getElementById(`cup_${i}`);
  }
  pay_buttons[0] = document.getElementById("credit");
  pay_buttons[1] = document.getElementById("cash");

  // disabled属性を一括削除
  num_buttons.forEach((b) => {
    b.classList.remove("disabled");
    b.removeAttribute("aria-disabled");
  })
  cup_buttons.forEach((b) => {
    b.classList.remove("disabled");
    b.removeAttribute("aria-disabled");
  })
  pay_buttons.forEach((b) => {
    b.classList.remove("disabled");
    b.removeAttribute("aria-disabled");
  })
  add_button.classList.remove("disabled");
  add_button.removeAttribute("aria-disabled");

  // 現在地別ボタン有効無効処理
  if (position <= 1) {
    cup_buttons.forEach((b) => {
      b.classList.add("disabled");
      b.setAttribute("aria-disabled", true);
    })
    add_button.classList.add("disabled");
    add_button.setAttribute("aria-disabled", true);
  }
  else {
    num_buttons.forEach((b) => {
      b.classList.add("disabled");
      b.setAttribute("aria-disabled", true);
    })
  }
  if (ice.current_ice.num >= 3) {
    cup_buttons[1].classList.add("disabled");
    cup_buttons[3].classList.add("disabled");
    cup_buttons[1].setAttribute("aria-disabled", true);
    cup_buttons[3].setAttribute("aria-disabled", true);
  }

  // 注文数別ボタン有効無効処理
  let total_orderd_amount = 0;
  ice.products.forEach(v => {
    if (v.is_active === true) {
      total_orderd_amount += v.quantity;
    }
  })
  // 合計注文数が1未満なら支払いボタンを無効化
  if (total_orderd_amount < 1) {
    pay_buttons.forEach((b) => {
      b.classList.add("disabled");
      b.setAttribute("aria-disabled", true);
    })
  }
  // 合計注文数が10以上なら追加ボタンを無効化
  if (total_orderd_amount >= 10) {
    add_button.classList.add("disabled");
    add_button.setAttribute("aria-disabled", true);
  }

};


// 追加ボタン設定
document.getElementById("btn_add").addEventListener("click", (event) => {
  const btn = event.currentTarget;

  if (btn.getAttribute("aria-disabled") === "true") {
    return false;

  } else {
    

    let ice_object;
    let double_combine = 0;
    let ice_size = ice.current_ice.flavor_size[0][1];

    if (ice.current_ice.num == 2) {
      if (
        ice.current_ice.flavor_size[0][1] == ice.current_ice.flavor_size[1][1]
      ) {
        double_combine = 1;
      } else {
        double_combine = 2;
      }
    }

    let price = ice.getPrice(ice_size, ice.current_ice.num, double_combine);

    let to = new order(
      ice.current_ice.num,
      ice.current_ice.container,
      1,
      ice.current_ice.flavor_size,
      price
    );
    ice.clearCurrentIce();
    redrawBread(0);
  }
});

redrawBread(0);

// 支払いボタン設定
document.getElementById("cash").addEventListener("click", () => {
  ice.submitIceDatas();
});
document.getElementById("credit").addEventListener("click", () => {
  ice.submitIceDatas();
});

// ----------   モーダル設定 ----------
let myModal = document.getElementById("myModal");
let myInput = document.getElementById("myInput");

myModal.addEventListener("shown.bs.modal", (event) => {
  const btn = event.currentTarget;

  if (btn.getAttribute("aria-disabled") === "true") {
    return false;
  } else {
    myInput.focus();
  }
});
