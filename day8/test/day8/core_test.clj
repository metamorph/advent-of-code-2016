(ns day8.core-test
  (:require [clojure.test :refer :all]
            [day8.core :refer :all]))

(def small-display (make-display 7 3))


(deftest a-test
  (testing "display instructions"
    (is (= [".#..#.#"
            "#.#...."
            ".#....."]
           (-> small-display
               (rect 3 2)
               (rotate-column 1 1)
               (rotate-row 0 4)
               (rotate-column 1 1))))))
