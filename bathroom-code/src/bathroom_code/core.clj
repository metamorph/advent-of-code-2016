(ns bathroom-code.core)

(def test-data ["ULL"
                "RRDDD"
                "LURDL"
                "UUUUD"])

(def std-keypad [[1 2 3]
                 [4 5 6]
                 [7 8 9]])

(defn value-from [keypad [x y]] (-> (get keypad y) (get x)))
(defn direction->movement [direction]
  (case direction
    \U [0 -1]
    \L [-1 0]
    \R [1 0]
    \D [0 1]
    (throw (IllegalArgumentException. (str "Invalid direction: " direction)))))

(defn move [keypad [x y :as current] direction]
  (let [movement (direction->movement direction)
        next-coord (mapv + current movement)
        next-value (value-from keypad next-coord)]
    (if (nil? next-value) current next-coord)))

(defn crack-the-code [keypad [x y :as starting-point] instruction] "1984")
