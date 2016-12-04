(ns triangles.core)

(defn read-input []
  (clojure.string/split-lines
   (slurp (clojure.java.io/resource "input.txt"))))

(defn parse-line [line]
  (map #(Integer/parseInt %)
       (clojure.string/split (clojure.string/trim line) #"\s+" 3)))

(defn parse-lines [lines] (map parse-line lines))

(defn concat-columns [parts]
  (concat (map first parts)
          (map second parts)
          (map last parts)))
(defn groups-of-three [xs]
  (partition 3 xs))

(defn valid-triangle? [[a b c :as sides]]
  (let [sorted (reverse (sort sides))]
    (< (first sorted) (apply + (rest sorted)))))

(defn count-valid-triangles []
  (let [triangles (parse-lines (read-input))]
    (count (filter valid-triangle? triangles))))

(defn count-valid-triangles-by-column []
  (let [rows (parse-lines (read-input))
        triangles (groups-of-three (concat-columns rows))]
    (count (filter valid-triangle? triangles))))
