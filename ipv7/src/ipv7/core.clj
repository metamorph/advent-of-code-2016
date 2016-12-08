(ns ipv7.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn -abba?
  "Takes a four char string and checks if it's an ABBA."
  [[a b c d]]
  (and (= a d)
       (= b c)
       (not= a b)))
(defn abba? "Checks is a string contains a subsequence of 4 chars that is ABBA."
  [s] (->> s (partition 4 1) (some -abba?)))

(defn partition-ipv
  "Takes an IPV7 and returns the hypernet parts and 'other' parts:
  {:hypernets ['dfjh' 'dsfjh']
   :others ['dsfsdf' 'sdfsdf']}"
  [ip]
  (loop [ip ip
         hypernet? false
         current []
         state {:hypernets []
                :others []}]
    (if (empty? ip)
      (if hypernet?
        (update state :hypernets conj current)
        (update state :others conj current))
      (let [c (first ip)]
        (cond
          (= \[ c) (recur (drop 1 ip) true [] (-> state (update :others conj current)))
          (= \] c) (recur (drop 1 ip) false [] (-> state (update :hypernets conj current)))
          :otherwise (recur (drop 1 ip) hypernet? (conj current c) state))))))

(defn support-tls? [s]
  (let [{:keys [hypernets others]} (partition-ipv s)]
    (and (not-any? abba? hypernets)
         (some abba? others))))

(defn count-tls [lines]
  (count (filter support-tls? lines)))

(defn count-ssl [lines]
  ;; TODO: Implement me
  10)

(defn -main
  [& args]
  (count-tls (str/split-lines (slurp (io/reader "input.txt")))

