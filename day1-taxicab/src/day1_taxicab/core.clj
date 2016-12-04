(ns day1-taxicab.core
  (:require [clojure.string :refer :all]))

(def input "The navigation instructions"
  "L2, L5, L5, R5, L2, L4, R1, R1, L4, R2, R1, L1, L4, R1, L4, L4, R5, R3, R1, L1, R1, L5, L1, R5, L4, R2, L5, L3, L3, R3, L3, R4, R4, L2, L5, R1, R2, L2, L1, R3, R4, L193, R3, L5, R45, L1, R4, R79, L5, L5, R5, R1, L4, R3, R3, L4, R185, L5, L3, L1, R5, L2, R1, R3, R2, L3, L4, L2, R2, L3, L2, L2, L3, L5, R3, R4, L5, R1, R2, L2, R4, R3, L4, L3, L1, R3, R2, R1, R1, L3, R4, L5, R2, R1, R3, L3, L2, L2, R2, R1, R2, R3, L3, L3, R4, L4, R4, R4, R4, L3, L1, L2, R5, R2, R2, R2, L4, L3, L4, R4, L5, L4, R2, L4, L4, R4, R1, R5, L2, L4, L5, L3, L2, L4, L4, R3, L3, L4, R1, L2, R3, L2, R1, R2, R5, L4, L2, L1, L3, R2, R3, L2, L1, L5, L2, L1, R4")

(defn parse-one "Parse one navigation instruction into a vector `[:l|:r <steps>]`."
  [s]
  (let [[direction & steps] s]
    [(keyword (lower-case direction))
     (Integer/parseInt (apply str steps))]))

(defn parse-input "Parse input into a list of vectors (see 'parse-one')."
  [s]
  (map parse-one (split s #",\s*")))

(defn asserted "Verifies that a value matches a predicate. Returns the value."
  ([pred v] (assert (pred v)) v)
  ([pred v msg] (assert (pred v) msg) v))

(defn assert-not-nil "Checks that a value is not nil."
  [v] (asserted (complement nil?) v "Value is nil"))

(defn turned "Given a navigation direction (left/right) return the direction (:n, :s, :e, :w)."
  [facing turning]
  (let [directions      [:n :e :s :w]
        facing-idx      (assert-not-nil (.indexOf directions facing))
        shift-direction (case turning
                          :l -1
                          :r 1
                          (throw (IllegalArgumentException. "Not a valid turning direction")))]
    (get directions
         (mod (+ facing-idx shift-direction) 4))))

(defn direction->movement "Return a vector `[dx dy]` that defines how to move in a given `direction`"
  [direction]
  (assert-not-nil
   (get {:n [0 -1]
         :e [1 0]
         :s [0 1]
         :w [-1 0]} direction)))

(defn expand-path "Given a `direction` and a `coordinate` and a number of `steps` - return the path (minus the initial `coordinate`) as a vector of `[x y]`."
  [direction [x y :as coordinate] steps]
  (let [movement (direction->movement direction)]
    (vec (drop 1 (take (inc steps) (iterate #(mapv + % movement) [x y]))))))

(defn reducer-fn
  [{:keys [position direction path] :as state}
   [turn nsteps]]
  (let [new-direction (turned direction turn)
        new-path      (expand-path new-direction position nsteps)]
    (-> state
        (assoc :position (last new-path))
        (update :path #(concat % new-path))
        (assoc :direction new-direction))))

(defn create-path [input]
  (->> input
       parse-input
       (reduce reducer-fn {:position [0 0]
                           :direction :n
                           :path []})))

(defn find-crossings [input]
  (let [path       (:path (create-path input)) ;; get the path
        duplicates (->> (frequencies path)
                        (filter #(let [[_ n] %] (> n 1))) ;; include entries with frequency > 1
                        (map first))] ;; extract the coordinates
    (filter (set duplicates) path))) ;; from the path, return only coordinates that occur in the list of duplicates



