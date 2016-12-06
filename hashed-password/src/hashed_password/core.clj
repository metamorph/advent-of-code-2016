(ns hashed-password.core
  (:gen-class)
  (:require digest))

(defn valid-hash? [s] (clojure.string/starts-with? s "00000"))
(defn hash->digit [s] (first (drop 5 s)))
(defn hash-generator [room-id]
  (let [initial {:hash ""
                 :idx 1}]
    (iterate
     (fn [{:keys [idx] :as m}]
       (-> m
           (assoc :hash (digest/md5 (str room-id (str (inc idx)))))
           (assoc :idx (inc idx))))
     initial)))

(defn hack-password [room-id]
  (apply str (eduction (comp
                        (filter #(valid-hash? (:hash %)))
                        (map #(hash->digit (:hash %)))
                        (take 8))
                       (hash-generator room-id))))

(def find-hashes-tail [room-id]
  (let [hashes (hash-generator room-id)]
    (loop [hashes hashes
           result []]

      (recur ))))

(defn -main [& args]
  (printf "Password is: %s\n" (hack-password "uqwqemis")))

