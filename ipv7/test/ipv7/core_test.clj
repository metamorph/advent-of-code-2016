(ns ipv7.core-test
  (:require [clojure.test :refer :all]
            [ipv7.core :refer :all]))

(deftest a-test
  (testing "Sample data"
    (is (support-tls? "abba[mnop]qrst"))
    (is (not (support-tls? "abcd[bddb]xyyx")))
    (is (not (support-tls? "aaaa[qwer]tyui")))
    (is (support-tls? "ioxxoj[asdfgh]zxcvbn"))))
