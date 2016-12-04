(ns triangles.core-test
  (:require [clojure.test :refer :all]
            [triangles.core :refer :all]))

(deftest valid-triangles
  (testing "Invalid triangle"
    (is (= false (valid-triangle? [5 10 25])))))
