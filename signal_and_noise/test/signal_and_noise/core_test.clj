(ns signal-and-noise.core-test
  (:require [clojure.test :refer :all]
            [signal-and-noise.core :refer :all]))

(deftest a-test
  (testing "Sample input"
    (is (= "easter" (error-corrected
                     ["eedadn"
                      "drvtee"
                      "eandsr"
                      "raavrd"
                      "atevrs"
                      "tsrnev"
                      "sdttsa"
                      "rasrtv"
                      "nssdts"
                      "ntnada"
                      "svetve"
                      "tesnvt"
                      "vntsnd"
                      "vrdear"
                      "dvrsen"
                      "enarar"])))))
