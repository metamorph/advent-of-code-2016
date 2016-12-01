(ns day1-taxicab.core
  (:require [clojure.string :refer :all]))

; From the instructions - calculate the coordinate in the grid from the starting point (being the origo).
; Then find the shortest path.

(def input "L2, L5, L5, R5, L2, L4, R1, R1, L4, R2, R1, L1, L4, R1, L4, L4, R5, R3, R1, L1, R1, L5, L1, R5, L4, R2, L5, L3, L3, R3, L3, R4, R4, L2, L5, R1, R2, L2, L1, R3, R4, L193, R3, L5, R45, L1, R4, R79, L5, L5, R5, R1, L4, R3, R3, L4, R185, L5, L3, L1, R5, L2, R1, R3, R2, L3, L4, L2, R2, L3, L2, L2, L3, L5, R3, R4, L5, R1, R2, L2, R4, R3, L4, L3, L1, R3, R2, R1, R1, L3, R4, L5, R2, R1, R3, L3, L2, L2, R2, R1, R2, R3, L3, L3, R4, L4, R4, R4, R4, L3, L1, L2, R5, R2, R2, R2, L4, L3, L4, R4, L5, L4, R2, L4, L4, R4, R1, R5, L2, L4, L5, L3, L2, L4, L4, R3, L3, L4, R1, L2, R3, L2, R1, R2, R5, L4, L2, L1, L3, R2, R3, L2, L1, L5, L2, L1, R4")

(defn parse-one [s]
  (let [[direction steps] s]
    [(keyword (lower-case direction))
     (Integer/parseInt (str steps))]))

(defn parse-input
  "Parse input into a list of vectors (see 'parse-one')"
  [s]
  (map parse-one (split s #",\s+")))

;; TODO: Put direction matrices in a vector and loop over that using `mod` to 
;; add steps to the current position.

(defn turn [current direction]
  (case direction
    :r (mod (inc current) 4)
    :l (mod (dec current) 4)))

(defn apply-action [{:keys [direction x y] :as state}
                  [turn steps :as action]]
  [])


