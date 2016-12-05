(ns security.core-test
  (:require [clojure.test :refer :all]
            [security.core :refer :all]))

(deftest a-test
  (testing "test sample input"
     (is (valid-room? (enc->room "aaaaa-bbb-z-y-x-123[abxyz]")))
     (is (= 1514 (sum-sector-ids ["aaaaa-bbb-z-y-x-123[abxyz]"
                                  "a-b-c-d-e-f-g-h-987[abcde]"
                                  "not-a-real-room-404[oarel]"
                                  "totally-real-room-200[decoy]"]))))
  (test "decryption"
        (is (= "very encrypted name"
               (decrypt-name "qzmt-zixmtkozy-ivhz")))))


