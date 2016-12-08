(ns ipv7.core-test
  (:require [clojure.test :refer :all]
            [ipv7.core :refer :all]))


(deftest abba-test
  (testing "abba" 
    (is (not (abba? "fhrjdfh")))
    (is (not (abba? "sflbbbbsf")))
    (is (abba? "sabba"))
    (is (abba? "dfkjhabbas"))))

(deftest a-test
  (testing "Sample data"
    (is (support-tls? "abba[mnop]qrst"))
    (is (not (support-tls? "abcd[bddb]xyyx")))
    (is (not (support-tls? "aaaa[qwer]tyui")))
    (is (support-tls? "ioxxoj[asdfgh]zxcvbn"))))
