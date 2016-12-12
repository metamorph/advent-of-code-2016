(ns day8.core-test
  (:require [clojure.test :refer :all]
            [day8.core :refer :all]))

(def small-display (make-display 7 3))


(deftest a-test
  (testing "display instructions"
    (is (= [".#..#.#"
            "#.#...."
            ".#....."]
           (map (partial apply str)
                (-> small-display
                (rect 3 2)
                (rotate-column 1 1)
                (rotate-row 0 4)
                (rotate-column 1 1))))))
  (testing "display instructions as text"
    (is (= [".#..#.#"
            "#.#...."
            ".#....."]
           (map (partial apply str)
                (transform small-display ["rect 3x2"
                                          "rotate column x=1 by 1"
                                          "rotate row y=0 by 4"
                                          "rotate column x=1 by 1"]))))))
