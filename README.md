SimplePoker
=============

概要
-------------
SimplePokerは、テキサスホールデムの戦略のモデリング・シミュレーションのための環境です。

現在、/src/test/java/*/PokerTester.javaで実行できることを確認していますが、最小限の実行環境なため、ユーザビリティは考慮していません。

使い方
-------------
AdaptStrategyクラスを継承した戦略を作成し、戦略内でベットを決定させます。これが、目的関数となり、その戦略をPlayerにセットしてから、比較対象となる戦略を他のPlayerにセットします。


次にGameFuctoryからPokerGameを作成したあと、addPlayer関数によってプレイヤーを追加し、getTableによってTableを取得してから、取得したTableのデータを初期化します。これにより、目的関数の実行可能領域（制約条件）を指定します。

最後に、作成したPokerGameをexecuteメソッドで実行すると、Playerが一人になるまでゲームが実行され、Playerが一人になった時点で、outputフォルダにバンクロールの推移データが出力されます。

実行環境
--------------
java version "1.7.0_21"

maven version "2.2.1"

junit version "4.11"



ライセンス
-------------
 
	SimplePoker
	The MIT License (MIT)
	Copyright(c) 2013, Shun Sugiyama
 
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 
 Date: Sat Feb 3 04:15:30 2013 JST

