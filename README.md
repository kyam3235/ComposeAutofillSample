# ComposeAutofillSample

## 概要

compose.ui.autofillを利用した自動入力のサンプル

## 動作イメージ

|IME|ドロップダウン|
|:---:|:---:|
|<img src=https://user-images.githubusercontent.com/11660859/169837920-7d96d2cf-9ce1-4a86-be2c-04c06daf98a2.gif width=300px/>|<img src=https://user-images.githubusercontent.com/11660859/169837970-a9262815-09aa-44b7-8893-9b5f6c76fd77.gif width=300px/>|

## 解説

https://github.com/kyam3235/ComposeAutofillSample/blob/8c7b7b8e37da0883f94303f848d87d35a8a65bec/ComposeAutofillSample/app/src/main/java/jp/kyamlab/composeautofillsample/MainActivity.kt#L34-L115

- [AutofillTree](https://developer.android.com/reference/kotlin/androidx/compose/ui/autofill/AutofillTree)に対して[AutofillNode](https://developer.android.com/reference/kotlin/androidx/compose/ui/autofill/AutofillNode)を追加することでアカウント情報をひとまとまりとして扱える
- `autofillTypes`に指定した値をアカウント情報から取得することができる
- [自動入力をキーボードと統合する](https://developer.android.com/guide/topics/text/ime-autofill?hl=ja)に記載されている通り、Android11以降で自動入力をサポートしているIMEを利用している場合はIMEに候補が表示される。IMEが自動入力をサポートしていない場合はプルダウンメニューに表示される。
- プルダウンメニューの位置は`autofillNode.boundingBox`で指定できる

## サンプルコード開発環境

Android Studio Chipmunk | 2021.2.1
