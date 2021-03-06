<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous"
    />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="/iceshop/css/main.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
    />
    <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />

    <title>メイン画面</title>
  </head>
  <body>

    <!-- Modal -->
    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">フレーバー選択</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">

            <!-- Modal Body -->
            <div id="md_body" class="container" style="width: 610px">
              <h2 id="md_icenum" class="text-center">種類：シングル</h2>
              <h4 class="text-center"><span>フレーバー：</span><span id="md_title_flavor"></span></h4>
              <h4 class="text-center"><span>サイズ：</span><span id="md_title_size"></span></h4>
              <div id="md_flavors"></div>
              <hr id="md_harfline"/>
              <div id="md_sizes"></div>

            </div>
          </div>

          <div id="md-footer" class="modal-footer">
            <button id="md_nextbtn" type="button" class="btn btn-outline-info float-end">
              確定
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div style="height: 270px; overflow-y: scroll">
        <table class="table">
          <thead>
            <tr>
              <th scope="col" style="width: 20px"></th>
              <th scope="col">個数</th>
              <th scope="col">容器</th>
              <th scope="col">注文数</th>
              <th scope="col">数変更</th>
              <th scope="col">サイズ</th>
              <th scope="col">フレーバー</th>
              <th scope="col">値段</th>
            </tr>
          </thead>
          <tbody id="rows">
            <!-- <tr>
            <td>
              <button id="del_" class="btn btn-outline-danger btn-sm">
                <i class="bi bi-trash"></i>
              </button>
            </td>
            <td>シングル</td>
            <td>コーン</td>
            <td id="qua_1">1</td>
            <td>
              <button
                id="pls_1"
                type="button"
                class="btn btn-outline-primary btn-sm"
              >
                +
              </button>
              <button
                id="min_1"
                type="button"
                class="btn btn-outline-primary btn-sm"
              >
                -
              </button>
            </td>
            <td>キング</td>
            <td>レモン</td>
            <td>￥280</td>
          </tr> -->
          </tbody>
        </table>
      </div>

      <!-- Total-->
      <table class="table table-bordered">
        <tbody>
          <tr>
            <td>小計</td>
            <td id="subtotal">￥0</td>
            <td>割引</td>
            <td id="discount">0%</td>
            <td>税率</td>
            <td id="tax">0%</td>
            <td>合計</td>
            <td id="total">￥0</td>
          </tr>
        </tbody>
      </table>

      <!-- Breadcrumb -->
      <hr />
      <nav id="bread_body" aria-label="breadcrumb">
        <ol id="bread" class="breadcrumb">
          <li class="breadcrumb-item">　ダブル</li>
          <li class="breadcrumb-item">
            レギュラー（バニラ） スモール（キャラメル）
          </li>
          <li class="breadcrumb-item">カップ</li>
        </ol>
      </nav>
      <hr />

      <!-- Buttons -->
      <div class="row">
        <div class="col-2">
          <a
            id="icenum_1"
            href="#"
            class="
              btn btn-sq btn-outline-primary
              d-flex
              align-items-center
              justify-content-center
            "
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
          >
            シングル
          </a>
        </div>
        <div class="col-2">
          <a
            id="cup_1"
            href="#"
            class="
              btn btn-sq btn-outline-info
              d-flex
              align-items-center
              justify-content-center
            "
          >
            コーン
          </a>
        </div>
        <div class="col-2"></div>
        <div class="col-2" id="10_off">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-primary
              d-flex
              align-items-center
              justify-content-center
            "
          >
            10%<br />OFF</a
          >
        </div>
        <div class="col-2" id="8_tax">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-info
              d-flex
              align-items-center
              justify-content-center
            "
          >
            持ち帰り<br />8%
          </a>
        </div>
        <div class="col-2"></div>
      </div>

      <div class="row">
        <div class="col-2">
          <a
            id="icenum_2"
            href="#"
            class="
              btn btn-sq btn-outline-primary
              d-flex
              align-items-center
              justify-content-center
            "
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
          >
            ダブル
          </a>
        </div>
        <div class="col-2">
          <a
          id="cup_2"
            href="#"
            class="
              btn btn-sq btn-outline-info
              d-flex
              align-items-center
              justify-content-center
            "
          >
            カップ
          </a>
        </div>
        <div class="col-2"></div>
        <div class="col-2" id="20_off">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-primary
              d-flex
              align-items-center
              justify-content-center
            "
          >
            20%<br />OFF
          </a>
        </div>
        <div class="col-2" id="10_tax">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-info
              d-flex
              align-items-center
              justify-content-center
            "
          >
            店内<br />10%
          </a>
        </div>
        <div class="col-2">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-success
              d-flex
              align-items-center
              justify-content-center
            "
          >
            クレジット
          </a>
        </div>
      </div>

      <div class="row">
        <div class="col-2">
          <a
            id="icenum_3"
            href="#"
            class="
              btn btn-sq btn-outline-primary
              d-flex
              align-items-center
              justify-content-center
            "
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
          >
            トリプル
          </a>
        </div>
        <div class="col-2">
          <a
          id="cup_3"
          href="#"
            class="
              btn btn-sq btn-outline-info
              d-flex
              align-items-center
              justify-content-center
            "
          >
            ワッフル
          </a>
        </div>
        <div class="col-2">
          <a
            id="btn_add"
            href="#"
            class="
              btn btn-sq btn-outline-success
              d-flex
              align-items-center
              justify-content-center
            "
          >
            追加
          </a>
        </div>
        <div class="col-2" id="31_off">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-primary
              d-flex
              align-items-center
              justify-content-center
            "
          >
            31%<br />OFF
          </a>
        </div>
        <div class="col-2" id="clear_extras">
          <a
            href="#"
            class="
              btn btn-sq btn-outline-warning
              d-flex
              align-items-center
              justify-content-center
            "
          >
            割引税率<br />クリア
          </a>
        </div>
        <div class="col-2">
          <a id="cash"
            href="#"
            class="
              btn btn-sq btn-outline-success
              d-flex
              align-items-center
              justify-content-center
            "
          >
            現金
          </a>
        </div>
      </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
      crossorigin="anonymous"
    ></script>
    <script src="/statics/js/main.js"></script>
  </body>
</html>
