(ns security.core)

(defn enc->room "Parse a string to a room struct (name/sector-id/checksum)."
  [s]
  (let [[m a b c] (re-find #"([a-z-]+)-([0-9]+)\[([a-z]+)\]" s)]
    (if m
      {:name a :sector-id (Integer/parseInt b) :checksum c}
      (throw (IllegalArgumentException. (str "Not a valid room: " s))))))

(defn name->checksum "TODO: Doesn't work - only sort alphabetical if a tie!"
  [name]
  (let [chars (filter #(not (= \- %)) name)
        by-char (frequencies chars)
        most-frequent-chars (map first (reverse (sort-by last by-char)))]
    (apply str (take 5 (sort most-frequent-chars)))))


(defn valid-room? [{:keys [name checksum]}] (= checksum (name->checksum name)))


(defn sum-sector-ids [input]
  (let [rooms (map enc->room input)
        valid (filter valid-room? rooms)]
    (reduce + 0 (map :sector-id valid))))
