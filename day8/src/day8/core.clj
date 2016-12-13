(ns day8.core
  (:gen-class))

(defn make-display [width height]
  (->> \.
       (repeat width)
       (vec)
       (repeat height)
       (vec)))


(defn update-column [display n f]
  (let [vals    (map #(get % n) display)
        updated (f vals)]
    (mapv #(assoc %1 n %2) display updated)))

(defn plot-at [display x y v]
  (update display y #(assoc % x v)))

(defn s->int [s] (Integer/parseInt s))

(defn rotate [coll n] (vec (take (count coll)
                                 (drop (- (count coll) n)
                                       (cycle (seq coll))))))

(defn rotate-column [display col n] (update-column display col #(rotate % n)))
(defn rotate-row [display row n] (update display row rotate n))
(defn rect [display width height]
  (let [coords (for [x (range width)
                     y (range height)] [x y])]
    (reduce (fn [d [x y]] (plot-at d x y \#)) display coords)))

(defn unary-rect-fn [x y] #(rect % x y))
(defn unary-rotate-column-fn [col n] #(rotate-column % col n))
(defn unary-rotate-row-fn [row n] #(rotate-row % row n))
(defn instruction->fn [s]
  (condp re-find s
    #"rect (\d+)x(\d+)"               :>> (fn [[_ x y]] (unary-rect-fn (s->int x) (s->int y)))
    #"rotate column x=(\d+) by (\d+)" :>> (fn [[_ col n]] (unary-rotate-column-fn (s->int col) (s->int n)))
    #"rotate row y=(\d+) by (\d+)"    :>> (fn [[_ row n]] (unary-rotate-row-fn (s->int row) (s->int n)))
    (throw (IllegalArgumentException. (str "Illegal input: " s)))))
(defn transform [display instructions]
  (let [fns (map instruction->fn instructions)]
    (reduce #(%2 %1) display fns)))


(defn -main
  [& args]
  (let [instructions (clojure.string/split-lines (slurp (clojure.java.io/resource "input.txt")))
        fns (map instruction->fn instructions)
        display (reduce #(%2 %1) (make-display 50 6) fns)]
    (reduce + 0 (map (fn [coll] (count (filter #(= % \#) coll))) display))))

