(ns security.core)

(defn enc->room "Parse a string to a room struct (name/sector-id/checksum)."
  [s]
  (let [[m a b c] (re-find #"([a-z-]+)-([0-9]+)\[([a-z]+)\]" s)]
    (if m
      {:name a :sector-id (Integer/parseInt b) :checksum c}
      (throw (IllegalArgumentException. (str "Not a valid room: " s))))))

(defn name->checksum
  [name]
  (let [chars (filter #(not (= \- %)) name)
        by-char (frequencies chars)
        comp-fn (fn [[k1 v1] [k2 v2]]
                  (if (not= v1 v2) (- v1 v2) (- (int k2) (int k1))))
        most-frequent-chars (map first (reverse (sort comp-fn by-char)))]
    (apply str (take 5 most-frequent-chars))))

(defn valid-room? [{:keys [name checksum]}] (= checksum (name->checksum name)))

(defn read-enc-rooms []
  (clojure.string/split-lines (slurp (clojure.java.io/resource "input.txt"))))

(defn sum-sector-ids [input]
  (let [rooms (map enc->room input)
        valid (filter valid-room? rooms)]
    (reduce + 0 (map :sector-id valid))))

(defn shift-char [c]
  (if (= \- c)
    \space
    (let [n (- (int c) 97)]
      (char (+ 97 (mod (inc n) 26))))))


(defn decrypt-name [name] name)
