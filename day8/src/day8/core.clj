(ns day8.core
  (:gen-class))

(defn make-display [width height]
  (->> \.
       (repeat width)
       (vec)
       (repeat height)
       (vec)))

(defn rotate [coll n] (vec (take (count coll)
                             (drop (- (count coll) n)
                                   (cycle (seq coll))))))
(defn instruction->fn [s]
  (condp re-find s
    #"rect (\d+)x(\d+)" :>> #(let [[_ x y] %]
                               (fn [d] (rect d (Integer/parseInt x) (Integer/parseInt y))))
    #"rotate column x=(\d+) by (\d+)" :>> #(let [[_ col n] %] (fn [d] (rotate-column d (Integer/parseInt col) (Integer/parseInt n))))
    #"rotate row y=(\d+) by (\d+)" :>> #(let [[_ row n] %] (fn [d] (rotate-row d (Integer/parseInt row) (Integer/parseInt n))))
    (throw (IllegalArgumentException. (str "Illegal input: " s)))))

(defn update-column [display n f]
  (let [vals (map #(get % n) display)
        updated (f vals)]
    (mapv #(assoc %1 n %2) display updated)))

(defn plot-at [display x y v]
  (update display y #(assoc % x v)))

(defn rotate-column [display col n] (update-column display col #(rotate % n)))
(defn rotate-row [display row n] (update display row rotate n))
(defn rect [display width height]
  (let [coords (for [x (range width)
                     y (range height)] [x y])]
    (reduce (fn [d [x y]] (plot-at d x y \#)) display coords)))

(defn transform [display instructions]
  (let [fns (map instruction->fn instructions)]
    (reduce #(%2 %1) display fns)))


(defn -main
  [& args]
  (let [instructions (clojure.string/split-lines (slurp (clojure.java.io/resource "input.txt")))
        fns (map instruction->fn instructions)
        display (reduce #(%2 %1) (make-display 50 6) fns)]
    (reduce + 0 (map (fn [coll] (count (filter #(= % \#) coll))) display))))

