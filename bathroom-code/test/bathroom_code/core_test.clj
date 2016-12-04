(ns bathroom-code.core-test
  (:require [clojure.test :refer :all]
            [bathroom-code.core :refer :all]))

(deftest cracking
  (testing "sample code"
    (is (= "1985" (crack-the-code std-keypad [1 1] test-data)))))
